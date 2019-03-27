package com.myRetail.myRetailAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.myRetail.myRetailAPI.MyRetailAPITestHelper.*;

/*
* Functional Tests to test myRetail RESTful Service
* */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRetailApiApplicationTests {

	//GET Request Tests

	@Test
	public void getProductInRedskyAndDatastore() throws Exception
	{
		String expected = "{\"_id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
		String url = "http://localhost:8080/products/13860428";
		validGet(expected, url);
	}

	@Test
	public void getProductInRedskyNotInDatastore () throws Exception
	{
		//Product ID 13860429 exists in Redsky api
		String url = "http://localhost:8080/products/13860429";
		invalidGet(url, 404);
	}

	@Test
	public void getProductNotInRedsky() throws Exception
	{
		String url = "http://localhost:8080/products/13860427";
		invalidGet(url, 404);
	}

	//Put Request Tests

	@Test
	public void putProductValidJSONRequest() throws Exception
	{
		String requestJson = "{\"_id\":13860428,\"current_price\":{\"value\":50.99,\"currency_code\":\"USD\"}}";
		String expected = "{\"_id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":50.99,\"currency_code\":\"USD\"}}";
		String url ="http://localhost:8080/products/13860428";
		String revertPrice = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";

		validPut(requestJson, url);
		validGet(expected, url);
		validPut(revertPrice, url);
	}

	@Test
	public void putProductNotInDatastore() throws Exception
	{
		String requestJson = "{\"_id\":13860429,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
		String url = "http://localhost:8080/products/138604279";
		invalidPut(requestJson, url, 400);
	}

	@Test
	public void putProductIdMismatch() throws Exception
	{
		String requestJson = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
		String url = "http://localhost:8080/products/138604279";
		invalidPut(requestJson, url, 400);
	}

	@Test
	public void putProductEmptyJSONRequest() throws Exception
	{
		String requestJson = "";
		String url = "http://localhost:8080/products/138604278";
		invalidPut(requestJson, url, 400);
	}

	@Test
	public void putProductMissingCurrentPrice() throws Exception
	{
		String requestJson = "{\"_id\": 13860427}";
		String url = "http://localhost:8080/products/138604278";
		invalidPut(requestJson, url, 400);
	}
}
