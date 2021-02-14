import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import files.payload;


public class basics {
public static void main(String[] args) {
		//given when then
		//validate POST method(Add Place) is working as expected
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String responsePostMethod= given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		//or get info from file
		//.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\rahul\\Documents\\Addbookdetails.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat()
		.statusCode(200)
		.body("scope", equalTo("APP"))
		.header("server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
		
		System.out.println("The Response is: "+responsePostMethod);
		
		//get data from Response
		JsonPath js=new JsonPath(responsePostMethod);
		String placeId= js.getString("place_id");

		System.out.println("Place Id: "+placeId);

		//Update Place with New Address
		String responceUpdateMethod= given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.UpdatePlace(placeId))
				.when().put("maps/api/place/update/json")
				.then().assertThat()
				.statusCode(200)
				.body("msg", equalTo("Address successfully updated"))
				.header("server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
		
		JsonPath js2=new JsonPath(responceUpdateMethod);
		String msg= js2.getString("msg");

		System.out.println("msg: "+msg);
		
		//Get Place to validate if New address is present in response
		String responceGetMethod= given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all()
				.statusCode(200)
				.body("address", equalTo("50, side layout, cohen 09"))
				.header("server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
		
		JsonPath js3=new JsonPath(responceGetMethod);
		
		String newAddress= js3.getString("address");

		System.out.println("newAddress: "+newAddress);
		Assert.assertEquals(newAddress,"50, side layout, cohen 09");
	}
	
	

}
