package stud.ntnu.Calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.ntnu.Calculator.model.User;
import stud.ntnu.Calculator.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            // For simplicity, we're comparing plain text passwords.
            // In a real application, passwords should be hashed and salted.
            System.out.println(user.getId() + " " + user.getUsername());
            return password.equals(user.getPassword());
        }
        return false;
    }
}
