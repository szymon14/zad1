package org.example.model;

import junit.framework.TestCase;
import org.example.printer.ConsoleStatementPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

public class AccountTest extends TestCase {

    private Clock fixedClock;
    private Account account;

    @BeforeEach
    public void setUp() {
        fixedClock = Clock.fixed(
                LocalDate.of(2025, 5, 28)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault());

        account = new Account(fixedClock);
    }

    @Test
    public void testDepositWithdrawCorrectHistory() {
        // GIVEN
        account.deposit(new BigDecimal("500"));
        account.withdraw(new BigDecimal("100"));
        account.deposit(new BigDecimal("600"));

        // WHEN
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        ConsoleStatementPrinter printer = new ConsoleStatementPrinter(ps);
        account.printStatement(printer);

        // THEN
        String expected = String.join(System.lineSeparator(),
                "DATE | AMOUNT | BALANCE",
                "28/05/2025 | 600.00 | 1000.00",
                "28/05/2025 | -100.00 | 400.00",
                "28/05/2025 | 500.00 | 500.00",
                ""
        );
        assertEquals(expected, bos.toString());
    }
}
