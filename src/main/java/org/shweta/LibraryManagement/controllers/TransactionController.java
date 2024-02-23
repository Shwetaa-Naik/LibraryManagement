package org.shweta.LibraryManagement.controllers;

import jakarta.validation.Valid;
import org.shweta.LibraryManagement.dtoRequests.TransactionRequest;
import org.shweta.LibraryManagement.dtoRequests.TransactionReturnRequest;
import org.shweta.LibraryManagement.exceptions.TransactionException;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //student has been added in db
    //book and author are added in db
    //now student can get the book from library
    //for this create the transaction and generate transaction ID

    @PostMapping("/create")
    public String createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) throws TransactionException {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Student student=(Student) authentication.getPrincipal();
        return transactionService.createTxn(transactionRequest,student);
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody TransactionReturnRequest transactionReturnRequest) throws TransactionException {
        return transactionService.returnBook(transactionReturnRequest);
    }
}
