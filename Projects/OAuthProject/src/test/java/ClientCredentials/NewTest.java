package ClientCredentials;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import ClientCredentials.payload;
import io.restassured.path.json.JsonPath;

public class NewTest {

	@Test
	public void OAuthGrantType() throws InterruptedException
	{

//Get Access token by Client Credentials
			String getAccessToken=given()
					.queryParam("client_id", payload.ClientID)
					.queryParam("client_secret", payload.ClientSecret)
					.queryParam("redirect_uri", payload.RedirectURL)
					.queryParam("grant_type", payload.GrantType)
					.queryParam("scope", payload.Scope)
					.queryParam("AccessTokenURL", payload.Accesstokenurl)
				.when().post(payload.RedirectURL)
				.then().assertThat()
				.statusCode(200).log().all().extract().response().asString();

			JsonPath js2= new JsonPath(getAccessToken);
			   String AccessToken=js2.get("accesToken");
			   System.out.println("AccessToken: "+AccessToken);
//Get all courses by Access token
		   String GetCourses=given().queryParam("access_token", AccessToken)
		   .when().get(payload.RedirectURL).then().extract().response().asString();
		   
			JsonPath js3= new JsonPath(GetCourses);
			   String instructor=js3.get("instructor");
			   System.out.println("instructor: "+instructor);

	}
}
