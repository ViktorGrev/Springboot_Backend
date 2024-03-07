package stud.ntnu.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import stud.ntnu.Calculator.utility.JwtUtil;

@RestController
public class DataController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/api/data")
    public ResponseEntity<?> getData(@RequestHeader("Authorization") String tokenHeader) {
        // Extract token from the header
        String token = tokenHeader.substring(7); // Assuming the token is preceded by "Bearer "
        if (jwtUtil.validateToken(token)) {
            // Here, you can fetch and return any data related to the token/user
            // For simplicity, just confirming the token is valid
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Token is valid.\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Invalid token.\"}");
        }
    }
}
