package lk.ijse.channelingCenter.model;


import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.PaymentDto;
import lk.ijse.channelingCenter.dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {
    public boolean saveSalary(final SalaryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into salary values(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSalary_id());
        pstm.setString(2, dto.getEmp_id());
        pstm.setString(3, dto.getMonth());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateSalary(final SalaryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE payment SET salary_id = ?,emp_id = ?,month = ?  WHERE  = salary_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSalary_id());
        pstm.setString(1, dto.getEmp_id());
        pstm.setString(1, dto.getMonth());


        return pstm.executeUpdate() > 0;
    }

    public SalaryDto searchSalary(String Salary_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM salary WHERE salary_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Salary_id);

        ResultSet resultSet = pstm.executeQuery();

        SalaryDto dto = null;

        if (resultSet.next()) {
            String salary_id= resultSet.getString(1);
            String emp_id = resultSet.getString(2);
            String month = resultSet.getString(3);


            dto = new SalaryDto(salary_id,emp_id,month);
        }

        return dto;
    }

    public boolean deleteSalary(String salary_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM salry WHERE salary_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, salary_id);

        return pstm.executeUpdate() > 0;
    }

}

