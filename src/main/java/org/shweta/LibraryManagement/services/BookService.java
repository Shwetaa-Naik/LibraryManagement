package org.shweta.LibraryManagement.services;

import org.shweta.LibraryManagement.dtoRequests.BookCreateRequest;
import org.shweta.LibraryManagement.modals.Author;
import org.shweta.LibraryManagement.modals.Book;
import org.shweta.LibraryManagement.repositories.AuthorRepository;
import org.shweta.LibraryManagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(BookCreateRequest bookCreateRequest) {
        //extract author email from bookCreateRequest
        //check with querying if author is present already in author table

       Author authorInDB= authorRepository.findByEmail(bookCreateRequest.getAuthorEmail());
        if(authorInDB == null){
            //create row inside table author
            authorInDB=authorRepository.save(bookCreateRequest.toAuthor());
        }
        //insert data into book table

        Book bookObj=bookCreateRequest.toBook();
        bookObj.setAuthor(authorInDB);
        return bookRepository.save(bookObj);


    }
}
