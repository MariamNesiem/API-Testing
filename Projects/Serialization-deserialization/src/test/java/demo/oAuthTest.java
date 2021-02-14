package demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.AccessToken;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

public class oAuthTest {
  @Test
  public void f() {
		String URL="https://rahulshettyacademy.com/getCourse.php?state=mariam&code=4%2F0AY0e-g4KyeAQJLyQK2TRMjYpVOW05g61Y66EamLb-Wa14Rzh7RfiKVulEuCrOmaN8VoLEQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		
		String partialCode = URL.split("code=")[1];
		String Code =partialCode.split("&scope")[0];
		   System.out.println("Code: "+Code);
//Get Access token by Code
//Deserialization
		   AccessToken AT=	given().urlEncodingEnabled(false)
					.queryParams("code",Code)
					.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
					.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
					.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
					.queryParams("grant_type","authorization_code").expect().defaultParser(Parser.JSON)
					.when()
					.post("https://www.googleapis.com/oauth2/v4/token").as(AccessToken.class);

//Get all courses by Access token
//Deserialization
			   GetCourse GS=given().queryParam("access_token", AT.getAccess_token()).expect().defaultParser(Parser.JSON)
			   .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

	  		
	  		System.out.println(GS.getLinkedIn());
	  		System.out.println(GS.getInstructor());
	  		System.out.println(GS.getCourses().getApi().get(1).getCourseTitle());
	  		
//Get the course price of Api (specific course)
		  		List<Api> courses=GS.getCourses().getApi();
		  		for(Api course:courses){
		  			if(course.getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
		  			System.out.println("CoursePrice API:"+ course.getPrice());
		  			break;
		  			}
		  		}

//Get the course names of WebAutomation
	  		List<WebAutomation> courseNames2=GS.getCourses().getWebAutomation();
	  		for(WebAutomation courseName:courseNames2){
	  			System.out.println("CourceName WebAutomation:"+ courseName.getCourseTitle());
	  		}

	  		
  }
}
