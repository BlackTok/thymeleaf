package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.gb.controller.ProductController;
import ru.gb.converter.ProductConverter;
import ru.gb.dao.ProductDao;
import ru.gb.dao.RoleDao;
import ru.gb.dao.UserDao;
import ru.gb.entity.Cart;
import ru.gb.service.CartService;
import ru.gb.service.DatabaseUserDetailService;
import ru.gb.service.ProductService;

@SpringBootApplication
@ComponentScan({"ru.gb"})
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);

		SessionFactoryUnits sessionFactoryUnits = new SessionFactoryUnits();

		ProductDao productDao = new ProductDao(sessionFactoryUnits);
		ProductConverter productConverter = new ProductConverter();
		ProductService productService = new ProductService(productDao, productConverter);
		ProductController productController = new ProductController(productService);

		UserDao userDao = new UserDao(sessionFactoryUnits);
		RoleDao roleDao = new RoleDao(sessionFactoryUnits);

		Cart cart = new Cart();
		CartService cartService = new CartService(cart);
	}
}
