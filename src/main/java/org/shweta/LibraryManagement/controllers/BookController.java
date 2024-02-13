package org.shweta.LibraryManagement.controllers;

import org.shweta.LibraryManagement.dtoRequests.BookCreateRequest;
import org.shweta.LibraryManagement.modals.Book;
import org.shweta.LibraryManagement.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    //advantage of defining request class
    //1.its an independent class i.e. you can after modified this class later on without
    //disturbing other classes
    //2.Your not letting know the backends exact schema to front end


    @PostMapping("/create")
    public Book addBook(@RequestBody BookCreateRequest bookCreateRequest){
        //validations can be

        return bookService.addBook(bookCreateRequest);

    }


}
