package org.zhubao;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

import lombok.Getter;

@Getter
public enum Operator {

    ADD("+", "-", 2, (n1, n2) -> n2 + n1),

    SUB("-", "+", 2, (n1, n2) -> n2 - n1),

    MUL("*", "/", 2, (n1, n2) -> n2 * n1),

    DIV("/", "*", 2, (n1, n2) -> n2 / n1),

    SQRT("sqrt", "pow", 1, (n1, n2) -> sqrt(n1)),
    
    POW("pow", "sqrt", 1, (n1, n2) -> pow(n1, 2d)),

    UNDO("undo", null, 0, null),

    CLEAR("clear", null, 0, null);

    private String operation;

    // for undo
    private String reverseOperation;
    
    private int operationNums;

    private BiFunction<Double, Double, Double> operationFunction;

    private static Map<String, Operator> operators = new HashMap<String, Operator>();

    static {
        for (Operator operator : values()) {
            operators.put(operator.getOperation(), operator);
        }
    }

    private Operator(String operation, String reverseOperation, int operationNums, BiFunction<Double, Double, Double> operationFunction) {
        this.operation = operation;
        this.reverseOperation = reverseOperation;
        this.operationNums = operationNums;
        this.operationFunction = operationFunction;
    }

    public static Operator of(String value) {
        return operators.get(value);
    }
}
