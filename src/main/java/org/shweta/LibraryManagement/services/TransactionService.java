package org.shweta.LibraryManagement.services;

import jakarta.transaction.Transactional;
import org.shweta.LibraryManagement.dtoRequests.TransactionRequest;
import org.shweta.LibraryManagement.dtoRequests.TransactionReturnRequest;
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

import java.sql.Time;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    @Transactional(rollbackOn = TransactionException.class)
    public String createTxn(TransactionRequest transactionRequest,Student student1) throws TransactionException {
        //1.check if student is present or not
       List<Student>studentInDB = studentService.getStudents(StudentFilterType.CONTACT_NUMBER, OperatorType.EQUALS,student1.getPhoneNumber());
        //2.if student is not present in db then throw an exception
        if(studentInDB ==null || studentInDB.isEmpty()){

                throw new TransactionException("Student is not registred with us and hence book wont provided");

        }

        Student student=studentInDB.get(0);
       //3.check if book is avaialable in db or not
      List<Book> bookInDB= bookService.getBooks(FilterType.BOOK_NO,OperatorType.EQUALS,transactionRequest.getBookNumber());
        //4.if book is not in DB then throw exception
        if(bookInDB == null || bookInDB.isEmpty()){
                throw new TransactionException("Selected book is not in Library");

        }
        //5.if the book is in DB but currently unavailable then throw exception
        Book book=bookInDB.get(0);
        if(book.getStudent() != null){
            throw new TransactionException("Selected book is currently unavailable");

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

    public String returnBook(TransactionReturnRequest transactionReturnRequest) throws TransactionException {


        //update the same transaction status AVAILABLE

        //1.check if student is registered with library i.e. if present in db or not
        String phone=transactionReturnRequest.getStudentPhoneNumber();
        List<Student> studentList=studentService.getStudents(StudentFilterType.CONTACT_NUMBER,OperatorType.EQUALS,phone);
        Student studentInDB=studentList.get(0);
        if(studentInDB ==null){
                throw new TransactionException("Student is not found");

        }

        /*2.check if the book is present in library i.e. if present entry in db or not and
         *if present its status is ISSUED or not*/
        List<Book> bookList=bookService.getBooks(FilterType.BOOK_NO,OperatorType.EQUALS,transactionReturnRequest.getBookNumber());
        Book bookInDB=bookList.get(0);
        if(bookInDB ==null || bookInDB.getStudent()==null){
                throw new TransactionException("Book is either not found or may be not issued to student, Hence you cant return");

        }

        //3.check if same student has took that same book which he wants to return
        if(studentInDB.equals(bookInDB.getStudent())){
            //4.if yes then calculate the number of days book with student and accordingly calculate fine
            //for calculating the amount you need to have book creation time and this can be get from
            //transaction table(object) we have transaction number
            //using which we can get the transaction object and can fetch the details
                Transaction txn=transactionRepository.findByTxnNumber(transactionReturnRequest.getTxnNumber());
                if(txn == null){
                        throw new TransactionException("No transaction has been found");

                }
            int amount=calculateAmount(txn);
            //5.then mark the book available i.e. studentid=null
            bookInDB.setStudent(null);
            bookService.saveUpdate(bookInDB);
            //6.update the same transaction status AVAILABLE
            txn.setStatus(TransactionType.RETURNED);
            txn.setPaidAmount(amount);
            transactionRepository.save(txn);
           // transactionRepository.saveUpdateTransaction(transactionReturnRequest.getTxnNumber(),amount,TransactionType.RETURNED);
        }
        else {
                throw new TransactionException("This book is not belongs to this Student");

        }


        return "Shweta";
    }

    /*private int calculateAmount(Transaction txn) {*/
    public int calculateAmount(Transaction txn) {
        int settlementAmount;
        //1.get transaction creation time
        Long creationTime=txn.getCreatedOn().getTime();
        Long todaysTime=System.currentTimeMillis();

        Long differenceMiliseconds=todaysTime-creationTime;
                                    //16-1=15
        Long days= TimeUnit.MILLISECONDS.toDays(differenceMiliseconds);

        int numberOfDays=days.intValue();

        if(numberOfDays >=14){
            int fine=numberOfDays-14;
            //15-14=1
            settlementAmount=fine*5;
            txn.setPaidAmount(settlementAmount);
        }
        else {
            settlementAmount= txn.getPaidAmount();
        }
        return settlementAmount;
    }
}
