package com.heroku.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heroku.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Spring 會自動根據方法名稱產生查詢，例如：
    List<Product> findByNameContaining(String name);
}
