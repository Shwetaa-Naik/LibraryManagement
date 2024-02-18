package org.shweta.LibraryManagement.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.shweta.LibraryManagement.exceptions.TransactionException;
import org.shweta.LibraryManagement.modals.Transaction;
import org.shweta.LibraryManagement.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/*@SpringBootTest helps understand springboot that this class has test methods */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

/*Inside test class you wont use @Autowired because your not loading the entire project your
* running only one method at a time
* so one option is to create object using new keyword
* or use @MockBean annotation to get the mock(dummy) object
* to tell springboot that inject these mocks in service class by using @InjectMocks
* */
    @InjectMocks
    TransactionService transactionService;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StudentService studentService;
    @Mock
    BookService bookService;

    @Before
    public void setup(){
            /*used to initialise variable which you want to initialise before using it in
            * each and every test case
            * */
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateAmount(){
        Transaction txn=Transaction.builder().createdOn(new Date()).paidAmount(300).build();
        /*calculateAmount should be public not private to test here*/
        int amount=transactionService.calculateAmount(txn);

        /*Assert the value*/
        Assert.assertEquals(300,amount);
    }


    /*@return TransactionException becz returnBook() throws this exception*/
    @Test(expected = TransactionException.class)
    public void testReturnBook() throws TransactionException {
        //returnBook depends upon studentService
        //instead of creating the object of studentService, you can mock using mockito

        when(studentService.getStudents(any(),any(),any())).thenReturn(null);
            transactionService.returnBook(any());
    }
}
