package com.myRetail.myRetailAPI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(RedskyClientService.class);

    /*
    * Retrieve product title for a given product from the Redsky api
    *
    * @param productId The id of the product that is requesting the title
    * @return string of title
    *
    * */
    public String getProductTitleFromRedsky(int productId) throws Exception {
        logger.info("getProductTitleFromRedsky");
        String productTitle;
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;

        String urlFromProductId =  createURLFromProductId(productId, redskyUrl, redskyUrlExclusion);

        //Perform get request
        ResponseEntity<String> response = restTemplate.getForEntity(urlFromProductId, String.class);
        root = mapper.readTree(response.getBody());

        //Retrieve title value from
        productTitle = root.get("product").get("item").get("product_description").get("title").textValue();

        return productTitle;

    }

    /*
    * Creates a URL to perform the GET request for a product id
    *
    * @param productId The product id that will be attached to the URL
    * @param url The url that needs to be modified
    * @param urlExclusion Exclusions from the api request
    *
    *
    * */
    public String createURLFromProductId(int productId, String url, String urlExclusion) {
        StringBuilder getURLRequest = new StringBuilder(url);

        getURLRequest.append(productId);
        getURLRequest.append(urlExclusion);

        return getURLRequest.toString();
    }

}
