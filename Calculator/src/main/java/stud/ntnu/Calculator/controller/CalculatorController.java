package stud.ntnu.Calculator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import stud.ntnu.Calculator.service.CalculatorService;
import stud.ntnu.Calculator.model.CalculationResponse;
import stud.ntnu.Calculator.model.CalculationRequest;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        logger.info("Calculating expression: " + request.getExpression());
        try {
            double result = calculatorService.calculate(request);
            logger.info("Sending respons: " + result);
            CalculationResponse response = new CalculationResponse();
            response.setResult(result);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            CalculationResponse response = new CalculationResponse();
            logger.info("Sending respons: " + response);
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

