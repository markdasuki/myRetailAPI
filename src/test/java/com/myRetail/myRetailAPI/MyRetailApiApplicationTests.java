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

	/*
	* Verify GET request in a product that exists in redsky api and the data store
	* */
	@Test
	public void getProductInRedskyAndDatastore() throws Exception {
		String expected = "{\"_id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
		String url = "http://localhost:8080/products/13860428";
		validGet(expected, url);
	}

	/*
	* Verify GET request fails when product does not exist in data store but exists in the redsky api
	* */
	@Test
	public void getProductInRedskyNotInDatastore () {
		//Product ID 13860429 exists in Redsky api
		String url = "http://localhost:8080/products/13860429";
		invalidGet(url, 404);
	}

	/*
	 * Verify GET request fails when product does not exist in redsky api
	 * */
	@Test
	public void getProductNotInRedsky() {
		String url = "http://localhost:8080/products/13860427";
		invalidGet(url, 404);
	}

	//Put Request Tests

	/*
	 * Verify PUT request can modify price value
	 * */
	@Test
	public void putProductModifyValue() throws Exception {
		String requestJson = "{\"_id\":13860428,\"current_price\":{\"value\":50.99,\"currency_code\":\"USD\"}}";
		String expected = "{\"_id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":50.99,\"currency_code\":\"USD\"}}";

		String url ="http://localhost:8080/products/13860428";
		String revertPrice = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";

		validPut(requestJson, url);
		validGet(expected, url);
		validPut(revertPrice, url);
	}

	/*
	 * Verify PUT request can modify currency code
	 * */
	@Test
	public void putProductModifyCurrencyCode() throws Exception{
		String requestJson = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"CAD\"}}";
		String expected = "{\"_id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":13.49,\"currency_code\":\"CAD\"}}";

		String url ="http://localhost:8080/products/13860428";
		String revertPrice = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";

		validPut(requestJson, url);
		validGet(expected, url);
		validPut(revertPrice, url);
	}

	/*
	 * Verify PUT request can modify price value and currency code
	 * */
	@Test
	public void putProductModifyCurrencyCodeAndValue() throws Exception {
		String requestJson = "{\"_id\":13860428,\"current_price\":{\"value\":16.33,\"currency_code\":\"CAD\"}}";
		String expected = "{\"_id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":16.33,\"currency_code\":\"CAD\"}}";

		String url ="http://localhost:8080/products/13860428";
		String revertPrice = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";

		validPut(requestJson, url);
		validGet(expected, url);
		validPut(revertPrice, url);
	}

	/*
	 * Verify PUT request on a product price that is not in the data store fails
	 * */
	@Test
	public void putProductNotInDatastore() {
		String requestJson = "{\"_id\":13860429,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
		String url = "http://localhost:8080/products/138604279";
		invalidPut(requestJson, url, 400);
	}

	/*
	 * Verify PUT request with a mismatched id fails
	 * */
	@Test
	public void putProductIdMismatch() {
		String requestJson = "{\"_id\":13860428,\"current_price\":{\"value\":13.49,\"currency_code\":\"USD\"}}";
		String url = "http://localhost:8080/products/138604279";
		invalidPut(requestJson, url, 400);
	}

	/*
	 * Verify PUT request with an empty JSON request fails
	 * */
	@Test
	public void putProductEmptyJSONRequest() {
		String requestJson = "";
		String url = "http://localhost:8080/products/138604278";
		invalidPut(requestJson, url, 400);
	}

	/*
	 * Verify PUT review with the current price missing fails
	 * */
	@Test
	public void putProductMissingCurrentPrice() {
		String requestJson = "{\"_id\": 13860427}";
		String url = "http://localhost:8080/products/138604278";
		invalidPut(requestJson, url, 400);
	}
}
