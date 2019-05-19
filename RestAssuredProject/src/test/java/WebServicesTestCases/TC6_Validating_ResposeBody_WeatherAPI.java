package WebServicesTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class TC6_Validating_ResposeBody_WeatherAPI 
{
			// Session 14
			@Test
			public void ValidatingResponseBody()
			{
					RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	
					RequestSpecification req = RestAssured.given();
					
					Response res = req.request(Method.GET,"/Delhi");
					
					// Getting response body
					// ResponseBody is a class to be imported
					ResponseBody body =res.getBody();
					
					String bodyasString = body.asString();
					System.out.println(bodyasString);
					
					Assert.assertEquals(bodyasString.contains("Delhi"),true);
							//Validates weather Hyderbad is present in the body or not
					
			}
	
}
