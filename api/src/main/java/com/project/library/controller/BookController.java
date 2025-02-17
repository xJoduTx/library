package com.project.library.controller;

import com.project.library.DTO.BookDTO;
import com.project.library.entity.Book;
import com.project.library.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/books")
@CrossOrigin(origins = "${library.api.url}", maxAge = 3600)
public class BookController {

    @NonNull
    @Autowired
    private final BookService bookService;

    @GetMapping("/all") // показывает все книги
    public List<Book> getAll() {
        return bookService.findAllBooksService();
    }

    @PostMapping("/save") //сохраняет книгу
    public Book saveBook(@Valid @RequestBody BookDTO bookDTO) throws Exception {
        return bookService.saveBookService(bookDTO);}

    @GetMapping("/getById/{id}") // ищет книгу по айди
    public Book findBookById(@PathVariable Long id){
        return bookService.findBookByIdService(id);
    }

    @GetMapping("/getByTitle/{title}") // ищет книгу по айди
    public Book findBookByTitle(@PathVariable String title){
        return bookService.findBookByTitleService(title);
    }

    @PutMapping("/update") // обновляет книгу ( сохраняет )
    public Book updateBookById(@RequestBody BookDTO bookDTO) throws Exception {
        return bookService.updateBookService(bookDTO);
    }

    @GetMapping("/take/{title}")
    public void decreaseBookByTitle(@PathVariable String title) throws Exception {
        bookService.decreaseBooksCopiesService(title);
    }

    @DeleteMapping("delete/{id}") // удаляет книгу по айди
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookByIdService(id);
    }
}



