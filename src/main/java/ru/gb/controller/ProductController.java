package ru.gb.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.entity.Product;
import ru.gb.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void initFactory() {

    }

    @GetMapping("/products")
    public List<Product> getProductsForPage(@RequestParam int pageNumber) {
        return productService.getPageOfProducts(pageNumber, 3);
    }

    @GetMapping("/products/add")
    public void addProduct(@RequestParam String title, @RequestParam int cost) {
        productService.addProduct(title, cost);
    }

    @GetMapping("/products/change")
    public void changeCost(@RequestParam Long id, @RequestParam int delta) {
        productService.changeCost(id, delta);
    }

    @GetMapping("/products/delete")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
}
