package org.banking.balance;

import lombok.Getter;
import org.banking.Transaction;

import java.time.LocalDate;
import java.util.Optional;

@Getter
public class Balance {

    private int currentBalance;

    public Optional<Transaction> add(int amount) {
        if (currentBalance + amount == Integer.MIN_VALUE) { // Int.Max + 1 == Overflow
            return Optional.empty();
        }
        currentBalance += amount;
        return Optional.of(new Transaction(LocalDate.now(), amount, currentBalance));
    }

    public Optional<Transaction> subtract(int amount) {
        if (currentBalance - amount < 0) {
            return Optional.empty();
        }
        currentBalance -= amount;
        return Optional.of(new Transaction(LocalDate.now(), -amount, currentBalance));
    }

}
