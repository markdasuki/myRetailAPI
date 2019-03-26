package com.myRetail.myRetailAPI;
import com.myRetail.myRetailAPI.exceptions.BadJSONRequestException;
import com.myRetail.myRetailAPI.exceptions.ProductNotFoundException;
import com.myRetail.myRetailAPI.models.Product;
import com.myRetail.myRetailAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**/
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> productGetRequest(@PathVariable("id") int id) throws ProductNotFoundException
    {
        return productService.getProductTitleAndPrice(id);
    }

    /**/
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> productPutRequest(@PathVariable("id") int id, @RequestBody Product productRequest) throws ProductNotFoundException, BadJSONRequestException
    {
        return productService.changeProductPrice(id, productRequest);
    }
}
