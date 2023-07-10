package com.example.syncapp.services.user;

import com.example.syncapp.entity.User;
import org.springframework.stereotype.Service;
public interface UserService {
    User signUp(String email, String password, String name);

    User login(int userId, String password);

    User findById(int id);

    User findByEmail(String email);
}
