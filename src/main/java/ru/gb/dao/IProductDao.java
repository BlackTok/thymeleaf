package ru.gb.dao;

import ru.gb.entity.Product;

import java.util.List;

public interface IProductDao {
    List<Product> getAllProduct();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);
}
