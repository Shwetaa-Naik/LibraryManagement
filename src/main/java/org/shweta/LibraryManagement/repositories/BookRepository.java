package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.modals.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
