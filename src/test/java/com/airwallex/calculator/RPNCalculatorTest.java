package com.airwallex.calculator;

import com.airwallex.exception.CalculatorException;
import com.airwallex.instruction.StackInstruction;
import com.airwallex.operators.OperatorsFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Stack;

public class RPNCalculatorTest {

    Calculator rpnCalculator;
    Stack<BigDecimal> workingStack;

    @Before
    public void init() {
        rpnCalculator = new RPNCalculator(new Stack<>(), new StackInstruction(), new OperatorsFactory());
        this.workingStack = getWorkingStack();
    }

    @Test
    public void testNumber() {
        rpnCalculator.process("4");
        Assert.assertEquals("4", getPeekValue());
    }

    @Test
    public void testAdd() {
        rpnCalculator.process("4");
        rpnCalculator.process("4");
        rpnCalculator.process("+");
        Assert.assertEquals("8", getPeekValue());
    }

    @Test(expected = CalculatorException.class)
    public void testAddWhenInsufficientParameters() {
        rpnCalculator.process("2");
        rpnCalculator.process("+");

    }

    @Test
    public void testSubtract() {
        rpnCalculator.process("9");
        rpnCalculator.process("18");
        rpnCalculator.process("-");
        Assert.assertEquals("-9", getPeekValue());
    }

    @Test
    public void testMultiply() {
        rpnCalculator.process("20");
        rpnCalculator.process("9");
        rpnCalculator.process("*");
        Assert.assertEquals("180", getPeekValue());
    }

    @Test
    public void testDivisionSuccess() {
        rpnCalculator.process("4");
        rpnCalculator.process("4");
        rpnCalculator.process("/");
        Assert.assertEquals("1", getPeekValue());
    }

    @Test
    public void testDivisionFailure() {
        rpnCalculator.process("3");
        rpnCalculator.process("0");
        rpnCalculator.process("/");

        //assert that peek value returns the previous status since division by 0 is not allowed
        Assert.assertEquals("0", getPeekValue());
    }

    @Test
    public void testSqrtSuccessForIntegerRootValue() {
        rpnCalculator.process("9");
        rpnCalculator.process("sqrt");
        Assert.assertEquals("3", getPeekValue());
    }

    @Test
    public void testSqrtSuccessForFloatRootValue() {
        rpnCalculator.process("2");
        rpnCalculator.process("sqrt");
        Assert.assertEquals("1.4142135623", getPeekValue());
    }

    @Test
    public void testSqrtFailure() {
        rpnCalculator.process("-5");
        rpnCalculator.process("sqrt");
        Assert.assertEquals("-5", getPeekValue());
    }

    @Test
    public void testClear() {
        rpnCalculator.process("9");
        rpnCalculator.process("sqrt");
        rpnCalculator.process("clear");

        Assert.assertEquals(0, workingStack.size());
    }

    @Test
    public void testUndo() {
        rpnCalculator.process("1");
        rpnCalculator.process("4");
        rpnCalculator.process("undo");

        Assert.assertEquals(BigDecimal.valueOf(1), Objects.requireNonNull(getWorkingStack()).peek());
    }

    @Test(expected = CalculatorException.class)
    public void testOtherCommands() {

        rpnCalculator.process("help");
        rpnCalculator.process("xxx");
        rpnCalculator.process("quit");

    }

    @Test
    public void testHandle() {

        String test1 = "clear 5 2";
        rpnCalculator.handle(test1);

        String test2 = "clear 2 sqrt clear 9 sqrt";
        rpnCalculator.handle(test2);

        String test3 = "clear 5 2 - 3 - clear";
        rpnCalculator.handle(test3);

        String test4 = "clear 5 4 3 2 undo undo * 5 * undo";
        rpnCalculator.handle(test4);

        String test5 = "clear 7 12 2 / * 4 /";
        rpnCalculator.handle(test5);

        String test6 = "clear 1 2 3 4 5 * clear 3 4 -";
        rpnCalculator.handle(test6);

        String test7 = "clear 1 2 3 4 5 * * * *";
        rpnCalculator.handle(test7);

        String test8 = "clear 1 2 3 * 5 + * * 6 5";
        rpnCalculator.handle(test8);

        String test9 = "xxx";
        rpnCalculator.handle(test9);
    }

    private String getPeekValue() {
        DecimalFormat decimalFormat = new DecimalFormat("0.###############");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        return decimalFormat.format(workingStack.peek());
    }

    private Stack<BigDecimal> getWorkingStack() {
        try {
            Field field = rpnCalculator.getClass().getDeclaredField("workingStack");
            field.setAccessible(true);
            return (Stack<BigDecimal>) field.get(rpnCalculator);
        } catch (Exception e) {
            System.err.println("Error Occurs!");
        }
        return null;
    }
}