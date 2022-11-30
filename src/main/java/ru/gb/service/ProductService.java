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
