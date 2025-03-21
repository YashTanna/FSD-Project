package com.example.backend.exception;

public class ProductNotFoundException extends RuntimeException{
    
    String id;

    public ProductNotFoundException(String id){
        super(String.format("Product with id %s is not found", id));
    }

}
