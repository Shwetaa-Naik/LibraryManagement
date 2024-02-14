package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.enums.BookType;
import org.shweta.LibraryManagement.modals.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {


    List<Book>findByBookNo(String value);
    List<Book> findByCost(String cost);
    List<Book> findByAuthorName(String name);
    List<Book> findByBookType(BookType bookType);
}
