import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) 
	{
	  RestAssured.baseURI="http://216.10.245.166/";
	  
		String response= given().log().all().header("Content-Type","application/json")
		.body(payload.Addbook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().log().all()
		.statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String ID= js.get("ID");
  }
  
  @DataProvider(name="BooksData")
  public Object[][]  getData()
  {
  //array=collection of elements
  //multidimensional array= collection of arrays
  return new Object[][] {
{"asdfg","1234dfg"},
{"gfdsa","1234asdfgh"}, 
{"jklmn","12345klmnj"} 
};
  }
}
