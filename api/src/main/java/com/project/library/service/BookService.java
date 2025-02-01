package com.project.library.service;

import com.project.library.DTO.BookDTO;
import com.project.library.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public interface BookService {
    Book convertToBookService(BookDTO bookDTO) throws Exception;
    void decreaseBooksCopiesService(String title) throws Exception;

    List<Book> findAllBooksService();

    Book saveBookService(BookDTO bookDTO) throws Exception;
    Book findBookByIdService(Long id);
    Book updateBookService(BookDTO bookDTO) throws Exception;
    Book findBookByTitleService(String title);
    void deleteBookByIdService(Long id);
}
