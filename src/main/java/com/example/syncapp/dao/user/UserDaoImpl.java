package com.example.syncapp.dao.user;

import com.example.syncapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        try {
            String query = "FROM User WHERE email = :email";
            return entityManager.createQuery(query, User.class).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User add(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }


    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User delete(int id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
        return user;
    }
}
