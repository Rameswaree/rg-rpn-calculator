package com.airwallex.instruction;

import java.math.BigDecimal;
import java.util.Stack;

public interface Instruction {

    //saves the stack
    void save(Stack<BigDecimal> stack);

    //gets previous status of stack
    Stack<BigDecimal> getPrevStatus();
}
