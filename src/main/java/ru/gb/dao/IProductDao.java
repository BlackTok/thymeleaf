package ru.gb.dao;

import ru.gb.entity.Product;

import java.util.List;

public interface IProductDao {
    List<Product> getAllProduct();

    void addProduct(String title, int cost);

    void changeCost(Long id, int delta);

    void deleteProduct(Long id);
}
