package com.productms.productms.service;

import com.productms.productms.entity.Product;
import com.productms.productms.repository.productRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class productService {

    Logger logger = LoggerFactory.getLogger(productService.class);

    @Autowired
    productRepository productRepository;

    public List<Product> findAll() {
        logger.debug("Buscando lista de clientes no DB");
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Object findById(String id) {
        if (productRepository.findById(id).isEmpty()) {
            logger.debug("Produto: " + id + " não existe");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            logger.debug("Listando produto id: " + id);
            return productRepository.findById(id);
        }
    }

    public Product update(String id, Product product) {
        Product product1 = productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto : "+ id + " não encontrado"));

        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        final Product productUpdate = productRepository.save(product1);

        return productUpdate;
    }

    public ResponseEntity<Product> delete(String id) {
        if (productRepository.findById(id).isEmpty()) {
            logger.debug("Produto: " + id + " não existe");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            logger.debug("Listando produto id: " + id);
            productRepository.deleteById(id);
            return new ResponseEntity<Product>(HttpStatus.OK);
        }
    }

    public List<Product> findBySearch(Double min_price, Double max_price, String q) {
        return productRepository.findBySearchQuery(min_price,max_price,q);
    }
}
