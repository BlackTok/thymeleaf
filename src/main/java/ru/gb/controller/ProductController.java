package ru.gb.controller;


import org.springframework.web.bind.annotation.*;
import ru.gb.dto.ProductDto;
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
    public List<ProductDto> getProductsForPage(@RequestParam int pageNumber) {
        return productService.getPageOfProducts(pageNumber, 3).
                stream().map(p -> new ProductDto(p)).toList();
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
