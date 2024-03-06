package stud.ntnu.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stud.ntnu.Calculator.model.Calculation;
import stud.ntnu.Calculator.model.User;
import stud.ntnu.Calculator.repository.CalculationRepository;
import stud.ntnu.Calculator.repository.UserRepository;
import stud.ntnu.Calculator.service.UserService;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean loginSuccess = userService.authenticate(user.getUsername(), user.getPassword());
        if (loginSuccess) {
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Invalid credentials\"}");
        }
    }

    @PostMapping("/api/previous")
    public ResponseEntity<List<Calculation>> previous(@RequestParam String username) {
        User user = userRepository.findUserByUsername(username);
        System.out.println(user.getId());
        List<Calculation> calculations = calculationRepository.findCalculationsByUserId(user.getId());
        System.out.println(calculations);
        return ResponseEntity.ok().body(calculations);
    }
}

