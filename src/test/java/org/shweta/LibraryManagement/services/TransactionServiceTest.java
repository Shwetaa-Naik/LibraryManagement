package org.shweta.LibraryManagement.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shweta.LibraryManagement.modals.Transaction;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/*@SpringBootTest helps understand springboot that this class has test methods */
@SpringBootTest
public class TransactionServiceTest {


    TransactionService transactionService;


    @Before
    public void setup(){
        transactionService=new TransactionService();

    }
    @Test
    public void testCalculateAmount(){
        Transaction txn=Transaction.builder().createdOn(new Date()).paidAmount(300).build();
        /*calculateAmount should be public not private to test here*/
        int amount=transactionService.calculateAmount(txn);

        /*Assert the value*/
        Assert.assertEquals(300,amount);
    }
}
