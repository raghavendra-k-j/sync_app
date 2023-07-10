package com.example.syncapp.services.book;

import com.example.syncapp.dao.book.BookDoa;
import com.example.syncapp.dao.user.UserDao;
import com.example.syncapp.entity.Book;
import com.example.syncapp.entity.User;
import com.example.syncapp.exceptions.DefaultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDoa bookDoa;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Book add(Book book) {
        if (book.getId() == null || book.getId().isEmpty()) {
            throw new DefaultException("Book ID is required");
        }

        Book previousBook = find(book.getId());
        if (previousBook != null) {
            throw new DefaultException("A book with the same ID already exists", false);
        }

        if (book.getUserId() <= 0) {
            throw new DefaultException("User ID must be a positive number");
        }

        User user = userDao.findById(book.getUserId());
        if(user == null) {
            throw new DefaultException("Invalid User Id");
        }

        String title = book.getTitle();
        if (title == null || title.isEmpty()) {
            throw new DefaultException("Book title is required");
        }

        if (title.length() > 255) {
            throw new DefaultException("Book title cannot exceed 255 characters");
        }


        String author = book.getAuthor();
        if (author == null || author.isEmpty()) {
            throw new DefaultException("Book author is required");
        }

        if (author.length() > 40) {
            throw new DefaultException("Book author cannot exceed 40 characters");
        }

        // Check if the title is unique
        if (bookDoa.existsByTitle(title)) {
            throw new DefaultException("Book with the same title already exists", true);
        }

        String content = book.getContent();
        if (content == null || content.isEmpty()) {
            throw new DefaultException("Book content is required");
        }

        return bookDoa.add(book);
    }

    @Override
    @Transactional
    public Book add(Book book, boolean handleConflicts) {
        if (book.getId() == null || book.getId().isEmpty()) {
            throw new DefaultException("Book ID is required");
        }

        if (book.getUserId() <= 0) {
            throw new DefaultException("User ID must be a positive number");
        }

        Book previousBook = find(book.getId());
        if (previousBook != null) {
            if(handleConflicts) {
                return update(book);
            }
            else {
                throw new DefaultException("A book with the same ID already exists", false);
            }
        }


        User user = userDao.findById(book.getUserId());
        if(user == null) {
            throw new DefaultException("Invalid User Id");
        }

        String title = book.getTitle();
        if (title == null || title.isEmpty()) {
            throw new DefaultException("Book title is required");
        }

        if (title.length() > 255) {
            throw new DefaultException("Book title cannot exceed 255 characters");
        }

        String author = book.getAuthor();
        if (author == null || author.isEmpty()) {
            throw new DefaultException("Book author is required");
        }

        if (author.length() > 40) {
            throw new DefaultException("Book author cannot exceed 40 characters");
        }

        // Check if the title is unique
        if (bookDoa.existsByTitle(title)) {
            throw new DefaultException("Book with the same title already exists", true);
        }

        String content = book.getContent();
        if (content == null || content.isEmpty()) {
            throw new DefaultException("Book content is required");
        }

        return bookDoa.add(book);
    }

    @Override
    @Transactional
    public Book update(Book book) {
        System.out.println(book);
        if (book.getId() == null || book.getId().isEmpty()) {
            throw new DefaultException("Book ID is required");
        }

        Book existingBook = find(book.getId());
        System.out.println(existingBook);
        if (existingBook == null) {
            throw new DefaultException("Book with the given ID does not exist", false);
        }

        if (book.getUserId() <= 0) {
            throw new DefaultException("User ID must be a positive number");
        }

        User user = userDao.findById(book.getUserId());
        if (user == null) {
            throw new DefaultException("Invalid User ID");
        }

        String title = book.getTitle();
        if (title == null || title.isEmpty()) {
            throw new DefaultException("Book title is required");
        }

        if (title.length() > 255) {
            throw new DefaultException("Book title cannot exceed 255 characters");
        }

        String author = book.getAuthor();
        if (author == null || author.isEmpty()) {
            throw new DefaultException("Book author is required");
        }

        if (author.length() > 40) {
            throw new DefaultException("Book author cannot exceed 40 characters");
        }

        // Check if the title is unique excluding the current book being updated
        if (bookDoa.existsByTitleAndNotId(title, book.getId())) {
            throw new DefaultException("Book with the same title already exists", true);
        }

        String content = book.getContent();
        if (content == null || content.isEmpty()) {
            throw new DefaultException("Book content is required");
        }

        return bookDoa.update(book);
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new DefaultException("Book ID is required");
        }

        Book existingBook = find(id);
        if (existingBook == null) {
            throw new DefaultException("Book with the given ID: " + id +" does not exist");
        }

        return bookDoa.delete(existingBook.getId());
    }

    @Override
    public Book find(String id) {
        if (id == null || id.isEmpty()) {
            throw new DefaultException("Book ID is required");
        }
        return bookDoa.find(id);
    }
}
