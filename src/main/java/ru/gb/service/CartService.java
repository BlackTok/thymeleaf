package ru.gb.service;

import org.springframework.stereotype.Service;
import ru.gb.converter.CartConverter;
import ru.gb.dto.CartProductDto;
import ru.gb.dto.ProductDto;
import ru.gb.entity.Cart;
import java.util.List;

@Service
public class CartService {
    private Cart cart;
    private CartConverter cartConverter = new CartConverter();

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public List<CartProductDto> getCartList() {
        return cart.getProducts();
    }

    public void addProduct(ProductDto productDto) {
        List<CartProductDto> cartProducts = getCartList();
        CartProductDto cartProductDto = cartProducts.stream()
                .filter(p -> p.getId().equals(productDto.getId())).findFirst()
                .orElse(null);

        if (cartProducts != null && !cartProducts.isEmpty() && cartProductDto != null) {
            int index = cartProducts.indexOf(cartProductDto);
            cartProductDto.setCount(cartProductDto.getCount() + 1);
            cartProductDto.setPrice(cartProductDto.getCount() * cartProductDto.getCost());
            cartProducts.set(index, cartProductDto);
        } else {
            cartProducts.add(new CartProductDto
                    (productDto.getId(), productDto.getTitle(), productDto.getCost(), 1, productDto.getCost())
            );
        }
    }

    public void deleteProduct(Long id) {
        List<CartProductDto> cartProducts = getCartList();
        CartProductDto cartProductDto = cartProducts.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        if (cartProductDto.getCount() > 1) {
            cartProductDto.setCount(cartProductDto.getCount() - 1);
            cartProductDto.setPrice(cartProductDto.getCost() * cartProductDto.getCount());
        } else {
            cartProducts.remove(cartProductDto);
        }
    }
}
