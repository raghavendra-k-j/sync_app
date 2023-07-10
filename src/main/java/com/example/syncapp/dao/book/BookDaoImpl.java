package com.example.syncapp.dao.book;

import com.example.syncapp.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDoa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book add(Book book) {
        entityManager.persist(book);
        return find(book.getId());
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public Book find(String id) {
        String query = "FROM Book WHERE id=:id";
        try {
            return entityManager.createQuery(query, Book.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        Book book = find(id);
        if (book != null) {
            entityManager.remove(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByTitle(String title) {
        String query = "SELECT COUNT(b) FROM Book b WHERE b.title = :title";
        Long count = entityManager.createQuery(query, Long.class).setParameter("title", title).getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitleAndNotId(String title, String bookId) {
        String query = "SELECT COUNT(b) FROM Book b WHERE b.title = :title AND b.id <> :bookId";
        Long count = entityManager.createQuery(query, Long.class).setParameter("title", title).setParameter("bookId", bookId).getSingleResult();
        return count > 0;
    }

}
