package com.myRetail.myRetailAPI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedskyClientService {

    private final String redskyUrl = "https://redsky.target.com/v2/pdp/tcin/";
    private final String redskyUrlExclusion = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

    /*
    * Retrieve product title for a given product from the Redsky api
    *
    * @param productId Id of t
    * @return string of title
    *
    * */
    public String getProductTitleFromRedsky(int productId) throws Exception
    {
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
    *
    * @param productId
    * @param url
    * @param urlExclusion
    *
    *
    * */
    public String createURLFromProductId(int productId, String url, String urlExclusion)
    {
        StringBuilder getURLRequest = new StringBuilder(url);

        getURLRequest.append(productId);
        getURLRequest.append(urlExclusion);

        return getURLRequest.toString();
    }

}
