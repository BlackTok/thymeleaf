package ru.gb.dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.gb.SessionFactoryUnits;
import ru.gb.entity.Role;
import ru.gb.entity.Users;

import java.util.List;

@Repository
//@RequiredArgsConstructor
//@RequiredArgsConstructor
public class UserDao implements IUserDao {
    private SessionFactoryUnits sessionFactoryUnits;

    public UserDao(SessionFactoryUnits sessionFactoryUnits) {
        this.sessionFactoryUnits = sessionFactoryUnits;
    }

    @Override
    public Users findByUserMail(String mail) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            Users user = (Users) session.createQuery("from Users where mail=:mail")
                    .setParameter("mail", mail)
                            .uniqueResultOptional().orElseGet(() -> new UsernameNotFoundException("Mail not found"));
            session.close();

            return user;
        }
    }

    public List<Users> getAllUsers() {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            List<Users> users = session.createQuery("from Users").list();
            session.close();

            return users;
        }
    }

    public void addUser(Users user) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUserById(Long id) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete Users where id=:id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void changeUser(Users user) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }
}
