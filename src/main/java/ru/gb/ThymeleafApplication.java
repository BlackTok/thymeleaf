package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.gb.controller.ProductController;
import ru.gb.dao.ProductDao;
import ru.gb.service.ProductService;

@SpringBootApplication
@ComponentScan({"ru.gb"})
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);

		SessionFactoryUnits sessionFactoryUnits = new SessionFactoryUnits();

		ProductDao productDao = new ProductDao(sessionFactoryUnits);
		ProductService productService = new ProductService(productDao);
		ProductController productController = new ProductController(productService);
	}
}
