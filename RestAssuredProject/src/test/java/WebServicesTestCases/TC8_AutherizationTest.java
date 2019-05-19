package WebServicesTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC8_AutherizationTest 
{
	@Test
	public void AutherizationTest()
	{
		
		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		/////////////////////////////
		//Basic authentication
				// Sending userName and Password 
		PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
		authscheme.setUserName("ToolsQA");
		authscheme.setPassword("TestPassword");
		
		
		RestAssured.authentication=authscheme; // defining the type of authentication 
		
		/////////////////////////////////
		
		
		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET,"/"); // Important when you check for authentication
		
		
		//print response in console window
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		

		//status code validation
		int statusCode=response.getStatusCode();
		System.out.println("Status code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
}
