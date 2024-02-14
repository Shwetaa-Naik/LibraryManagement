package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.enums.BookType;
import org.shweta.LibraryManagement.modals.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {


    List<Book>findByBookNumber(String value);
    List<Book> findByBookCost(String cost);
    List<Book> findByAuthor_AuthorName(String authorName);
    List<Book> findByType(BookType bookType);
}
