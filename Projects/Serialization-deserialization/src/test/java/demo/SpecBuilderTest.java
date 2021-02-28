package demo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import GoogleMapsBody.location;
import GoogleMapsBody.requestBody;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class SpecBuilderTest {
  @Test
  public void f() {
	  requestBody RB= new requestBody();
	  location l= new location();
	  l.setLat(-38.383494);
	  l.setLng(33.427362);
		RB.setLocation(l) ;
		RB.setAccuracy(50);
		RB.setName("Frontline house");
		RB.setPhone_number("(+91) 983 893 3937");
		RB.setAddress("29, side layout, cohen 09");
		RB.setWebsite("http://google.com");
		RB.setLanguage("French-IN");
		List<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shope");
		RB.setTypes(types);
		
//Request Spec Builder
	   RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
	  .setContentType(ContentType.JSON).build();
	   
	  RequestSpecification req1=given().spec(req).body(RB);
	  
//Response Spec Builder
	  ResponseSpecification res =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	  Response res2 =req1.when().post("/maps/api/place/add/json").
	  then().spec(res).extract().response();

	  String responseString=res2.asString();
	  System.out.println(responseString);
  }
}
