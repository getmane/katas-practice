package org.banking.statement;

import org.banking.Transaction;

import java.util.Optional;

public interface Statement {

    void addTransaction(Transaction transaction);

    Optional<Transaction> getLastTransaction();

}
