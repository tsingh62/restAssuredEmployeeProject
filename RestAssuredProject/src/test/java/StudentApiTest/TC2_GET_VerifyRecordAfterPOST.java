package StudentApiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC2_GET_VerifyRecordAfterPOST 
{
	@Test
	public void verifyRecordAfterPOST()
	{
		RestAssured.baseURI = "http://localhost:8085";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/student/101");  // HTTP GET method
	 
		// Retrieve the body of the Response
		// ResponseBody is a class
		ResponseBody body = response.getBody();
	 
		// Check if a String is contained in the Response Body
		// Do a String.contains
		String bodyAsString = body.asString();
		
		Assert.assertEquals(bodyAsString.contains("Pavan"), true);
		Assert.assertEquals(bodyAsString.contains("Kumar"), true);
		Assert.assertEquals(bodyAsString.contains("abcdefrg@gmail.com"), true);
		Assert.assertEquals(bodyAsString.contains("Manger"), true);

		int statusCode = response.getStatusCode(); // Getting status code
		 
		System.out.println(statusCode);
		
		// Status code verification
		Assert.assertEquals(statusCode, 200);
	}
}

