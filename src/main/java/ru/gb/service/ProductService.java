package ru.gb.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.dao.ProductDao;
import ru.gb.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getPageOfProducts(int numberPage, int countOnPage) {
        List<Product> allProducts = productDao.getAllProduct();
        List<Product> productsForPage = new ArrayList<>();
        int lastProduct = numberPage * countOnPage;
        int firstProduct = numberPage * countOnPage - countOnPage;
        if (lastProduct > allProducts.size())
            lastProduct = allProducts.size();

        for (int i = firstProduct; i < lastProduct; i++) {
            productsForPage.add(allProducts.get(i));
        }

        return productsForPage;
    }

    public void addProduct(String title, int cost) {
        productDao.addProduct(title, cost);
    }

    public void changeCost(Long id, int delta) {
        productDao.changeCost(id, delta);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
