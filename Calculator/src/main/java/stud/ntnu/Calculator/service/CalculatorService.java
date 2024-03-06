package stud.ntnu.Calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.ntnu.Calculator.model.CalculationRequest;
import stud.ntnu.Calculator.model.User;
import stud.ntnu.Calculator.repository.CalculationRepository;
import stud.ntnu.Calculator.repository.UserRepository;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Service
public class CalculatorService {
    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private UserRepository userRepository;

    public double calculateAndSave(CalculationRequest request, String username) {
        double result = calculate(request); // Use the existing calculate method

        // After calculation, save the result along with the expression and user ID
        saveCalculationResult(request.getExpression(), result, username);

        return result;
    }

    private void saveCalculationResult(String expression, double result, String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            // User does not exist, handle accordingly
            System.out.println("User does not exist for username: " + username);
            return; // Or throw an exception
        }
        int userId = user.getId();

        System.out.println("Found user_id: " + userId + " for username: " + username);
        calculationRepository.saveCalculation(expression, String.valueOf(result), userId);
    }

    private double calculate(CalculationRequest request) {
        // Your existing calculation logic
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
