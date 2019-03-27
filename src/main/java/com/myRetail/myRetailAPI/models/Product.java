package com.myRetail.myRetailAPI.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

/*
* Model for myRetail product
* */
@Document(collection="products")
public class Product {
    @Id
    @Field("_id")
    private int _id;

    private String name;

    @Field("current_price")
    private Map current_price;

    public Product(){}

    public Product(int _id, String name, Map current_price)
    {
        this._id = _id;
        this.name = name;
        this.current_price=current_price;
    }

    /*
    * Constructor for creating a product with no name
    * */
    public Product(int _id, Map current_price)
    {
        this._id = _id;
        this.current_price=current_price;
    }

    public int get_id()
    {
        return _id;
    }
    public void set_id(int _id)
    {
        this._id=_id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public Map getCurrent_price()
    {
        return current_price;
    }

    public void setCurrent_price(Map current_price)
    {
        this.current_price = current_price;
    }


}


