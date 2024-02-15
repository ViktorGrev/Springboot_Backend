package stud.ntnu.Calculator.service;

import org.springframework.stereotype.Service;
import stud.ntnu.Calculator.model.CalculationRequest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Service
public class CalculatorService {
        public double calculate(CalculationRequest request) {
            String expression = request.getExpression();
            if (hasDivideByZero(expression)) {
                throw new IllegalArgumentException("Unsupported operation: " + request.getExpression());
            }
            return evaluateExpression(expression);
        }

        private boolean hasDivideByZero(String expression) {
            // Implement logic to check if the expression contains a division by zero
            return false; // Placeholder
        }

    public static double evaluateExpression(String expression) {
        // Remove all spaces from the expression
        expression = expression.replaceAll("\\s", "");
        // Split the expression into operands and operators
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

        double result = 0;
        double currentOperand = 0;
        char currentOp = '+';

        for (String token : tokens) {
            if (token.matches("[-+*/]")) {
                // Current token is an operator
                currentOp = token.charAt(0);
            } else {
                // Current token is an operand
                double operand = Double.parseDouble(token);
                switch (currentOp) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        if (operand == 0) throw new ArithmeticException("Division by zero.");
                        result /= operand;
                        break;
                }
            }
        }

        return result;
    }
}
