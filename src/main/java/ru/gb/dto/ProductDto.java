package ru.gb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.gb.entity.Product;

@Data
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String title;
    private int cost;
}
