package WebServicesTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC7_ExtractEachNodeFromResponseBody 
{
	@Test
	public void extractEachNode()
	{
	RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	
	RequestSpecification req = RestAssured.given();
	
	Response res = req.request(Method.GET,"/Hyderabad");
	
	// Weather API - Extract Values of Each Node
	
		// Import Jsonpath 
	// What we do manually - that is in the chrome browser
	// put the code and find the Json path by clicking on submit button
	// we use JsonPath here which does the same function finds the correct node and path
	JsonPath jsonpathEvaluator = res.jsonPath();
	
	jsonpathEvaluator.get("City");
	System.out.println("City");
	Assert.assertEquals(jsonpathEvaluator.get("City"),"Hyderabad");
	
	System.out.println(jsonpathEvaluator.get("Temperature"));
	System.out.println(jsonpathEvaluator.get("WeatherDescription"));
	System.out.println(jsonpathEvaluator.get("WindSpeed"));
	
	}

}
