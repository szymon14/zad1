package org.example;

import org.example.model.Account;
import org.example.printer.ConsoleStatementPrinter;
import java.math.BigDecimal;
import java.time.Clock;

public class App {
    public static void main(String[] args) {
        Account account = new Account(Clock.systemDefaultZone());
        account.deposit(BigDecimal.valueOf(500));
        account.withdraw(BigDecimal.valueOf(100));
        account.deposit(BigDecimal.valueOf(600));

        account.printStatement(new ConsoleStatementPrinter(System.out));
    }
}
