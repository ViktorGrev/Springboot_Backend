package stud.ntnu.Calculator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import stud.ntnu.Calculator.model.Calculation;
import stud.ntnu.Calculator.utility.CalculationRowMapper;

import java.util.List;

@Repository
public class CalculationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCalculation(String expression, String result, int userId) {
        System.out.println("Attempting to save calculation with user_id: " + userId);
        String sql = "INSERT INTO calculation (expression, result, user_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, expression, result, userId);
    }

    public List<Calculation> findCalculationsByUserId(int userId) {
        String sql = "SELECT expression, result FROM calculation WHERE user_id = ? LIMIT 10";
        return jdbcTemplate.query(sql, new Object[]{userId}, new CalculationRowMapper());
    }
}
