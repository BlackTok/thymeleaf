package ru.gb.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.gb.SessionFactoryUnits;
import ru.gb.entity.Product;

import java.util.Comparator;
import java.util.List;

@Repository
public class ProductDao implements IProductDao {
    SessionFactoryUnits sessionFactoryUnits;

    public ProductDao(SessionFactoryUnits sessionFactoryUnits) {
        this.sessionFactoryUnits = sessionFactoryUnits;
    }

    @Override
    public List<Product> getAllProduct() {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            session.close();
            products.sort(Comparator.comparing(Product::getId));

            return products;
        }
    }

    @Override
    public void addProduct(String title, int cost) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product(title, cost);
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void changeCost(Long id, int delta) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setCost(product.getCost() + delta);
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete Product where id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
