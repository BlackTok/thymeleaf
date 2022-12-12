package ru.gb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.converter.CartConverter;
import ru.gb.dto.CartProductDto;
import ru.gb.dto.ProductDto;
import ru.gb.service.CartService;
import java.util.List;

@RestController
public class CartController {
    private final CartService cartService;
    private CartConverter cartConverter = new CartConverter();

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public List<CartProductDto> getCart() {
        List<CartProductDto> cartProducts = cartService.getCartList();

        return cartProducts;
    }

    @PostMapping("/cart")
    public ResponseEntity addProductToCart(@RequestBody ProductDto productDto) {
        cartService.addProduct(productDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteProduct(@PathVariable Long id) {
        cartService.deleteProduct(id);
    }
}
