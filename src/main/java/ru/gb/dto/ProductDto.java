package ru.gb.dto;

import lombok.Data;
import ru.gb.entity.Product;

@Data
public class ProductDto {
    private long id;
    private int cost;
    private String title;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.cost = product.getCost();
        this.title = product.getTitle();
    }

    public ProductDto() {
    }
}
