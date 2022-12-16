package ru.gb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserServiceDetail {
    UserDetails loadUserByUserMail(String mail) throws UsernameNotFoundException;
}
