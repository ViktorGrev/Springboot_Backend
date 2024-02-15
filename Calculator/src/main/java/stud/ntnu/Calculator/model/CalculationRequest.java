package stud.ntnu.Calculator.model;

public class CalculationRequest {
    private String expression;

    // No-argument constructor
    public CalculationRequest() {}

    // Constructor with all arguments
    public CalculationRequest(String expression) {
        this.expression = expression;
    }

    // Getter and setter
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
