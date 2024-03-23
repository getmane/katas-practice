package org.banking;

import org.banking.balance.Balance;
import org.banking.statement.Statement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// TODO: Parametrize where possible
@ExtendWith(MockitoExtension.class)
class AccountTest {

    @Mock
    Balance balance;

    @Mock
    Statement statement;

    @InjectMocks
    Account account;

    @Test
    void shouldDepositAndCreateStatement() {
        // Given
        int depositSum = 500;
        int lastBalance = account.getBalance();
        int expectedBalance = lastBalance + depositSum;

        Optional<Transaction> transaction = Optional.of(new Transaction(LocalDate.now(), depositSum, expectedBalance));

        when(balance.add(depositSum)).thenReturn(transaction);
        when(balance.getCurrentBalance()).thenReturn(expectedBalance);

        when(statement.getLastTransaction()).thenReturn(transaction);

        // When
        account.deposit(depositSum);

        // Then
        assertEquals(expectedBalance, account.getBalance());
        assertEquals(account.getLastTransaction(), transaction);
    }

    @Test
    void shouldNotDepositOrCreateStatement() {
        // Given
        int depositSum = 500;
        int lastBalance = account.getBalance();

        Optional<Transaction> emptyTransaction = Optional.empty();

        when(balance.add(depositSum)).thenReturn(emptyTransaction);
        when(balance.getCurrentBalance()).thenReturn(lastBalance);

        when(statement.getLastTransaction()).thenReturn(emptyTransaction);

        // When
        account.deposit(depositSum);

        // Then
        assertEquals(lastBalance, account.getBalance());
        assertEquals(account.getLastTransaction(), emptyTransaction);
    }

    @Test
    void shouldWithdrawAndCreateStatement() {
        // Given
        int withdrawSum = 500;
        int lastBalance = account.getBalance();
        int expectedBalance = lastBalance - withdrawSum;

        Optional<Transaction> transaction = Optional.of(new Transaction(LocalDate.now(), withdrawSum, expectedBalance));

        when(balance.subtract(withdrawSum)).thenReturn(transaction);
        when(balance.getCurrentBalance()).thenReturn(expectedBalance);

        when(statement.getLastTransaction()).thenReturn(transaction);

        // When
        account.withdraw(withdrawSum);

        // Then
        assertEquals(expectedBalance, account.getBalance());
        assertEquals(account.getLastTransaction(), transaction);
    }

    @Test
    void shouldNotWithDrawOrCreateStatement() {
        // Given
        int withDrawSum = 500;
        int lastBalance = account.getBalance();

        Optional<Transaction> emptyTransaction = Optional.empty();

        when(balance.subtract(withDrawSum)).thenReturn(emptyTransaction);
        when(balance.getCurrentBalance()).thenReturn(lastBalance);

        when(statement.getLastTransaction()).thenReturn(emptyTransaction);

        // When
        account.withdraw(withDrawSum);

        // Then
        assertEquals(lastBalance, account.getBalance());
        assertEquals(account.getLastTransaction(), emptyTransaction);
    }

    @Test
    void shouldPrintStatement() {
        // Given
        String expectedStatement =
                "Date\tAmount\tBalance\n" +
                LocalDate.now() + "\t+" + 100 + "\t" + 100 + "\n" +
                LocalDate.now() + "\t-" + 100 + "\t" + 0;
        when(statement.toString()).thenReturn(expectedStatement);

        // When
        String statement = account.printStatement();

        // Then
        assertEquals(expectedStatement, statement);
    }
}
