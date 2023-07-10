package com.example.syncapp.dao.user;

import com.example.syncapp.entity.User;

public interface UserDao {
    User findById(int id);

    User findByEmail(String email);

    User add(User user);

    User update(User user);

    User delete(int id);

}
