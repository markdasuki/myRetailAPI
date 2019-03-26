package com.myRetail.myRetailAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MyRetailAPITestHelper {

    public static void validGet(String expectedResponse, String url) throws Exception
    {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;

        //Perform get request
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        root = mapper.readTree(response.getBody());

        assertThat(response.getStatusCode().value() == 200);
        JSONAssert.assertEquals(root.toString(), expectedResponse, JSONCompareMode.STRICT);
    }

    public static void invalidGet(String url, int statusCode) throws Exception
    {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        //Perform get request
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode().value() == statusCode);
    }

    public static void invalidPut(String requestJson, String url, int statusCode)
    {
        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

        assertThat(response.getStatusCodeValue() == statusCode);
    }

    public static void validPut(String requestJson, String url)
    {
        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

        assertThat(response.getStatusCodeValue() == 200);
    }
}
