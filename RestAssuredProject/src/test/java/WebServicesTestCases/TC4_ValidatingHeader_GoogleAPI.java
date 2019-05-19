package WebServicesTestCases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC4_ValidatingHeader_GoogleAPI 
{
	// Session 14
	@Test
	public void getGoogleHeader()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		
		RequestSpecification req = RestAssured.given();
		
		Response res = req.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s\r\n" + 
				"");
		
		// Capture headers and validate
		String contentType = res.header("Content-Type");
		System.out.println(contentType);
		Assert.assertEquals(contentType,"application/xml; charset=UTF-8");
		
		String contentEncoding = res.header("Content-Encoding");
		System.out.println(contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}

}
