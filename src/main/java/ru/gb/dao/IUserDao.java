package ru.gb.dao;

import ru.gb.entity.Users;

public interface IUserDao {
    Users findByUserMail(String mail);
}
