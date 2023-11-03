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
    public boolean savePatient(final PatientDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into patient values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getPatient_name());
        pstm.setString(3, dto.getMobile_number());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getSex());
        pstm.setString(6, dto.getEmail());
        pstm.setString(7, dto.getBlood());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updatePatient(final PatientDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE patient SET patient_name = ?,mobile_number = ?,address = ?,sex = ?,email = ?,blood =? WHERE  = patient_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_name());
        pstm.setString(2, dto.getMobile_number());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getSex());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getBlood());

        return pstm.executeUpdate() > 0;
    }

    public PatientDto searchPatient(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        PatientDto dto = null;

        if(resultSet.next()) {
            String Patient_id = resultSet.getString(1);
            String Patient_name = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Sex = resultSet.getString(4);
            String Email = resultSet.getString(5);
            String Blood = resultSet.getString(6);

            dto = new PatientDto(Patient_id,Patient_name,Address,Sex,Email,Blood);
        }

        return dto;
    }
    public List<PatientDto> getAllPatient() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM patient";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PatientDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String Patient_id = resultSet.getString(1);
            String Patient_name = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String Sex = resultSet.getString(4);
            String Email = resultSet.getString(5);
            String Blood = resultSet.getString(6);


            var dto = new PatientDto(Patient_id,Patient_name,Address,Sex,Email,Blood);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean deletePatient(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM patient WHERE patient_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<PatientDto> LoadAllPatient() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Patient";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<PatientDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new PatientDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return dtoList;
    }

}