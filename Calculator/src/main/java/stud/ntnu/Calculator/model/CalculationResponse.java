package stud.ntnu.Calculator.model;

public class CalculationResponse {
    private double result;
    private String message; // Optional, for error messages or details

    // Constructors, getters, and setters


    public CalculationResponse() {
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
