package pojo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AccountSearchPayload {
	private String baseURI="https://platform-account-service-qa.aamlive.com";
	private String resource="/api/v1/data/Account/Search";
	private RequestSpecification req;
	
	public RequestSpecification req(String AccessToken) {
		this.req =new RequestSpecBuilder().setBaseUri(this.baseURI)
				   .addHeader("Authorization",AccessToken)
					  .setContentType(ContentType.JSON).build();
		return this.req;
	}
	
	public String getResource() {
		return this.resource;
	}
	
	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	public String BodySearchBy(String accountname){
		return "{\r\n"
				+ "    \"accountTypeKey\": null,\r\n"
				+ "    \"advisorRoleTypeKey\": null,\r\n"
				+ "    \"brokerNumberKey\": null,\r\n"
				+ "    \"clearingAgentAccountNumber\": null,\r\n"
				+ "    \"clearingAgentKey\": null,\r\n"
				+ "    \"companyKey\": null,\r\n"
				+ "    \"fisAccountName\": \""+accountname+"\",\r\n"
				+ "    \"fisAccountNumber\": null,\r\n"
				+ "    \"pageNumber\": 1,\r\n"
				+ "    \"pageSize\": 3000,\r\n"
				+ "    \"repKey\": null,\r\n"
				+ "    \"showAllMMFlag\": 0,\r\n"
				+ "    \"showDeletedFlag\": 0,\r\n"
				+ "    \"sortDirection\": null,\r\n"
				+ "    \"sortField\": null,\r\n"
				+ "    \"taxIDNumber\": null,\r\n"
				+ "    \"userRepKey\": 137530\r\n"
				+ "}";
	}
	}
