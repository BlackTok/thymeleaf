package ru.gb;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryUnits {
    private final SessionFactory factory;

    public SessionFactoryUnits() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();;
    }

    public Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    public void shutdown() {
        if (factory != null)
            factory.close();
    }
}
