package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.dto.MedicineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineModel {
    public boolean saveMedicine(final MedicineDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into medicine values(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getMedi_code());
        pstm.setString(2, dto.getStock());
        pstm.setString(3, dto.getSupplier_id());
        pstm.setString(4, dto.getLocation());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateMedicine(final MedicineDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE medicine SET stock = ?,supplier_id = ?,location = ? WHERE  = medi_code?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getMedi_code());
        pstm.setString(2, dto.getStock());
        pstm.setString(3, dto.getSupplier_id());
        pstm.setString(4, dto.getLocation());

        return pstm.executeUpdate() > 0;
    }

    public MedicineDto searchMedicine(String Medi_code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM medicine WHERE medi_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Medi_code);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String emp_name = resultSet.getString(2);
            String emp_address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String qualification = resultSet.getString(5);


            dto = new EmployeeDto(emp_id, emp_name, emp_address, email, qualification);
        }

        return dto;
    }

    public boolean deleteEmployee(String emp_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, emp_id);

        return pstm.executeUpdate() > 0;
    }

}
