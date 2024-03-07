package stud.ntnu.Calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.ntnu.Calculator.model.User;
import stud.ntnu.Calculator.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final String SECRET_KEY = "yourSecretKey"; // Change this to a secure value

    public String generateJwtToken(String username) {
        // Generate JWT token with username and expiration time
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 minutes expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            // Validate JWT token
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            // Perform authentication based on JWT token
            String token = generateJwtToken(username);
            return validateJwtToken(token);
        }
        return false;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}