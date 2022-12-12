package ru.gb.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.gb.dto.CartProductDto;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Cart {
    private List<CartProductDto> products = new ArrayList<>();
}
