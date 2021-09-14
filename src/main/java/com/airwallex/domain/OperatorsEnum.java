package com.airwallex.domain;

import java.util.HashMap;
import java.util.Map;

public enum OperatorsEnum {
    ADD("+", "Add", 2),
    SUB("-", "Subtract", 2),
    MUL("*", "Multiply", 2),
    DIV("/", "Divide", 2),
    SQRT("sqrt", "Square Root", 1),
    UNDO("undo", "Undo", 1),
    CLEAR("clear", "Clear", 0);

    private String value;
    private String description;
    private int operandsNumber;

    private static Map<String, Integer> operandsNumberMap;

    static {
        operandsNumberMap = new HashMap<>();
        for (OperatorsEnum operator : OperatorsEnum.values()) {
            operandsNumberMap.put(operator.getValue(), operator.getOperandsNumber());
        }
    }

    OperatorsEnum(String value, String description, int operandsNumber) {
        this.value = value;
        this.description = description;
        this.operandsNumber = operandsNumber;
    }

    public static boolean operable(String input, int stackSize, int mementoSize) {
        if (UNDO.value.equals(input)) {
            return mementoSize >= operandsNumberMap.get(input);
        }

        return stackSize >= operandsNumberMap.get(input);
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public int getOperandsNumber() {
        return operandsNumber;
    }
}
