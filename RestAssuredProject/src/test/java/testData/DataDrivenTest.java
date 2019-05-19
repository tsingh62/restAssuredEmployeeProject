package testData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest 
{
		
	@Test
	public void createRecord() throws IOException
	{
	
	// Xl file path 
	String path = System.getProperty("user.dir")+ "//src/test/java/testData/TestData.xlsx";

		
	RestAssured.baseURI = "http://localhost:8085";
	RequestSpecification request = RestAssured.given();

	// Count no of records present in Xl file 
	int rowcount = XLUtils.getRowCount(path, "Sheet1");
	System.out.println(rowcount);
	
		for (int i=1; 1<=rowcount; i++)
			// here i=0 refers to the header
			// to avoid the header we use i=1
			// Col= value starts from 0
		{
	
			// JSONObject is a class that represents a simple JSON. We can add Key -
			// Value pairs using the put method
		
			//For POST request we need to create a Json object value
			// as we need to pass some value to the database
			JSONObject requestParams = new JSONObject();
			
			requestParams.put("id", XLUtils.getCellData(path, "Sheet1", i, 0)); // Cast
			requestParams.put("firstName", XLUtils.getCellData(path, "Sheet1", i, 1));
			requestParams.put("lastName", XLUtils.getCellData(path, "Sheet1", i, 2));
			requestParams.put("email", XLUtils.getCellData(path, "Sheet1", i, 3));
			requestParams.put("programme", XLUtils.getCellData(path, "Sheet1", i,4));
			
			// Sub-set of the course passed separately   
			// See xl sheet 
			List<String> courses = new ArrayList<String>();
			courses.add(XLUtils.getCellData(path, "Sheet1", i, 5));
			courses.add(XLUtils.getCellData(path, "Sheet1", i, 6));
			
			// now pass courses as other parameters have been passed
				// after passed the two values for courses
			requestParams.put("courses", courses);
		
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
		
			// Add the Json to the body of the request
			request.body(requestParams.toJSONString()); // Must else will give error
		
			// Post the request and check the response
			//Response response = request.post("/student");   // HTTP Post method
			Response response=request.request(Method.POST,"/student");
							
					
			System.out.println("Response body: " + response.body().asString());
		
			// Validate the Response status code
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 201); // 201- Created
		
			// Validate the Response success code
			 // To extract the node we use Json path ==vip
			String successCode = response.jsonPath().get("msg");
			Assert.assertEquals(successCode, "Student added");
		}
	
	
	
	}
	
}
	
