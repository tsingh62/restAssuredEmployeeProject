package WebServicesTestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC2_POST_Request 
{
		@Test
		public void registerCustomet() throws InterruptedException
		{
			RestAssured.baseURI="http://restapi.demoqa.com/customer";
			
			RequestSpecification httprequest=RestAssured.given();
			
			// For Post we need to define the body as well
			// Request body
			// Specifying request Pay-load in JASON format
			JSONObject requestParams=new JSONObject();
			
			requestParams.put("FirstName","xyz123400989899090"); // put is adding values to paramters
			requestParams.put("LastName","abc123400998909009"); // here put is a method to add values to params
			requestParams.put("UserName","xyz12340097909008");
			requestParams.put("Password","xyz12340098809");
			requestParams.put("Email","ascxyz8761209003@gmail.com");
			
			
			// Specify body type is JSON/Content type 
			httprequest.header("Content-Type","application/json");
			
			// Add the JSON to the body of the request
			httprequest.body(requestParams.toJSONString());// Must be there
		
			
			// POST request 
			Response response = httprequest.request(Method.POST,"/register"); // specify the path
		//	Response response = request.post("/register"); // also correct
			System.out.println(response);
			
			Thread.sleep(3000);
			
			//Validate the response status code
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			Assert.assertEquals(statusCode, 201);
			
			// Verifying success code from response body
			String successCode = response.jsonPath().get("SuccessCode");
			System.out.println(successCode);
			//validate success code
			Assert.assertEquals(successCode, "OPERATION_SUCCESS");
			 		
			
		}
	
	
}
