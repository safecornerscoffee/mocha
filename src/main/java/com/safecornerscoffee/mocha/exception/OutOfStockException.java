package com.safecornerscoffee.mocha.exception;

public class OutOfStockException extends IllegalStateException {
    public OutOfStockException() {
        super();
    }

    public OutOfStockException(String s) {
        super(s);
    }
}
