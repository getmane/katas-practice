package org.banking.transaction;

import org.banking.statement.InMemoryStatement;
import org.banking.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryStatementTest {

    @Test
    void shouldAddTransaction() {
        // Given
        InMemoryStatement statement = new InMemoryStatement();
        Transaction transaction = new Transaction(LocalDate.now(), 500, 100);

        // When
        statement.addTransaction(transaction);

        // Then
        assertEquals(transaction, statement.getLastTransaction().get());
    }

    @Test
    void shouldPrintStatement() {
        // Given
        InMemoryStatement statement = new InMemoryStatement();

        Transaction positiveTransaction = new Transaction(LocalDate.now(), 500, 100);
        Transaction negativeTransaction = new Transaction(LocalDate.now(), -500, 600);
        statement.addTransaction(positiveTransaction);
        statement.addTransaction(negativeTransaction);

        String expectedPrintResult =
                "Date\tAmount\tBalance\n"
                + positiveTransaction.date() + "\t" + positiveTransaction.amount() + "\t" + positiveTransaction.balance() + "\n"
                + negativeTransaction.date() + "\t" + negativeTransaction.amount() + "\t" + negativeTransaction.balance();

        // When
        String statementPrintResult = statement.toString();

        // Then
        assertEquals(expectedPrintResult, statementPrintResult);
    }
}
