package ru.gb.converter;

import org.springframework.stereotype.Component;
import ru.gb.dto.ProductDto;
import ru.gb.entity.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getTitle(), productDto.getCost(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
