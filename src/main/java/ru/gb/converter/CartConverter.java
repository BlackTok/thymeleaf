package ru.gb.converter;

import org.springframework.stereotype.Component;
import ru.gb.dto.CartProductDto;
import ru.gb.dto.ProductDto;
import ru.gb.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartConverter {
    public ProductDto CartProductDtoToProductDto(CartProductDto cartProductDto) {
        return new ProductDto(cartProductDto.getId(), cartProductDto.getTitle(), cartProductDto.getCost());
    }

    public CartProductDto ProductDtoToCartProductDto(ProductDto productDto, int count) {
        return new CartProductDto
                (productDto.getId(), productDto.getTitle(), productDto.getCost(),
                        count, productDto.getCost() * count);
    }
}
