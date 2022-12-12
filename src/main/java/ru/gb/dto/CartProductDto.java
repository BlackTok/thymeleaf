package ru.gb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProductDto {
    private Long id;
    private String title;
    private int cost;
    private int count;
    private int price;
}
