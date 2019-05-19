package WebServicesTestCases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC5_Printing_AllHeaders 
{
	// Session 14
		@Test
		public void getGoogleHeader()
		{
			RestAssured.baseURI="https://maps.googleapis.com";
			
			RequestSpecification req = RestAssured.given();
			
			Response res = req.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s\r\n" + 
					"");
			
			// Capture details of all the headers 
			// Headers class import from restAssured
			Headers headers = res.headers();
			
			// capturing all the headers and storing them in a for loop
			for (Header header:headers)
			{
				header.getName();
				header.getValue();
				System.out.println(header.getName()+"               "+header.getValue());
			}
			
		}

	}