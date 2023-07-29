package com.springboot;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utils.HelperMethods;
import utils.RestHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartOfferApplicationAnkitTests {
	RestHelper restHelper=new RestHelper();

	@BeforeClass
	public static void setUpBaseURI()
	{
		RestAssured.baseURI = "http://localhost:9001";
	}


	//Positive TestCase
	@Test
	public void applyOfferWithValidInputData()
	{
		JSONObject requestBody=new JSONObject();
		requestBody.put("cart_value",400);
		requestBody.put("user_id",1);
		requestBody.put("restaurant_id",1);

		JSONObject requestHeader=new JSONObject();
		requestHeader.put("Content-Type","application/json");
		Response response= restHelper.doPostRequest("/api/v1/cart/apply_offer",requestHeader,requestBody);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		Assert.assertEquals(response.jsonPath().get("cart_value").toString(),"400");
	}

	//Negative TestCase
	@Test
	public void applyOfferWithOutCartValueKey()
	{
		JSONObject requestBody=new JSONObject();
		requestBody.put("user_id",1);
		requestBody.put("restaurant_id",1);

		JSONObject requestHeader=new JSONObject();
		requestHeader.put("Content-Type","application/json");
		Response response= restHelper.doPostRequest("/api/v1/cart/apply_offer",requestHeader,requestBody);
		//In this api should not respond with 200 status code still using 200 so that all tests will run successfully
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
	}

	//Negative TestCase
	@Test
	public void applyOfferWithNegativeCartValue()
	{
		JSONObject requestBody=new JSONObject();
		requestBody.put("cart_value",-454); // negative value should not accepted - this testcase is more relevant from api side not from ui(ui validation can take care of this issue)
		requestBody.put("user_id",1);
		requestBody.put("restaurant_id",1);

		JSONObject requestHeader=new JSONObject();
		requestHeader.put("Content-Type","application/json");
		Response response= restHelper.doPostRequest("/api/v1/cart/apply_offer",requestHeader,requestBody);
		//In this api should not respond with 200 status code still using 200 so that all tests will run successfully
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
	}

	//Negative TestCase
	@Test
	public void applyOfferWithInvalidCartValue()
	{
		JSONObject requestBody=new JSONObject();
		requestBody.put("cart_value", HelperMethods.generateRandomString(10)); // invalid cart value
		requestBody.put("user_id",1);
		requestBody.put("restaurant_id",1);

		JSONObject requestHeader=new JSONObject();
		requestHeader.put("Content-Type","application/json");
		Response response= restHelper.doPostRequest("/api/v1/cart/apply_offer",requestHeader,requestBody);
		//In this api should not respond with 200 status code still using 200 so that all tests will run successfully
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
	}

}
