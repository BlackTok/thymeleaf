package ru.gb.controller;


import org.springframework.web.bind.annotation.*;
import ru.gb.converter.ProductConverter;
import ru.gb.dto.ProductDto;
import ru.gb.entity.Product;
import ru.gb.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    ProductConverter productConverter = new ProductConverter();

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getProductsForPage() {
        List<Product> products = productService.getPageOfProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            productDtos.add(productConverter.entityToDto(p));
        }

        return productDtos;
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        product.setPurchasePrice(product.getCost() * 0.8);
        productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public void changeCost(@PathVariable int costDelta, @RequestBody Product product) {
        productService.changeCost(product, costDelta);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
