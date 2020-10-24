package com.bl.Exception;

public class InvoiceSummaryException extends Exception {
    public enum ExceptionType {
        NO_RIDES;
    }

    public ExceptionType type;

    public InvoiceSummaryException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
