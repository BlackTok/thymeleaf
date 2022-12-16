package ru.gb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.dao.UserDao;
import ru.gb.entity.Role;
import ru.gb.entity.Users;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserServiceDetail {
    private final UserDao userDao;

    public Users findByUserMail(String mail) {
        return userDao.findByUserMail(mail);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUserMail(String mail) throws UsernameNotFoundException {
        Users user = findByUserMail(mail);
        return new org.springframework.security.core.userdetails.User(
                user.getMail(), user.getPassword(), mapRolesToAuthorities(user.getRoles())
        );
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
