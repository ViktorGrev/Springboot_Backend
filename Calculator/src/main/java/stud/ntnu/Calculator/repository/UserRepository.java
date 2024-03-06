package stud.ntnu.Calculator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import stud.ntnu.Calculator.model.User;
import stud.ntnu.Calculator.utility.UserRowMapper;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(String username, String password) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, password);
    }

    public User findUserByUsername(String username) {
        String query = "SELECT user_id, username, password FROM user WHERE username = ?";
        Object[] parameters = new Object[]{username};
        return jdbcTemplate.queryForObject(query, parameters, new UserRowMapper());
    }
}
