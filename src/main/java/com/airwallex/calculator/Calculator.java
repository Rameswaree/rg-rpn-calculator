package com.airwallex.calculator;

public interface Calculator {
    abstract void process(String input);
    abstract void print();
    public abstract void handle(String nextLine);
}
