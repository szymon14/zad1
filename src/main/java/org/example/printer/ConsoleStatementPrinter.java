package org.example.printer;

import org.example.model.Operation;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ConsoleStatementPrinter implements StatementPrinter {

    private static final String HEADER = "DATE | AMOUNT | BALANCE";
    private final PrintStream out;

    public ConsoleStatementPrinter(PrintStream out) {
        this.out = out;
    }

    @Override
    public void print(List<Operation> operations) {
        out.println(HEADER);
        List<Operation> reversed = new ArrayList<>(operations);
        Collections.reverse(reversed);
        for (Operation op : reversed) {
            out.printf("%s | %s | %s%n",
                    op.formattedDate(),
                    scaleToString(op.getAmount()),
                    scaleToString(op.getBalance()));
        }
    }

    private String scaleToString(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    }
}