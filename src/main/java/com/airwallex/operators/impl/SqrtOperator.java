package com.airwallex.operators.impl;

import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.InstructorsOperator;

import java.math.BigDecimal;
import java.util.Stack;

public class SqrtOperator extends InstructorsOperator {
    @Override
    protected Stack<BigDecimal> compute(StackInstruction stackInstruction, Stack<BigDecimal> workingStack) {
        BigDecimal operand = workingStack.pop();

        if(operand.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Square root of a negative number is not allowed");

            workingStack.push(operand);
            isToBeSaved = false;

            return workingStack;
        }

        workingStack.push(BigDecimal.valueOf(Math.sqrt(operand.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE).doubleValue())));

        return workingStack;
    }
}
