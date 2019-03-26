package com.myRetail.myRetailAPI.service;

import com.myRetail.myRetailAPI.exceptions.BadJSONRequestException;
import com.myRetail.myRetailAPI.exceptions.ProductNotFoundException;
import com.myRetail.myRetailAPI.models.Product;
import com.myRetail.myRetailAPI.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedskyClientService redskyClientService;

    /*
    * Service to retrieve title from redsky api and price from mongoDB data store and return as JSON
    *
    * @param
    * @return
    * @throws
    *
    * */
    public ResponseEntity<Product> getProductTitleAndPrice(int id) throws ProductNotFoundException
    {
        String productTitle;
        Product productInfoDatastore;

        try{
            productTitle = redskyClientService.getProductTitleFromRedsky(id);
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException("Product not found from redsky API for product id: " + id);
        }

        productInfoDatastore = productRepository.findProductBy_id(id);

        if(productInfoDatastore == null)
        {
            throw new ProductNotFoundException("Product Price not found in data store for product id: " + id);
        }

        productInfoDatastore.setName(productTitle);

        return new ResponseEntity<>(productInfoDatastore, HttpStatus.OK);
    }

    /*
    * Service to change the current price of a product in the mongoDB data store
    *
    * @param
    * @param
    * @return
    * @throws
    * @throws
    *
    * */
    public ResponseEntity<String> changeProductPrice(int id, Product productRequest) throws ProductNotFoundException, BadJSONRequestException
    {
        Product productFromDataStore;

        if(productRequest.getCurrent_price() == null)
        {
            throw new BadJSONRequestException("Current Price does not exist");
        }

        if(productRequest.get_id() !=  id)
        {
            throw new BadJSONRequestException("Bad JSON Request: URL id is: " + id + " JSON request id is: " + productRequest.get_id());
        }

        productFromDataStore = productRepository.findProductBy_id(id);

        if(productFromDataStore == null)
        {
            throw new ProductNotFoundException("Product Price not found in data store for product id: " + id);
        }

        productFromDataStore.setCurrent_price(productRequest.getCurrent_price());

        productRepository.save(productFromDataStore);

        return new ResponseEntity<>("Product update request into data store successful! ", HttpStatus.OK);
    }
}
