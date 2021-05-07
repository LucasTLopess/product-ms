package com.productms.productms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Document(collection = "product")
public class Product {

    @Id
    private String id;

    @NotNull(message = "Deve conter nome do produto")
    private String name;

    @NotNull(message = "Deve conter alguma breve descrição do produto")
    private String description;

    @NotNull(message = "Preço do produto deve ser algum valor positivo")
    @Min(0)
    private Double price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
