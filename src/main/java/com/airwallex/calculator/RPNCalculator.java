package com.airwallex.calculator;

import com.airwallex.domain.OperatorsEnum;
import com.airwallex.exception.CalculatorException;
import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.OperatorsFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;

public class RPNCalculator implements Calculator {
    private Stack<BigDecimal> workingStack;
    private StackInstruction stackInstruction;
    private OperatorsFactory operatorsFactory;
    private int position;
    private DecimalFormat decimalFormat;

    private final String SUPPORTED_OPERATORS ="+, -, *, /, sqrt, clear, undo";
    private final String QUIT = "quit";
    private final String HELP = "help";

    public RPNCalculator(Stack<BigDecimal> workingStack, StackInstruction stackInstruction, OperatorsFactory operatorsFactory) {
        this.workingStack = workingStack;
        this.stackInstruction = stackInstruction;
        this.operatorsFactory = operatorsFactory;
        this.decimalFormat = new DecimalFormat("0.##########");

        System.out.println("---------- Welcome to RPN Calculator ----------");
        System.out.printf("Enter a number (or numbers) and any of the following operators to perform operations :[%s] \n Enter help for more help, quit to exit", SUPPORTED_OPERATORS);

    }

    @Override
    public void process(String input) {
        if(QUIT.equalsIgnoreCase(input)) {
            System.out.println("Exit from RPN Calculator!");
            System.exit(0);
        }

        if(HELP.equalsIgnoreCase(input)) {
            printHelpMessage();
            return;
        }

        if(isNumber(input)) {
            workingStack.push(new BigDecimal(input));
            stackInstruction.save(workingStack);
            return;
        }

        if(!operatorsFactory.isOperatorSupported(input)) {
            throw new CalculatorException(String.format("The operator [%s] is invalid and currently supports the following: [%s]", input, SUPPORTED_OPERATORS));
        }

        if(!OperatorsEnum.operable(input, workingStack.size(), stackInstruction.size())) {
            throw new CalculatorException(String.format("operator %s (position: %s): insufficient parameters", input, position));
        }

        workingStack = operatorsFactory.getOperatorHandler(input).saveProcess(stackInstruction, workingStack);
    }

    @Override
    public void print() {
        System.out.print("stack: ");
        for (BigDecimal b : workingStack) {
            System.out.print(decimalFormat.format(b) + " ");
        }
        System.out.println();
    }

    @Override
    public void handle(String nextLine) {
        position = -1;

        String[] elements = nextLine.split("\\s");
        for (String input : elements) {
            position += 2;
            //filters spaces in input line
            if ("".equals(input)) {
                position--;
                continue;
            }

            try {
                process(input);
            } catch (CalculatorException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        print();
    }

    private boolean isNumber(String input) {
        try {
            Double.valueOf(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void printHelpMessage() {
        System.out.printf("[1] The following operators are supported :[%s]%n", SUPPORTED_OPERATORS);
        System.out.println("[2] +-*/ : Corresponds to addition, subtraction, multiplication, and division operations. Note that when performing division operations, the divisor cannot be 0");
        System.out.println("[3] sqrt : returns the positive square root, the calculated number must be greater than or equal to 0");
        System.out.println("[4] clear : Clear all data, can be undone by undo");
        System.out.println("[5] undo : Undo the last successful operation");
    }
}
