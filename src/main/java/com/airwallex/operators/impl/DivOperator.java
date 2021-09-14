package com.airwallex.operators.impl;

import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.InstructorsOperator;

import java.math.BigDecimal;
import java.util.Stack;

public class DivOperator extends InstructorsOperator {
    @Override
    protected Stack<BigDecimal> compute(StackInstruction stackInstruction, Stack<BigDecimal> workingStack) {
        BigDecimal operandOne = workingStack.pop();

        if(operandOne.equals(BigDecimal.ZERO)) {
            System.out.println("Division by zero is not allowed");

            workingStack.push(operandOne);
            isToBeSaved = false;

            return workingStack;
        }

        BigDecimal operandTwo = workingStack.pop();

        workingStack.push(
                operandTwo.divide(operandOne, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE)
        );

        return workingStack;
    }
}
