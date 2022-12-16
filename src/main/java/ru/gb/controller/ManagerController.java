package ru.gb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Product;
import ru.gb.service.ProductService;

import java.util.List;

@RestController
public class ManagerController {
    private final ProductService productService;

    public ManagerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/manage")
    public List<Product> getProductList() {
        return productService.getPageOfProducts();
    }

    @PostMapping("/manage")
    public ResponseEntity addProduct(@RequestBody Product product) {
        productService.addProduct(product);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/manage/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/manage/{id}")
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable int id) {
        productService.changeCost(product, id);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
