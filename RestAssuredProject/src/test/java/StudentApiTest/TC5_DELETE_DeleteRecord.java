package StudentApiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_DELETE_DeleteRecord 
{

	@Test
		public void deleteRecord()
		{
			RestAssured.baseURI = "http://localhost:8085";
			RequestSpecification request = RestAssured.given();
	
					
			// Post the request and check the response
			//Response response = request.delete("/student/101");  // HTTP DELETE method
			Response response = request.request(Method.DELETE,"/student/101"); 
	
			System.out.println("Response body: " + response.body().asString()); //Empty
			
			
			// Validate the Response status code
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 204); // 204- Content not found
	
		}
	
	

}
