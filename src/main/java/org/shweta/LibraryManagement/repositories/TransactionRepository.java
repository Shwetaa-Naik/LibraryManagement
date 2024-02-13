package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.modals.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
