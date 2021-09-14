package com.airwallex.operators;

import com.airwallex.domain.OperatorsEnum;
import com.airwallex.operators.impl.*;

import java.util.HashMap;
import java.util.Map;

public class OperatorsFactory {
    private Map<String, InstructorsOperator> operatorsMap;

    public OperatorsFactory() {
        init();
    }

    private void init() {
        operatorsMap = new HashMap<>();
        operatorsMap.put(OperatorsEnum.ADD.getValue(), new AddOperator());
        operatorsMap.put(OperatorsEnum.SUB.getValue(), new SubOperator());
        operatorsMap.put(OperatorsEnum.MUL.getValue(), new MulOperator());
        operatorsMap.put(OperatorsEnum.DIV.getValue(), new DivOperator());
        operatorsMap.put(OperatorsEnum.CLEAR.getValue(), new ClearOperator());
        operatorsMap.put(OperatorsEnum.UNDO.getValue(), new UndoOperator());
        operatorsMap.put(OperatorsEnum.SQRT.getValue(), new SqrtOperator());
    }

    public InstructorsOperator getOperatorHandler(String input) {
        return operatorsMap.get(input);
    }

    public boolean isOperatorSupported(String input) {
        return operatorsMap.containsKey(input);
    }
}
