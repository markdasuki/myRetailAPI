package com.myRetail.myRetailAPI.repositories;
import com.myRetail.myRetailAPI.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductPriceRepository extends MongoRepository<Product, String> {
    Product findProductBy_id(int _id);
}
