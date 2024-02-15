package org.shweta.LibraryManagement.services;

import jakarta.transaction.Transactional;
import org.shweta.LibraryManagement.dtoRequests.TransactionRequest;
import org.shweta.LibraryManagement.enums.FilterType;
import org.shweta.LibraryManagement.enums.OperatorType;
import org.shweta.LibraryManagement.enums.StudentFilterType;
import org.shweta.LibraryManagement.enums.TransactionType;
import org.shweta.LibraryManagement.exceptions.TransactionException;
import org.shweta.LibraryManagement.modals.Book;
import org.shweta.LibraryManagement.modals.Student;
import org.shweta.LibraryManagement.modals.Transaction;
import org.shweta.LibraryManagement.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    @Transactional(rollbackOn = TransactionException.class)
    public String createTxn(TransactionRequest transactionRequest)  {
        //1.check if student is present or not
       List<Student>studentInDB = studentService.getStudents(StudentFilterType.CONTACT_NUMBER, OperatorType.EQUALS,transactionRequest.getStudentPhoneNumber());
        //2.if student is not present in db then throw an exception
        if(studentInDB ==null || studentInDB.isEmpty()){
            try {
                throw new TransactionException("Student is not registred with us and hence book wont provided");
            } catch (TransactionException e) {
                throw new RuntimeException(e);
            }
        }

        Student student=studentInDB.get(0);
       //3.check if book is avaialable in db or not
      List<Book> bookInDB= bookService.getBooks(FilterType.BOOK_NO,OperatorType.EQUALS,transactionRequest.getBookNumber());
        //4.if book is not in DB then throw exception
        if(bookInDB == null || bookInDB.isEmpty()){
            try {
                throw new TransactionException("Selected book is not in Library");
            } catch (TransactionException e) {
                throw new RuntimeException(e);
            }
        }
        //5.if the book is in DB but currently unavailable then throw exception
        Book book=bookInDB.get(0);
        if(book.getStudent() != null){
            try {
                throw new TransactionException("Selected book is currently unavailable");
            } catch (TransactionException e) {
                throw new RuntimeException(e);
            }
        }

        String txnId=UUID.randomUUID().toString();
        //6.after passing all checks, create transaction object with all the fields and transaction id
        Transaction txn = Transaction.builder().
                student(student).
                book(book).
                paidAmount(transactionRequest.getMoney()).
                txnNumber(txnId).
                status(TransactionType.ISSUED).
                build();

        //7. save the data in transaction table
        Transaction savedTransaction=transactionRepository.save(txn);
        //8.change the status of book
        int studentId=student.getId();
        int bookId=book.getId();
            bookService.updateStudentId(studentId,bookId);

       return savedTransaction.getTxnNumber();
    }
}
