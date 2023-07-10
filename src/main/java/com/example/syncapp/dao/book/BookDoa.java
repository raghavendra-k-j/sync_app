package com.example.syncapp.dao.book;

import com.example.syncapp.entity.Book;

public interface BookDoa {

    Book add(Book book);

    Book update(Book book);

    Book find(String id);

    boolean delete(String id);

    boolean existsByTitle(String title);

    boolean existsByTitleAndNotId(String title, String bookId);
}
