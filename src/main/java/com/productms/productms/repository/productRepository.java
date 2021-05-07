package com.productms.productms.repository;

import com.productms.productms.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends MongoRepository<Product, String> {


    @Query("{$or: [{ 'price': { $gte: ?0}},{'price':{$lte: ?1} },{'description':{$eq: ?2}}, {'name':{$eq: ?2}} ]}")
    public List<Product> findBySearchQuery(Double min_price, Double max_price, String q);

}
