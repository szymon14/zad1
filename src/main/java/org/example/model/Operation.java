package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public final class Operation {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate date;
    private final BigDecimal amount;
    private final BigDecimal balance;

    public Operation(LocalDate date, BigDecimal amount, BigDecimal balance) {
        if (date == null || amount == null || balance == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }
        this.date = LocalDate.of(
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth()
        );
        this.amount = new BigDecimal(amount.toPlainString());
        this.balance = new BigDecimal(balance.toPlainString());
    }

    public String formattedDate() {
        return date.format(FORMATTER);
    }

    public LocalDate getDate() {
        return LocalDate.of(
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth()
        );
    }

    public BigDecimal getAmount() {
        return new BigDecimal(amount.toPlainString());
    }

    public BigDecimal getBalance() {
        return new BigDecimal(balance.toPlainString());
    }
}