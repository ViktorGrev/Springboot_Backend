package stud.ntnu.Calculator.utility;

import org.springframework.jdbc.core.RowMapper;
import stud.ntnu.Calculator.model.Calculation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculationRowMapper implements RowMapper<Calculation> {
    @Override
    public Calculation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Calculation calculation = new Calculation();
        calculation.setExpression(rs.getString("expression"));
        calculation.setResult(rs.getString("result"));
        return calculation;
    }
}
