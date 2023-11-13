package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean saveEmployee(final EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into employee values(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmp_id());
        pstm.setString(2, dto.getEmp_name());
        pstm.setString(3, dto.getEmp_address());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getJob_role());
        pstm.setString(6, dto.getQualification());
        pstm.setString(7, dto.getSalary());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public static boolean updateEmployee(final EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET emp_name = ?, emp_address = ?, email = ?, job_role = ?, qualification = ?, salary = ? WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmp_name());
        pstm.setString(2, dto.getEmp_address());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getJob_role());
        pstm.setString(5, dto.getQualification());
        pstm.setString(6, dto.getSalary());
        pstm.setString(7, dto.getEmp_id());

        return pstm.executeUpdate() > 0;
    }

    public static EmployeeDto searchEmployee(String Emp_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Emp_id);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {

            String emp_id = resultSet.getString(1);
            String emp_name = resultSet.getString(2);
            String emp_address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String job_role = resultSet.getString(5);
            String qualification = resultSet.getString(6);
            String salary = resultSet.getString(7);


            dto = new EmployeeDto(emp_id, emp_name, emp_address, email, job_role, qualification, salary);
        }

        return dto;
    }

    public static boolean deleteEmployee(String emp_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, emp_id);

        return pstm.executeUpdate() > 0;
    }

}
