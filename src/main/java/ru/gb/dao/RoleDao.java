package ru.gb.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.gb.SessionFactoryUnits;
import ru.gb.entity.Role;
import ru.gb.entity.Users;

import java.util.List;

@Repository
public class RoleDao {
    private SessionFactoryUnits sessionFactoryUnits;

    public RoleDao(SessionFactoryUnits sessionFactoryUnits) {
        this.sessionFactoryUnits = sessionFactoryUnits;
    }

    public List<Role> getUserRolesByUserId(Long id) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            List<Role> roles = session.createQuery("from Role").getResultList();
            session.close();

            return roles;
        }
    }

    public Role getRoleById(Long id) {
        try (Session session = sessionFactoryUnits.getCurrentSession()) {
            session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.close();

            return role;
        }
    }
}
