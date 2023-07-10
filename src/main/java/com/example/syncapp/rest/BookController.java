package com.example.syncapp.rest;

import com.example.syncapp.entity.Book;
import com.example.syncapp.exceptions.DefaultException;
import com.example.syncapp.rest.defaults.Response;
import com.example.syncapp.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {


    @Autowired
    BookService bookService;

    @PostMapping("add")
    public ResponseEntity<Response> add(@RequestBody Book book) {
        System.out.println(book);
        try {
            Book createdBook = bookService.add(book);
            return ResponseEntity.status(HttpStatus.OK).body(new Response().addProperty("book", createdBook));
        }
        catch (DefaultException defaultException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response().setError(defaultException));
        }
    }

    @PostMapping("update")
    public ResponseEntity<Response> update(@RequestBody Book book) {
        try {
            Book updatedBook = bookService.update(book);
            return ResponseEntity.status(HttpStatus.OK).body(new Response().addProperty("book", updatedBook));
        }
        catch (DefaultException defaultException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response().setError(defaultException));
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Response> delete(@RequestBody Book book) {
        try {
            boolean isDeleted = bookService.delete(book.getId());
            return ResponseEntity.status(HttpStatus.OK).body(new Response().addProperty("is_deleted", isDeleted));
        }
        catch (DefaultException defaultException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response().setError(defaultException));
        }
    }


}
