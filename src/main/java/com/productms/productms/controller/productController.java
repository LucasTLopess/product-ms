package com.productms.productms.controller;

import com.productms.productms.entity.Product;
import com.productms.productms.service.productService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController("/products")
public class productController {

    Logger logger = LoggerFactory.getLogger(productController.class);

    @Autowired
    public productService productService;

    @GetMapping
    public List<Product> listing() {
        logger.debug("Acessando serviço para listar todos os clientes");
        return productService.findAll();
    }

    @PostMapping
    public Product create(@RequestBody @Valid Product product) {
        logger.debug("Acessando serviço para cadastrar novo produto");
        return productService.create(product);
    }

    @GetMapping("/{id}")
    public Object listProductbyId(@PathVariable String id) {
        logger.debug("Acessando serviço para visualizar produto id: " + id);
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(@Valid @PathVariable(value = "id") String id, @RequestBody Product product) {
        logger.debug("Acessando serviço para atualizar produto id: " + id);
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        logger.debug("Acessando serviço para deletar  produto id: " + id);
        productService.delete(id);
    }

    @GetMapping("/search")
    public List<Product> listingBySearch(@RequestParam(name = "q") String q,
                                         @RequestParam(name = "min_price") Double min_price,
                                         @RequestParam(name = "max_price") Double max_price) {
        logger.debug("Acessando serviço para listar todos os clientes com base nos parametros");

        return productService.findBySearch(q,min_price,max_price);
    }


}
