package com.productms.productms.controller;

import com.productms.productms.entity.Product;
import com.productms.productms.repository.productRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/products")
public class productController {

    Logger logger = LoggerFactory.getLogger(productController.class);

    @Autowired
    public productRepository productRepository;

    @GetMapping
    public ResponseEntity<?> listing() {
        logger.debug("Acessando serviço para listar todos os clientes");
        List<Product> list = productRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        logger.debug("Criando novo produto");
        return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Object listOne(@PathVariable String id) {
        if (productRepository.findById(id).isEmpty()) {
            logger.debug("Produto: " + id + " não existe");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            logger.debug("Listando produto id: " + id);
            return productRepository.findById(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        if (productRepository.findById(id).isEmpty()) {
            logger.debug("Produto: " + id + " não existe");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            logger.debug("Listando produto id: " + id);
            productRepository.deleteById(id);
            return new ResponseEntity<Product>(HttpStatus.OK);
        }
    }



}
