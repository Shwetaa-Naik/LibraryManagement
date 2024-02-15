package org.shweta.LibraryManagement.repositories;

import jakarta.transaction.Transactional;
import org.shweta.LibraryManagement.enums.BookType;
import org.shweta.LibraryManagement.modals.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {


    List<Book>findByBookNumber(String value);
    List<Book> findByBookCost(String cost);
    List<Book> findByAuthor_AuthorName(String authorName);
    List<Book> findByType(BookType bookType);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Book b SET b.student.id = :studentId WHERE b.id = :bookId")
    void saveUpdate(int studentId, int bookId);


}
