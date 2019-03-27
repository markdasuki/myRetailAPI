package com.myRetail.myRetailAPI;
import com.myRetail.myRetailAPI.exceptions.BadJSONRequestException;
import com.myRetail.myRetailAPI.exceptions.ProductNotFoundException;
import com.myRetail.myRetailAPI.models.Product;
import com.myRetail.myRetailAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* Controller for HTTP requests for myRetail
* */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    *
    * Handles HTTP GET Request at /products/{id}
    * @param id The product id for the GET request to retrieve a products data
    * @return ResponseEntity with product data as a JSON
    *
    * */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> productGetRequest(@PathVariable("id") int id) throws ProductNotFoundException {
        return productService.getProductTitleAndPrice(id);
    }

    /*
    *
    * Handles HTTP Put Request at /products/{id}
    * @param id the product id for the PUT request to modify product price
    * @param productRequest The JSON request body to modify the product price
    * @return ResponseEntity with status of price update
    *
    * */
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> productPutRequest(@PathVariable("id") int id, @RequestBody Product productPriceChange) throws ProductNotFoundException, BadJSONRequestException {
        return productService.changeProductPrice(id, productPriceChange);
    }
}
