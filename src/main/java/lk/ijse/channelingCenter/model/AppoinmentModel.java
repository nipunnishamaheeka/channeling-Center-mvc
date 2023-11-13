package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.AppoinmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentModel {
    public static boolean saveAppoinment(final AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into appoinment values(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getAppoinment_id());
        pstm.setString(2, dto.getTime());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getId());
        pstm.setString(5, dto.getPatient_id());
        pstm.setString(6, dto.getFee_status());
        pstm.setString(7, dto.getAge());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public static boolean updateAppoinment(final AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE appoinment SET patient_id = ?,date = ?,time = ?,id = ?,fee_status = ?,age = ? WHERE  = appoinment_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getAppoinment_id());
        pstm.setString(2, dto.getTime());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getPatient_id());
        pstm.setString(5, dto.getId());
        pstm.setString(6, dto.getFee_status());
        pstm.setString(7, dto.getAge());

        return pstm.executeUpdate() > 0;
    }

   /* public static AppoinmentDto searchAppoinment(String Appoinment_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appoinment WHERE appoinment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Appoinment_id);

        ResultSet resultSet = pstm.executeQuery();

        AppoinmentDto dto = null;

        if (resultSet.next()) {
            String appoinment_id = resultSet.getString(1);
            String time = resultSet.getString(2);
            String date = resultSet.getString(3);
            String patient_name = resultSet.getString(4);
            String dr_name = resultSet.getString(5);
            String fee_status = resultSet.getString(6);
            String age = resultSet.getString(7);

            dto = new AppoinmentDto(appoinment_id, time, date,patient_name, dr_name,fee_status,age);
        }

        return dto;
    }*/

   /* public List<AppoinmentDto> getAllAppoinment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appoinment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppoinmentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String appoinment_id = resultSet.getString(1);
            String time = resultSet.getString(2);
            String date = resultSet.getString(3);
            String patient_name = resultSet.getString(4);
            String dr_name = resultSet.getString(5);
            String fee_status = resultSet.getString(6);
            String age = resultSet.getString(7);

            var dto = new AppoinmentDto(appoinment_id, time, date,patient_name, dr_name,fee_status,age);
            dtoList.add(dto);
        }
        return dtoList;
    }*/

    public static boolean deleteAppoinment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM appoinment WHERE appoinment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }


   /* public List<AppoinmentDto> LoadAllAppoinment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appoinment";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<AppoinmentDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new AppoinmentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return dtoList;
    }*/
}