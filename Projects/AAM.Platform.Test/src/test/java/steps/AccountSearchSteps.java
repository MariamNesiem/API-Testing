package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.AccountSearchPayload;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class AccountSearchSteps {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	AccountSearchPayload AS;
    static String AccessToken="Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc2OWQ4Mjc1NDUxYjA2OTEwZWFmYTE0M2QzMDUzNTkzIiwidHlwIjoiYXQrand0In0.eyJuYmYiOjE2MTY4NjA2ODAsImV4cCI6MTYxNjg2NDI4MCwiaXNzIjoiaHR0cHM6Ly9wbGF0Zm9ybS1zZWN1cml0eS1xYS5hYW1saXZlLmNvbSIsImF1ZCI6InNhbGVzZm9yY2VBdXRoZW50aWNhdGlvbkFwaSIsImNsaWVudF9pZCI6InNhbGVzZm9yY2VBdXRoZW50aWNhdGlvbkNsaWVudCIsInN1YiI6ImM2ODMxMWViLTZiNTgtNGViMS1iN2FlLThkNDBkYzcwY2JlOSIsImF1dGhfdGltZSI6MTYxNjg2MDY4MCwiaWRwIjoiU2FsZXNmb3JjZSIsInVzZXJuYW1lIjoiYWFtLWludGVncmFudGRldmdyb3VwQGFhbWxpdmUuY29tIiwiZW1haWwiOiJhYW0taW50ZWdyYW50ZGV2Z3JvdXBAYWFtbGl2ZS5jb20iLCJkaXNwbGF5X25hbWUiOiJBQU0gRGV2IFRlc3QiLCJwaWN0dXJlIjoiaHR0cHM6Ly9hYW0tLWMubmExNDAuY29udGVudC5mb3JjZS5jb20vcHJvZmlsZXBob3RvLzcyOTFCMDAwMDAwQVpraS9GIiwidGh1bWJuYWlsIjoiaHR0cHM6Ly9hYW0tLWMubmExNDAuY29udGVudC5mb3JjZS5jb20vcHJvZmlsZXBob3RvLzcyOTFCMDAwMDAwQVpraS9UIiwicmVwa2V5IjoiMTM3NTMwIiwiZW1wUmVwS2V5IjoiIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInNhbGVzZm9yY2VBdXRoZW50aWNhdGlvbkFwaSJdLCJhbXIiOlsiZXh0ZXJuYWwiXX0.M3D81iddVTJeBXQD4SBrvYxzHhKrTD2kY2uQ3v4QZlJ3LYbz0ZY0HNgOoCSbvulM2pt49g4RhjwZ4DPh9qmafOiMUjjX0JEq1u5SU5R4yOrU4wwyu8vDPZQpNaYvSU8-vgN4XUkMDJ07K_Jtwqpvd1df_0dBtCvuGLw6dcpby1Ras-Hk-urDEeloLq8N2vdNn8wPnAW9BfrEJvhzWjphnTbye8CBzU8dbWbP-i4tGMlm6WNhgo3Q7tcby3WKRKlwPyAwWsYAWCQQVNQvGjf3X97lBFhi8S3n24DDNznn07V9psb3h_UcZpyKNRK2Ba9GpYAROUnrQ3-Xn3FNqL-4Ag";
	@Given("Search Criteria with {string}")
	public void search_criteria_with(String accountName) {
		AS=new AccountSearchPayload();
     res=given().spec(AS.req(AccessToken))
				.body(AS.BodySearchBy(accountName));
	}
	
	@When("User calls Account Search API with {string} http request")
	public void user_calls_with_http_request(String method) {
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response =res.when().post(AS.getResource());
	}
	
	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
	}
}
