package com.project.library.service.impl;

import com.project.library.DTO.BookDTO;
import com.project.library.entity.Book;
import com.project.library.repository.BookRepo;
import com.project.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public void decreaseBooksCopiesService(String title) throws Exception {
        Book book = bookRepo.findBookByTitle(title);
        if (book.getBooks_copies() > 1) {
            book.setBooks_copies(book.getBooks_copies() - 2);
            saveBookService(deconvertToBookDTOService(book));
        } else {
            deleteBookByIdService(book.getId());
        }
    }

    public BookDTO deconvertToBookDTOService(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .books_copies(book.getBooks_copies())
                .available_copies(book.isAvailable_copies())
                .build();
    }

    @Override
    public List<Book> findAllBooksService() {
        return bookRepo.findAll();
    }

    public Book checkForCopies(BookDTO bookDTO) {
        Book tempBook = bookRepo.findBookByTitle(bookDTO.getTitle());
        System.out.println(tempBook);
        System.out.println(bookDTO);

        if (tempBook == null) {
            return Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .isbn(bookDTO.getIsbn())
                    .books_copies(1)
                    .available_copies(false)
                    .build();
        } else {
            if (tempBook.getTitle().equals(bookDTO.getTitle()) &
                    tempBook.getAuthor().equals(bookDTO.getAuthor()) &
                    tempBook.getIsbn() == bookDTO.getIsbn()) {
                System.out.println("То же имя, увеличиваем кол-во");
                tempBook.setBooks_copies(tempBook.getBooks_copies() + 1);
                return tempBook;
            }
        }
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .isbn(bookDTO.getIsbn())
                .books_copies(1)
                .available_copies(false)
                .build();

    }

    public Book checkForIdentityBook(BookDTO bookDTO) {
        List<Book> tempBooks = bookRepo.findAll();

        return tempBooks.stream()
                .filter(book -> book.getTitle().equals(bookDTO.getTitle()))
                .filter(book -> book.getAuthor().equals(bookDTO.getAuthor()))
                .filter(book -> book.getIsbn() == bookDTO.getIsbn())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book convertToBookService(BookDTO bookDTO) throws Exception {
        if (bookDTO.getAuthor() == "" | bookDTO.getAuthor() == null
                | bookDTO.getTitle() == "" | bookDTO.getTitle() == null |
                bookDTO.getIsbn() <= 0) {
            throw new Exception("Поля класса не могут быть пустыми");
        }
        Book tempBook = checkForIdentityBook(bookDTO);

        if (tempBook == null) {
            return Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .isbn(bookDTO.getIsbn())
                    .books_copies(1)
                    .available_copies(false)
                    .build();
        } else if (tempBook.getTitle().equals(bookDTO.getTitle()) &
                tempBook.getAuthor().equals(bookDTO.getAuthor()) &
                tempBook.getIsbn() == bookDTO.getIsbn()) {
            System.out.println("То же имя, увеличиваем кол-во");
            tempBook.setBooks_copies(tempBook.getBooks_copies() + 1);
            return tempBook;
        } else {
            return Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .isbn(bookDTO.getIsbn())
                    .books_copies(1)
                    .available_copies(false)
                    .build();
        }
    }

    @Override
    public Book saveBookService(BookDTO bookDTO) throws Exception {
        return bookRepo.save(convertToBookService(bookDTO));
    }

    @Override
    public Book findBookByIdService(Long id) {
        return bookRepo.findBookById(id);
    }

    @Override
    public Book updateBookService(BookDTO bookDTO) throws Exception {
        return bookRepo.save(convertToBookService(bookDTO));
    }

    @Override
    public Book findBookByTitleService(String title) {
        return bookRepo.findBookByTitle(title);
    }

    @Override
    public void deleteBookByIdService(Long id) {
        bookRepo.deleteById(id);
    }
}
// Этот файл работает с бд