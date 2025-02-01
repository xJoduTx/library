package com.project.library.repository;

import com.project.library.DTO.BookDTO;
import com.project.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryBookDAO{

    private final List<Book> BOOKS = new ArrayList<>();

    public List<Book> findAllBooks() {
        return BOOKS;
    }

    public Book saveBook(Book book) {
        BOOKS.add(book);
        return book;
    }


    public Book findBookById(Long id) {
        return BOOKS.stream()
                .filter(element -> element.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Book findBookByTitle(String title) {
        return BOOKS.stream()
                .filter(element -> element.getTitle() == title)
                .findFirst()
                .orElse(null);
    }

    public Book updateBook(Book book) {
        var bookIndex = IntStream.range(0, BOOKS.size())
                .filter(index -> BOOKS.get(index).getId() == book.getId())
                .findFirst()
                .orElse(-1);
        if (bookIndex > -1){
            BOOKS.set(bookIndex, book);
            return book;
        }
        return null;
    }

    public void deleteBookById(Long id) {
        var book = findBookById(id);
        if (book != null){
            BOOKS.remove(book);
        }
    }
}
