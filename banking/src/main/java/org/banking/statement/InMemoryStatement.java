package org.banking.statement;

import org.banking.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryStatement implements Statement {

    private final List<Transaction> transactions;

    public InMemoryStatement() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public Optional<Transaction> getLastTransaction() {
        return transactions.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(transactions.get(transactions.size() - 1));
    }

    @Override
    public String toString() {
        String statementHeader = "Date\tAmount\tBalance\n";
        String statementTransactions = this.transactions.stream()
                .map(InMemoryStatement::transactionToString)
                .collect(Collectors.joining("\n"));
        return statementHeader + statementTransactions;
    }

    private static String transactionToString(Transaction transaction) {
        return transaction.date()
                + "\t" + transaction.amount()
                + "\t" + transaction.balance();
    }
}
