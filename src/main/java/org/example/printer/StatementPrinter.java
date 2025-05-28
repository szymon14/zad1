package org.example.printer;

import org.example.model.Operation;

import java.util.List;

public interface StatementPrinter {
    void print(List<Operation> operations);
}