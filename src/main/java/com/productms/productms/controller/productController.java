package com.productms.productms.controller;

import com.productms.productms.entity.Product;
import com.productms.productms.repository.productRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


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
    public Object listProduct(@PathVariable String id) {
        if (productRepository.findById(id).isEmpty()) {
            logger.debug("Produto: " + id + " não existe");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            logger.debug("Listando produto id: " + id);
            return productRepository.findById(id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(
            @PathVariable(value = "id") String id, @RequestBody @Validated Product product) throws ResourceAccessException
             {
        Product product1 = productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto : "+ id + " não encontrado"));

        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        final Product productUpdate = productRepository.save(product1);

        return ResponseEntity.ok(productUpdate);
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
