package WebServicesTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC1_GET_Request 
{
	@Test
	public void getWeatherDetails()
	{
		// ResAssured pre-defined class
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city/Hyderabad"; // URI
										// see test case "RestAssured_Testcases.xlsx" session13
		
		//Send a request
		// Request specification class
		RequestSpecification httpRequest = RestAssured.given(); 
		//Response pre-defined class
		Response response = httpRequest.request(Method.GET,"/Delhi"); // Parameter as per request
		
		// Extract body from response
			// Convert JSON to string format
		String responsebody=response.getBody().asString();// capture the response body JSON to string 
		System.out.println(responsebody);
		
		// Getting the status code and status line in a variable 
		int statusCode=response.getStatusCode();  // capture status code
		String statusLine=response.getStatusLine(); // Capture status line
		
		
		
		// Validating 
		Assert.assertEquals(statusCode, 200); // expected = statuscode / actual=200
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK"); // expected = statusline / actual = HTTP/1.1 200 OK
		
	}

}
