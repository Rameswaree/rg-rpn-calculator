package com.airwallex.exception;

/**
 * Custom exception thrown
 * when the operator is invalid
 * or when there are insufficient parameters
 */
public class CalculatorException extends RuntimeException{

    public CalculatorException(String message) {
        super(message);
    }
}
