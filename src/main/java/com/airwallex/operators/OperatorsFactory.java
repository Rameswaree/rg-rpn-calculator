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

    /*
      On calling the constructor, the init method gets
      invoked, initializing the map with the operator
      and the appropriate subclass of InstructorsOperator
     */
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

    /**
     * Checks if the operator is being supported
     *
     * @param input The operator to be checked
     * @return boolean value
     */
    public boolean isOperatorSupported(String input) {
        return operatorsMap.containsKey(input);
    }
}
