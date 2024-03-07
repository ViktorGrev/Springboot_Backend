package stud.ntnu.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.ntnu.Calculator.model.Calculation;
import stud.ntnu.Calculator.model.User;
import stud.ntnu.Calculator.repository.CalculationRepository;
import stud.ntnu.Calculator.repository.UserRepository;
import stud.ntnu.Calculator.service.UserService;
import stud.ntnu.Calculator.utility.JwtUtil;

import java.util.List;
import java.util.Objects;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User foundUser = userService.getUserByUsername(user.getUsername());
        if (foundUser != null) {
            boolean loginSuccess = userService.authenticate(foundUser.getUsername(), foundUser.getPassword());
            if (loginSuccess) {
                // Generate JWT token for the authenticated user
                String token = jwtUtil.generateToken(foundUser.getUsername());
                // Include the token in the response
                return ResponseEntity.ok().body("{\"success\": true, \"token\": \"" + token + "\"}");
            } else {
                return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Invalid credentials\"}");
            }
        } else {
            return ResponseEntity.notFound().build(); // User not found
        }
    }

    // Endpoint for generating JWT token
    @PostMapping("/api/token")
    public ResponseEntity<?> generateToken(@RequestBody User user) {
        User foundUser = userService.getUserByUsername(user.getUsername());
        boolean loginSuccess = userService.authenticate(foundUser.getUsername(), foundUser.getPassword());
        if (loginSuccess) {
            // Generate JWT token
            String token = jwtUtil.generateToken(foundUser.getUsername());
            return ResponseEntity.ok().body("{\"success\": true, \"token\": \"" + token + "\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Invalid credentials\"}");
        }
    }

    @PostMapping("/api/previous")
    public ResponseEntity<List<Calculation>> previous(@RequestParam String username, @RequestParam String tokenHeader) {
        if (jwtUtil.validateToken(tokenHeader)){
            User user = userRepository.findUserByUsername(username);
            System.out.println(user.getId());
            List<Calculation> calculations = calculationRepository.findCalculationsByUserId(user.getId());
            System.out.println(calculations);
            return ResponseEntity.ok().body(calculations);
        }
        throw new IllegalArgumentException("The token is wrong");
    }

    @PostMapping("/api/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String username, @RequestParam String tokenHeader) {
        if (jwtUtil.validateToken(tokenHeader) && Objects.equals(jwtUtil.extractUsername(tokenHeader), username)){
            return ResponseEntity.ok().body(true);
        }
        throw new IllegalArgumentException("The token is wrong");
    }
}
