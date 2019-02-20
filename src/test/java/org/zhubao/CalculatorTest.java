package org.zhubao;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import static java.lang.Math.sqrt;

public class CalculatorTest {

    private static RPNCalculator calculator;

    @BeforeClass
    public static void init() {
        calculator = new RPNCalculator();
    }

    @Test
    public void testOperators() throws Exception {
        // eg 1
        calculator.calculate("5 2");
        assertEquals(5, calculator.getResult().get(0), 0);
        assertEquals(2, calculator.getResult().get(1), 0);

        // eg 2
        calculator.calculate("clear");
        calculator.calculate("2 sqrt");
        assertEquals(sqrt(2), calculator.getResult().get(0), 0);

        // eg 3
        calculator.calculate("clear");
        calculator.calculate("5 2 -");
        assertEquals(3, calculator.getResult().get(0), 0);
        calculator.calculate("3 -");
        assertEquals(0, calculator.getResult().get(0), 0);

        // eg 4
        calculator.calculate("clear");
        calculator.calculate("5 4 3 2");
        calculator.calculate("undo undo *");
        assertEquals(20, calculator.getResult().get(0), 0);
        calculator.calculate("5 *");
        assertEquals(100, calculator.getResult().get(0), 0);
        calculator.calculate("undo");
        assertEquals(20, calculator.getResult().get(0), 0);
        assertEquals(5, calculator.getResult().get(1), 0);

        // eg 5
        calculator.calculate("clear");
        calculator.calculate("7 12 2 /");
        assertEquals(7, calculator.getResult().get(0), 0);
        assertEquals(6, calculator.getResult().get(1), 0);
        calculator.calculate("*");
        assertEquals(42, calculator.getResult().get(0), 0);
        calculator.calculate("4 /");
        assertEquals(1, calculator.getResult().size());
        assertEquals(10.5, calculator.getResult().get(0), 0);

        // eg 6
        calculator.calculate("clear");
        calculator.calculate("1 2 3 4 5");
        calculator.calculate("*");
        assertEquals(4, calculator.getResult().size(), 0);
        assertEquals(20, calculator.getResult().get(3), 0);

        // eg 7
        calculator.calculate("clear");
        calculator.calculate("1 2 3 4 5");
        calculator.calculate("* * * *");
        assertEquals(120, calculator.getResult().get(0), 0);

        // eg 8
        calculator.calculate("clear");
        try {
            calculator.calculate("1 2 3 * 5 + * * 6 5");
        } catch (Exception e) {
            assertEquals("operator * : insufficient parameters", e.getMessage());
        }

    }
}