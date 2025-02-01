package com.project.library.service.impl;

import com.project.library.DTO.BookDTO;
import com.project.library.entity.Book;
import com.project.library.repository.InMemoryBookDAO;
import com.project.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryStudentServiceImpl implements BookService {
    private final InMemoryBookDAO bookRepo;

    @Override
    public List<Book> findAllBooksService() {
        return bookRepo.findAllBooks();
    }

    @Override
    public Book convertToBookService(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .isbn(bookDTO.getIsbn())
                .available_copies(bookDTO.isAvailable_copies())
                .build();
    }

    @Override
    public void decreaseBooksCopiesService(String title) {
    }

    @Override
    public Book saveBookService(BookDTO bookDTO) {
        return bookRepo.saveBook(convertToBookService(bookDTO));
    }

    @Override
    public Book findBookByIdService(Long id) {
        return bookRepo.findBookById(id);
    }

    @Override
    public Book updateBookService(BookDTO bookDTO) {
        return bookRepo.updateBook(convertToBookService(bookDTO));
    }

    @Override
    public Book findBookByTitleService(String title) {
        return bookRepo.findBookByTitle(title);
    }

    @Override
    public void deleteBookByIdService(Long id) {
        bookRepo.deleteBookById(id);
    }
}
// этот файл работает с внутренней памятью ( памятью именно кода, списки и листы например )
