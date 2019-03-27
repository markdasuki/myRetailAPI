package com.myRetail.myRetailAPI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
* Manages services to the external redsky api
* */
@Service
public class RedskyClientService {

    private final String redskyUrl = "https://redsky.target.com/v2/pdp/tcin/";
    private final String redskyUrlExclusion = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

    /*
    * Retrieve product title for a given product from the Redsky api
    *
    * @param productId The id of the product that is requesting the title
    * @return string of product title
    *
    * */
    public String getProductTitleFromRedsky(int productId) throws Exception {
        String productTitle;
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse;

        String urlFromProductId =  createURLFromProductId(productId, redskyUrl, redskyUrlExclusion);

        //Perform get request
        ResponseEntity<String> getResponse = restTemplate.getForEntity(urlFromProductId, String.class);
        jsonResponse = mapper.readTree(getResponse.getBody());

        //Retrieve title value from JSON
        productTitle = jsonResponse.get("product")
                           .get("item")
                           .get("product_description")
                           .get("title")
                           .textValue();

        return productTitle;
    }

    /*
    * Creates URL to perform the GET request for a product id
    *
    * @param productId The product id that will be attached to the URL
    * @param url The url that needs to be modified
    * @param urlExclusion Exclusions from the api request
    * @return string of URL
    *
    * */
    public String createURLFromProductId(int productId, String url, String urlExclusion) {
        StringBuilder getURLRequest = new StringBuilder(url);

        getURLRequest.append(productId);
        getURLRequest.append(urlExclusion);

        return getURLRequest.toString();
    }

}
