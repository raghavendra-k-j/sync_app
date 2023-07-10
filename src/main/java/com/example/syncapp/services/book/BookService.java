package com.example.syncapp.services.book;

import com.example.syncapp.entity.Book;
import jakarta.transaction.Transactional;

public interface BookService {

    Book add(Book book);

    Book add(Book book, boolean handleConflicts);

    Book update(Book book);

    boolean delete(String id);

    Book find(String id);

}
