package jiraAPITest;


import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import jiraAPITest.payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class NewTest {
//	Properties prop=new Properties();
//	@BeforeTest
//	public void getData() throws IOException
//	{
//		FileInputStream fis=new FileInputStream("C:\\Users\\rahul\\workspace\\DemoProject\\src\\files\\env.properties");
//		prop.load(fis);
//		//prop.get("HOST");
//	}
	@Test
	public void JiraAPICreateIssue()
	{
		RestAssured.baseURI= "http://localhost:8080/";
		String projectID="RESA";
//Get SeccionKey
		SessionFilter session=new SessionFilter();
		String getSessionResponse=given().header("Content-Type", "application/json")
			.body(payload.getSessionBody()).filter(session)
			.when().post("rest/auth/1/session")
			.then().assertThat()
			.statusCode(200).extract().response().asString();
		//we can get session id by filter or by the following
		JsonPath js= new JsonPath(getSessionResponse);
		   String sessionID=js.get("session.name")+"="+js.get("session.value");
		   System.out.println(sessionID);
		   	   
//Creating Issue/Defect
		String addIssueResponse=given().header("Content-Type", "application/json").
	    //	header("Cookie",sessionID).
		body(payload.addIssueIntoProjectBody(projectID)).filter(session).when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response().asString();
		
		   JsonPath js2= new JsonPath(addIssueResponse);
		   String issueID=js2.get("id");
		   System.out.println(issueID);

//Add Attachment
		   String addAttachmentResponse=given().header("X-Atlassian-Token", "no-check").
				   header("Content-Type", "multipart/form-data")
					.pathParam("issueID",issueID).multiPart("file",new File("jira.txt"))
					.filter(session).when().post("/rest/api/2/issue/{issueID}/attachments").
					then().statusCode(200).log().all().extract().response().asString();
//Add Comment
          String addCommentResponse = given().pathParam("key", issueID).log().all()
        		  .header("Content-Type","application/json").body("{\r\n" +
		   "    \"body\": \"my comment\",\r\n" +
		   "    \"visibility\": {\r\n" +
		   "        \"type\": \"role\",\r\n" +
		   "        \"value\": \"Administrators\"\r\n" +
		   "    }\r\n" +
		   "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all()
		   .assertThat().statusCode(201).extract().response().asString();

		   JsonPath js3=new JsonPath(addCommentResponse);
		   String commentId= js3.getString("id");

//Get Issue
		   String issueDetails=given().filter(session).pathParam("key", issueID)
		   .queryParam("fields", "comment")
		   .log().all().when().get("/rest/api/2/issue/{key}").then()
		   .log().all().extract().response().asString();
		   System.out.println(issueDetails);

		   JsonPath js1 =new JsonPath(issueDetails);
		   int commentsCount=js1.getInt("fields.comment.comments.size()");

		   for(int i=0;i<commentsCount;i++)
		   {
		   String commentIdIssue =js1.get("fields.comment.comments["+i+"].id").toString();
		      if (commentIdIssue.equalsIgnoreCase(commentId))
		      {
		    	  String message= js1.get("fields.comment.comments["+i+"].body").toString();
		    	  System.out.println(message);
		          Assert.assertEquals(message, "my comment");
		   }
		   }

	}
}
