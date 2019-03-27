package com.myRetail.myRetailAPI.service;

import com.myRetail.myRetailAPI.exceptions.BadJSONRequestException;
import com.myRetail.myRetailAPI.exceptions.ProductNotFoundException;
import com.myRetail.myRetailAPI.models.Product;
import com.myRetail.myRetailAPI.repositories.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/*
*  Manages services between external red sky api and product price data store
* */
@Service
public class ProductService {

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private RedskyClientService redskyClientService;

    /*
    * Service to retrieve title from redsky api and price from the product price data store and return as JSON
    *
    * @param id Product id of product
    * @return ResponseEntity with product data as a JSON
    *
    * */
    public ResponseEntity<Product> getProductTitleAndPrice(int id) throws ProductNotFoundException
    {
        String productTitle;
        Product productFromDataStore;

        try{
            productTitle = redskyClientService.getProductTitleFromRedsky(id);
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException("Product not found from redsky API for product id: " + id);
        }

        productFromDataStore = productPriceRepository.findProductBy_id(id);

        if(productFromDataStore == null)
        {
            throw new ProductNotFoundException("Product Price not found in data store for product id: " + id);
        }

        productFromDataStore.setName(productTitle);

        return new ResponseEntity<>(productFromDataStore, HttpStatus.OK);
    }

    /*
    * Service to change the current price of a product in the product price data store
    *
    * @param id
    * @param Product
    * @return
    *
    * */
    public ResponseEntity<String> changeProductPrice(int id, Product priceChange) throws ProductNotFoundException, BadJSONRequestException
    {
        Product productFromDataStore;

        if(priceChange.getCurrent_price() == null)
        {
            throw new BadJSONRequestException("Bad JSON request, no product price to update");
        }

        if(priceChange.get_id() !=  id)
        {
            throw new BadJSONRequestException("Bad JSON request: URL id is: " + id + ", JSON request id is: " + priceChange.get_id());
        }

        productFromDataStore = productPriceRepository.findProductBy_id(id);

        if(productFromDataStore == null)
        {
            throw new ProductNotFoundException("Product Price not found in data store for product id: " + id);
        }

        productFromDataStore.setCurrent_price(priceChange.getCurrent_price());

        productPriceRepository.save(productFromDataStore);

        return new ResponseEntity<>("Product update request into data store successful! ", HttpStatus.OK);
    }
}
