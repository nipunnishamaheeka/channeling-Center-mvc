package lk.ijse.channelingCenter.model;


import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.PatientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel {
    public static boolean savePatient(final PatientDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into patient values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getPatient_name());
        pstm.setString(3, dto.getMobile_number());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getSex());
        pstm.setString(6, dto.getEmail());
        pstm.setString(7, dto.getAge());
        pstm.setString(8, dto.getBlood());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public static boolean updatePatient(final PatientDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE patient SET patient_name = ?,mobile_number = ?,address = ?,sex = ?,email = ?,age = ?,blood =? WHERE   patient_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_name());
        pstm.setString(2, dto.getMobile_number());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getSex());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getBlood());
        pstm.setString(7, dto.getAge());
        pstm.setString(8, dto.getPatient_id());

        return pstm.executeUpdate() > 0;
    }

    public static PatientDto searchPatient(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        PatientDto dto = null;

        if(resultSet.next()) {
            String Patient_id = resultSet.getString(1);
            String Patient_name = resultSet.getString(2);
            String Mobile_number = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String Sex = resultSet.getString(5);
            String Email = resultSet.getString(6);
            String Age = resultSet.getString(7);
            String Blood = resultSet.getString(8);

            dto = new PatientDto(Patient_id,Patient_name,Mobile_number,Address,Sex,Email,Age,Blood);
        }

        return dto;
    }

    public static boolean deletePatient(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM patient WHERE patient_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<PatientDto> getAllPatient() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM patient";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PatientDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String Patient_id = resultSet.getString(1);
            String Patient_name = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Mobile_number = resultSet.getString(4);
            String Sex = resultSet.getString(5);
            String Email = resultSet.getString(6);
            String Age = resultSet.getString(7);
            String Blood = resultSet.getString(8);


            var dto = new PatientDto(Patient_id,Patient_name,Mobile_number,Address,Sex,Email,Age,Blood);
            dtoList.add(dto);
        }
        return dtoList;
    }
}