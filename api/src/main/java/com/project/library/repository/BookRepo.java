package com.project.library.repository;

import com.project.library.DTO.BookDTO;
import com.project.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    void deleteBookById(Long id);
    Book findBookById(Long id);
    Book findBookByTitle(String title);

}
