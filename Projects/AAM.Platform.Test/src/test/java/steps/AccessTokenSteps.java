package steps;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Cookie;
import pojo.*;

public class AccessTokenSteps {
//	AccessToken accessToken;
//	OAuthParam OAuthparam;
	@When("User login with valid credentials")
	public void user_login_with_valid_credentials() throws InterruptedException {
//get session by selenium			

//get session by API Request
//		 RestAssured.baseURI="https://login.salesforce.com/";
//		OAuthparam=new OAuthParam();
//		OAuthparam.setClientId();
//		OAuthparam.setClientSecret();
//		OAuthparam.setGrantType();
//		OAuthparam.setPassword();
//		OAuthparam.setUserName();
//		accessToken =given()
//				   .queryParam("grant_type",OAuthparam.getGrantType())
//				   .queryParam("client_id",OAuthparam.getClientId())
//				   .queryParam("client_secret",OAuthparam.getClientSecret())
//				   .queryParam("username",OAuthparam.getUserName())
//				   .queryParam("password",OAuthparam.getPassword())
//		  .contentType(ContentType.JSON).when()
//			.post("services/oauth2/token").as(AccessToken.class);
		
		
	}
	
	@Then("Access Token is generated successfully")
	public void access_token_is_generated_successfully() {
//		AccessToken=accessToken.getAccess_token();
//		assertNotEquals(AccessToken,null);
	}
}
