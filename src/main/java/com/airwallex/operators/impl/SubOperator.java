package com.airwallex.operators.impl;

import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.InstructorsOperator;

import java.math.BigDecimal;
import java.util.Stack;

public class SubOperator extends InstructorsOperator {
    @Override
    protected Stack<BigDecimal> compute(StackInstruction stackInstruction, Stack<BigDecimal> workingStack) {
        BigDecimal operandOne = workingStack.pop();
        BigDecimal operandTwo = workingStack.pop();

        workingStack.push(operandTwo.subtract(operandOne));

        return workingStack;
    }
}
