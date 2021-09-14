package com.airwallex.operators.impl;

import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.InstructorsOperator;

import java.math.BigDecimal;
import java.util.Stack;

public class UndoOperator extends InstructorsOperator {
    @Override
    protected Stack<BigDecimal> compute(StackInstruction stackInstruction, Stack<BigDecimal> workingStack) {
        isToBeSaved = false;

        return stackInstruction.getPrevStatus();
    }
}
