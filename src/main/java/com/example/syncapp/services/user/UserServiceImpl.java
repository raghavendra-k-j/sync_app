package com.example.syncapp.services.user;

import com.example.syncapp.dao.user.UserDao;
import com.example.syncapp.entity.User;
import com.example.syncapp.exceptions.DefaultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User signUp(String email, String password, String name) {
        // Validating name
        if (name == null || name.isEmpty()) {
            throw new DefaultException("Name cannot be null or empty.");
        }
        if (name.length() < 3 || name.length() > 40) {
            throw new DefaultException("Name should be between 3 and 40 characters long.");
        }

        // Validating email
        if (email == null || email.isEmpty()) {
            throw new DefaultException("Email cannot be null or empty.");
        }
        if (email.length() > 60) {
            throw new DefaultException("Email should be at most 60 characters long.");
        }
        if (!email.matches("[^@]+@[^@]+\\.[^@]+")) {
            throw new DefaultException("Invalid email format.");
        }

        // Validating password
        if (password == null || password.isEmpty()) {
            throw new DefaultException("Password cannot be null or empty.");
        }
        if (password.length() < 8 || password.length() > 20) {
            throw new DefaultException("Password should be between 8 and 20 characters long.");
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$")) {
            throw new DefaultException("Password should contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
        }

        User previousUser = userDao.findByEmail(email);
        System.out.println(previousUser);

        if(previousUser != null) {
            throw new DefaultException("Email Already Exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));

        return userDao.add(user);
    }

    @Override
    @Transactional
    public User login(int userId, String password) {
        // Validating email
        if (userId == 0) {
            throw new DefaultException("User id required.");
        }

        // Validating password
        if (password == null || password.isEmpty()) {
            throw new DefaultException("Password cannot be null or empty.");
        }

        User user = userDao.findById(userId);

        // Check if the user exists
        if (user == null) {
            throw new DefaultException("User does not exist.");
        }

        // Check if the password is correct
        if (!user.getPassword().equals(password)) {
            throw new DefaultException("Incorrect password.");
        }

        return user;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
