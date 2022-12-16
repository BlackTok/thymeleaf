package ru.gb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.gb.dao.RoleDao;
import ru.gb.dao.UserDao;
import ru.gb.entity.Role;
import ru.gb.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DatabaseUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDao.findByUserMail(username);
        List<Role> roles = user.getRoles();
        //List<Role> roles = roleDao.getUserRolesByUserId(user.getId());
        UserDetails userDetails = User.builder()
                .username(user.getMail())
                .password(user.getPassword())
                .authorities(mapRolesToAuthorities(roles))
                .build();

        return userDetails;
    }

    public List<Users> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(Users user) {
        userDao.addUser(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUserById(id);
    }

    public void changeRole(Users user, Long roleId, Boolean add) {
        List<Role> roles = user.getRoles();
        if (add) {
            roles.add(roleDao.getRoleById(roleId));
        } else {
            roles.removeIf(r -> r.getId().equals(roleId));
        }
        user.setRoles(roles);

        userDao.changeUser(user);
    }
}
