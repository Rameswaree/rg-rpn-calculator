package com.airwallex.operators;

import com.airwallex.instruction.StackInstruction;

import java.math.BigDecimal;
import java.util.Stack;

public abstract class InstructorsOperator {

    protected boolean isToBeSaved = true;

    protected final int DEFAULT_SCALE = 15;
    protected final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;


    abstract protected Stack<BigDecimal> compute(StackInstruction stackInstruction, Stack<BigDecimal> workingStack);
}
