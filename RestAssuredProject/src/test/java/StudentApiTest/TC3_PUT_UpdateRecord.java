package StudentApiTest;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_PUT_UpdateRecord 
{
	@Test
	public void updateRecord()
	{
		RestAssured.baseURI = "http://localhost:8085";
		RequestSpecification request = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key -
		// Value pairs using the put method

		// Even when we are updating we need to send the full body
		// again
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("id", "101"); // Cast
		requestParams.put("firstName", "Pavan");
		requestParams.put("lastName", "Kumar");
		requestParams.put("email", "abcdefrg@gmail.com");
		requestParams.put("programme", "Trainer"); // new update
		
		List<String> courses = new ArrayList<String>();
		courses.add("Java");
		courses.add("Selenium");
		courses.add("BigData"); // new add to course 
		requestParams.put("courses", courses);

		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		request.body(requestParams.toJSONString());  // ADDING REQUEST PAYLOAD TO REQUEST BODY

		// Post the request and check the response
		  //Response response = request.put("/student/101");  // HTTP PUT method
			Response response=request.request(Method.PUT,"/student/101");
			
		System.out.println("Response body: " + response.body().asString());

		// Validate the Response status code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200); // 200- Ok

		// Validate the Response success code
		// check the node in respone by Json path
		String successCode = response.jsonPath().get("msg");
		Assert.assertEquals(successCode, "Student Updated");
	}
}

