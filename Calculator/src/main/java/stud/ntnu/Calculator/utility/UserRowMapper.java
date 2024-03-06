package stud.ntnu.Calculator.utility;

import org.springframework.jdbc.core.RowMapper;
import stud.ntnu.Calculator.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int user_id = rs.getInt("user_id"); // Retrieve user_id from ResultSet
        String username = rs.getString("username");
        String password = rs.getString("password");
        return new User(user_id, username, password);
    }
}

