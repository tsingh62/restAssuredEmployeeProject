package WebServicesTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_GET_ListUsers 
{
	@Test
	public void listUers()
	{
		RestAssured.baseURI="https://reqres.in";
		
		RequestSpecification req = RestAssured.given();
		
		Response res = req.request(Method.GET,"/api/users?page=2");
		
		String responseBody = res.getBody().asString();
		System.out.println(responseBody);
		
		// Extract status code and verification
		int statuscode = res.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);
		
		// Status line 
		String statusline = res.getStatusLine();
		System.out.println(statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		
	
		
	
	}
	
}
