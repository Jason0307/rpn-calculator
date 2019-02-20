package org.zhubao;

import java.util.Arrays;
import java.util.Stack;

import lombok.Getter;

@Getter
public class RPNCalculator {

    private Stack<Double> result = new Stack<>();

    private Stack<OperationHistory> history = new Stack<OperationHistory>();

    public void calculate(String input) {
        calculate(input, false);
    }

    public void calculate(String input, boolean isUndoOperation) {
        if (input == null) {
            throw new RuntimeException("Please input an valid command.");
        }
        Arrays.asList(input.split(" ")).forEach(command -> {
            Double number = parseDouble(command);
            boolean isDigital = number != null;
            if (isDigital) {
                result.push(number);
                if (!isUndoOperation) {
                    history.push(null);
                }
            } else {
                Operator operator = Operator.of(command);
                if (Operator.CLEAR == operator) {
                    result.clear();
                    history.clear();
                    return;
                }

                if (Operator.UNDO == operator) {
                    undoLastOperation();
                    return;
                }

                if (operator.getOperationNums() > result.size()) {
                    throw new RuntimeException(
                            String.format("operator %s : insufficient parameters", operator.getOperation()));
                }
                double n1 = result.pop();
                double n2 = operator.getOperationNums() > 1 ? result.pop() : 0;
                result.push(operator.getOperationFunction().apply(n1, n2));
                if (!isUndoOperation) {
                    history.push(new OperationHistory(operator, n1));
                }
            }
        });
    }

    private void undoLastOperation() {
        if (history.isEmpty()) {
            throw new RuntimeException("There is no operations to undo.");
        }

        OperationHistory lastOperation = history.pop();
        if (lastOperation == null) {
            result.pop();
        } else {
            calculate(lastOperation.getReverseOperation(), true);
        }

    }

    private Double parseDouble(String command) {
        try {
            return Double.parseDouble(command);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
