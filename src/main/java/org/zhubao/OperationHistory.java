package org.zhubao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistory {

    private Operator operator;

    private Double value;

    public String getReverseOperation() {
        if (operator.getOperationNums() < 1)
            throw new RuntimeException(String.format("Invalid operation for operator %s", operator.getOperation()));

        return (operator.getOperationNums() < 2) ? String.format("%s", operator.getReverseOperation())
                : String.format("%f %s %f", value, operator.getReverseOperation(), value);
    }
}
