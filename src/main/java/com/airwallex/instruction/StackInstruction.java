package com.airwallex.instruction;

import java.math.BigDecimal;
import java.util.Stack;

public class StackInstruction implements Instruction {
    private static Stack<Stack<BigDecimal>> stackOfStacks = new Stack<>();

    @Override
    public void save(Stack<BigDecimal> stack) {
        stackOfStacks.push(getBigDecimalStack(stack));
    }

    @Override
    public Stack<BigDecimal> getPrevStatus() {
        stackOfStacks.pop();

        return !stackOfStacks.empty() ? stackOfStacks.peek():new Stack<>();
    }

    private Stack<BigDecimal> getBigDecimalStack(Stack<BigDecimal> stack) {

        Stack<BigDecimal> bigDecimalStack = new Stack<>();

        for(BigDecimal bigDecimal:stack) {
            bigDecimalStack.push(bigDecimal);
        }

        return bigDecimalStack;
    }

    public int size() {
        return stackOfStacks.size();
    }
}
