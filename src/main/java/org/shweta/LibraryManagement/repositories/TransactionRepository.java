package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.enums.TransactionType;
import org.shweta.LibraryManagement.modals.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findByTxnNumber(String txn);

    /*@Query(value = "UPDATE Transaction t SET t.paidAmount=:amount, t.status=:type where t.txnNumber=:txnNumber")
    void saveUpdateTransaction(String txnNumber, int amount, TransactionType type);
*/
}
