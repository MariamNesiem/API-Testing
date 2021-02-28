package demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import GoogleMapsBody.Response;
import GoogleMapsBody.location;
import GoogleMapsBody.requestBody;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class GoogleMapsTest {
  @Test
  public void f() {
	  RestAssured.baseURI="https://rahulshettyacademy.com";
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

	  Response r=
			  given()
			//  .log().all()
			  .queryParam("key", "qaclick123")
			  .body(RB)
			  .expect().defaultParser(Parser.JSON)
			  .when().post("/maps/api/place/add/json")
			  .as(Response.class);
			//  .then().assertThat().statusCode(200);
	  
	  System.out.println(r.getStatus());
	  
  }
}
