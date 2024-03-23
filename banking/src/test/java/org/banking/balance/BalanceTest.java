package org.banking.balance;

import org.banking.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 500, 250_000})
    void shouldAdd_WithSuccess(int addAmount) {
        // Given
        Balance balance = new Balance();
        int lastBalance = balance.getCurrentBalance();

        // When
        Optional<Transaction> result = balance.add(addAmount);

        // Then
        assertEquals(lastBalance + addAmount, result.get().balance());
        assertEquals(addAmount, result.get().amount());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MAX_VALUE + 1})
    void shouldAdd_WithFail(int amount) {
        // Given
        Balance balance = new Balance();

        // When
        Optional<Transaction> result = balance.add(amount);

        // Then
        assertEquals(Optional.empty(), result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 500, 250_000})
    void shouldReduce_WithSuccess(int amount) {
        // Given
        Balance balance = new Balance();
        balance.add(Integer.MAX_VALUE);
        int lastBalance = balance.getCurrentBalance();

        // When
        Optional<Transaction> result = balance.subtract(amount);

        // Then
        assertEquals(lastBalance - amount, result.get().balance());
        assertEquals(amount, - result.get().amount());
    }

    @ParameterizedTest
    @ValueSource(ints = {100, -20})
    void shouldReduce_WithFail(int amount) {
        // Given
        Balance balance = new Balance();
        balance.add(50);

        // When
        Optional<Transaction> result = balance.subtract(amount);

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
