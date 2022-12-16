package ru.gb.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.converter.ProductConverter;
import ru.gb.dao.ProductDao;
import ru.gb.dto.ProductDto;
import ru.gb.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;
    private ProductConverter productConverter;

    public ProductService(ProductDao productDao, ProductConverter productConverter) {
        this.productDao = productDao;
        this.productConverter = productConverter;
    }

    public List<Product> getPageOfProducts() {
        return productDao.getAllProduct();
    }

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public void changeCost(Product product, int delta) {
        product.setCost(product.getCost() + delta);
        productDao.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
