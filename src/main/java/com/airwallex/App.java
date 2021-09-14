package com.airwallex;

import com.airwallex.calculator.Calculator;
import com.airwallex.calculator.RPNCalculator;
import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.OperatorsFactory;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

public class App {

    public static void main(String[] args) {
        Stack<BigDecimal> workingStack = new Stack<>();
        StackInstruction stackInstruction = new StackInstruction();
        Calculator calculator = new RPNCalculator(workingStack, stackInstruction, new OperatorsFactory());

        Scanner scanner = new Scanner(System.in);
        String nextLine;

        while (true) {
            nextLine = scanner.nextLine();

            while ("".equals(nextLine)) {
                nextLine = scanner.nextLine();
            }

            calculator.handle(nextLine);
        }
    }
}
