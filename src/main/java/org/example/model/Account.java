package org.example.model;

import org.example.printer.StatementPrinter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private final Clock clock;// to track time
    private final List<Operation> operations = new ArrayList<>();

    public Account(Clock clock) {
        this.clock = clock;
    }

    public void deposit(BigDecimal amount) {
        BigDecimal scaledAmount = scale(amount);
        BigDecimal newBalance = scale(currentBalance().add(scaledAmount));
        operations.add(new Operation(LocalDate.now(clock), scaledAmount, newBalance));
    }

    public void withdraw(BigDecimal amount) {
        BigDecimal scaledAmount = scale(amount);
        BigDecimal currentBalance = currentBalance();
        if (currentBalance.compareTo(scaledAmount) < 0) {
            throw new IllegalArgumentException(
                    "Insufficient funds: available balance is " + currentBalance);
        }
        BigDecimal newBalance = scale(currentBalance.subtract(scaledAmount));
        operations.add(new Operation(LocalDate.now(clock), scaledAmount.negate(), newBalance));
    }

    private BigDecimal currentBalance() {
        if (operations.isEmpty()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        }
        return scale(operations.get(operations.size() - 1).getBalance());
    }

    private BigDecimal scale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void printStatement(StatementPrinter printer) {
        printer.print(Collections.unmodifiableList(operations));
    }
}