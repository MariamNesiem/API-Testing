package AuthorizationCode;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import AuthorizationCode.payload;
import io.restassured.path.json.JsonPath;

public class NewTest {

	@Test
	public void OAuthGrantType() throws InterruptedException
	{
		

//Get Code need to do this with the browser itself so we will use selenium
//don't work any more as ggogle prevent automation from hitting its account
//		System.setProperty("webdriver.chrome.driver", "C://Users//mariam.nesiem//Desktop//OAuthProject//src//test//resources//chromedriver.exe");
//		WebDriver driver =new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=test");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("mariamnesiem90@gmail.com");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("KEMSOHIEN88f");
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);	
//		String URL=driver.getCurrentUrl();
		String URL="https://rahulshettyacademy.com/getCourse.php?state=mariam&code=4%2F0AY0e-g5F9JM1xXwjmndauYBdkkWMTYWEW8k-h1loPR-Qw3IiBE38bsCu8Yf5D4oaLANyBw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialCode = URL.split("code=")[1];
		String Code =partialCode.split("&scope")[0];
		   System.out.println("Code: "+Code);
	
//
//Get Access token by Code
			String getAccessToken=given().urlEncodingEnabled(false).queryParam("code", Code)
					.queryParam("client_id", payload.ClientID)
					.queryParam("client_secret", payload.ClientSecret)
					.queryParam("redirect_uri", payload.RedirectURL)
					.queryParam("grant_type", payload.GrantType)
				.when().post(payload.Accesstokenurl)
				.then().assertThat()
				.statusCode(200).extract().response().asString();

			JsonPath js2= new JsonPath(getAccessToken);
			   String AccessToken=js2.get("access_token");
			   System.out.println("AccessToken: "+AccessToken);
//Get all courses by Access token
		   
		   String GetCourses=given().queryParam("access_token", AccessToken)
		   .when().get(payload.RedirectURL).then().extract().response().asString();
		   
			JsonPath js3= new JsonPath(GetCourses);
			   String instructor=js3.get("instructor");
			   System.out.println("instructor: "+instructor);

	}
}
