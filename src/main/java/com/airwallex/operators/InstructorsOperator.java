package com.airwallex.operators;

import com.airwallex.instruction.StackInstruction;

import java.math.BigDecimal;
import java.util.Stack;

public abstract class InstructorsOperator {

    //Default value assigned to be true
    protected boolean isToBeSaved = true;

    //default precision and rounding mode
    protected final int DEFAULT_SCALE = 15;
    protected final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    public Stack<BigDecimal> saveProcess(StackInstruction stackInstruction, Stack<BigDecimal> workingStack) {
        Stack<BigDecimal> currentWorkingStack = compute(stackInstruction, workingStack);

        if (isToBeSaved) {
            stackInstruction.save(currentWorkingStack);
        }

        return currentWorkingStack;
    }

    //Separate logic for each operator
    abstract protected Stack<BigDecimal> compute(StackInstruction stackInstruction, Stack<BigDecimal> workingStack);
}
