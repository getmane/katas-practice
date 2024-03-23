package org.banking;

import java.time.LocalDate;

public record Transaction(LocalDate date, int amount, int balance) {}