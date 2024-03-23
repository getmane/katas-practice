package org.banking;

import org.banking.balance.Balance;
import org.banking.statement.Statement;

import java.util.Optional;

public class Account {

    private final Balance balance;
    private final Statement statement;

    public Account(Balance balance, Statement statement) {
        this.balance = balance;
        this.statement = statement;
    }

    void deposit(int amount) {
        Optional<Transaction> depositTransaction = this.balance.add(amount);
        depositTransaction.ifPresent(this.statement::addTransaction);
    }

    void withdraw(int amount) {
        Optional<Transaction> withdrawTransaction = this.balance.subtract(amount);
        withdrawTransaction.ifPresent(this.statement::addTransaction);
    }

    String printStatement() {
        return this.statement.toString();
    }

    int getBalance() {
        return this.balance.getCurrentBalance();
    }

    Optional<Transaction> getLastTransaction() {
        return this.statement.getLastTransaction();
    }
}
