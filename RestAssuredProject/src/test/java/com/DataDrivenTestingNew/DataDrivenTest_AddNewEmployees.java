package com.DataDrivenTestingNew;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testData.XLUtils;

public class DataDrivenTest_AddNewEmployees 
{
	@Test(dataProvider="empdataprovider")
	void postNewEmployees(String ename,String eage,String esal)
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//Here we created data whihc we can send along with the post request
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name",ename);
		requestParams.put("salary",eage);
		requestParams.put("age",esal);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		//POST REQUEST
		Response response=httpRequest.request(Method.POST,"/create");
		
		
		//capture response body to perform validations
		
		String responseBody=response.getBody().asString();
		
		System.out.println("Response body is:"+ responseBody);
		
		Assert.assertEquals(responseBody.contains(ename),true);
		Assert.assertEquals(responseBody.contains(eage),true);
		Assert.assertEquals(responseBody.contains(esal),true);
		
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException
	{
		//Read data from excel
		String path=System.getProperty("user.dir")+"/src/test/java/DataDrivenTestingNew/empdata.xlsx";
		
		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1",1);
		
		String empdata[][]=new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) // i is 1 since 0 row index is for header values 
		{
			for (int j = 0; j < colcount; j++) 
			{
				empdata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				//so the location of data in excel sheet starts from 1 (row) ,0 (col) - index value 
				//now we need to place this data into a location with 0 (row), 0 (col) - index value
				// therefore we use [i-1] as the data location (removing the header value)
				// is placed into the new location of 0,0 (index value)
				// so the value of i will always be [i-1] for the new location
				// as we dont take the header value
			}

		}
		
		//String empdata[][]={ {"abc123","30000","40"}, {"xyz123","40000","30"}, {"pqr123","80000","50"}};
		
		
		return(empdata);
	}
	
	
}


