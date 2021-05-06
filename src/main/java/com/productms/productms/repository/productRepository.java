package com.productms.productms.repository;

import com.productms.productms.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends MongoRepository<Product, String> {

}
