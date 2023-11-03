package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.dto.DoctorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel {
    public boolean saveDoctor(final DoctorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into doctor values(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getDoc_id());
        pstm.setString(2, dto.getAppoinment_id());
        pstm.setString(3, dto.getSalary_id());
        pstm.setString(4, dto.getQualification());
        pstm.setString(5, dto.getDoc_name());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateDoctor(final DoctorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE doctor SET appoinment_id = ?,salary_id = ?,qulification = ?,doc_name = ? WHERE  = doc_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getDoc_id());
        pstm.setString(2, dto.getAppoinment_id());
        pstm.setString(3, dto.getSalary_id());
        pstm.setString(4, dto.getQualification());
        pstm.setString(5, dto.getDoc_name());

        return pstm.executeUpdate() > 0;
    }

    public DoctorDto searchDoctor(String Doc_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM doctor WHERE doc_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Doc_id);

        ResultSet resultSet = pstm.executeQuery();

        DoctorDto dto = null;

        if (resultSet.next()) {
            String doc_id = resultSet.getString(1);
            String appoinment_id = resultSet.getString(2);
            String salary_id = resultSet.getString(3);
            String qualification = resultSet.getString(4);
            String doc_name = resultSet.getString(5);

            dto = new DoctorDto(doc_id, appoinment_id, salary_id, qualification, doc_name);
        }

        return dto;
    }

    public boolean deleteDoctor(String doc_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM doctor WHERE doc_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, doc_id);

        return pstm.executeUpdate() > 0;
    }

}
