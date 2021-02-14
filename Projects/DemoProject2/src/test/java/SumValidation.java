import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses()
	{
		int sum = 0;
		JsonPath js=new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
		System.out.println("1. Print No of courses returned by API");
		int count=	js.getInt("courses.size()");
		System.out.println(count);
		//Print Purchase Amount
		System.out.println("2. Print Purchase Amount");
		int totalAmmount=	js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmmount);
		//Print Title of the first course
		System.out.println("3. Print Title of the first course");
		String firstTitle=js.get("courses[0].title");
		System.out.println(firstTitle);
		//Print Purchase Amount
		System.out.println("4. Verify if sum of all course prices matches with Purchase Amount");
		for(int i=0;i<count;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			sum = sum + amount;
		}
		Assert.assertEquals(sum, totalAmmount);
		
       //Print All course titles and their respective Prices
	   System.out.println("5. Print All course titles and their respective Prices");
       for(int i=0;i<count;i++)
       {
	      String courseTitles=js.get("courses["+i+"].title");
	      System.out.println(js.get("courses["+i+"].price").toString());
	      System.out.println(courseTitles);
	    }
       //Print no of copies sold by RPA Course
       System.out.println("6. Print no of copies sold by RPA Course");
       for(int i=0;i<count;i++)
       {
	      String courseTitles=js.get("courses["+i+"].title");
	      if(courseTitles.equalsIgnoreCase("RPA"))
	         {
		         int copies=js.get("courses["+i+"].copies");
		         System.out.println(copies);
		         break;
             }
	   }
 
	}
}