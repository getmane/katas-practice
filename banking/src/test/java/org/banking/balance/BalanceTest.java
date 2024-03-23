package org.banking.balance;

import org.banking.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceTest {

    @Test
    void shouldAdd_WithSuccess() {

        // Given
        Balance balance = new Balance();
        int depositSum = 500;
        int lastBalance = balance.getCurrentBalance();

        // When
        Optional<Transaction> result = balance.add(depositSum);

        // Then
        assertEquals(lastBalance + depositSum, result.get().balance());
        assertEquals(depositSum, result.get().amount());
    }

    @Test
    void shouldAdd_WithFail() {
        // Given
        Balance balance = new Balance();
        int depositSum = Integer.MAX_VALUE;

        // When
        Optional<Transaction> result = balance.add(depositSum + 1);

        // Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    void shouldReduce_WithSuccess() {
        // Given
        Balance balance = new Balance();
        balance.add(500);
        int withdrawSum = 100;
        int lastBalance = balance.getCurrentBalance();

        // When
        Optional<Transaction> result = balance.subtract(withdrawSum);

        // Then
        assertEquals(lastBalance - withdrawSum, result.get().balance());
        assertEquals(withdrawSum, - result.get().amount());
    }

    @Test
    void shouldReduce_WithFail() {
        // Given
        Balance balance = new Balance();
        balance.add(100);
        int withdrawSum = 2000;

        // When
        Optional<Transaction> result = balance.subtract(withdrawSum);

        // Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    void shouldPrintBalance() {
        // Given
        Balance balance = new Balance();

        // When
        int currentBalance = balance.getCurrentBalance();

        // Then
        assertEquals(0, currentBalance);
    }
}
