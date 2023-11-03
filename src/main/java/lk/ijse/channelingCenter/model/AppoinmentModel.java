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
    public boolean saveAppoinment(final AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into appoinment values(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getAppoinment_id());
        pstm.setString(3, dto.getAppoinment_date());
        pstm.setString(4, dto.getAppoinment_time());
        pstm.setString(5, dto.getEmp_id());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateAppoinment(final AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE appoinment SET patient_id = ?,appoinment_date = ?,appoinment_time = ?,emp_id = ? WHERE  = appoinment_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getAppoinment_id());
        pstm.setString(3, dto.getAppoinment_date());
        pstm.setString(4, dto.getAppoinment_time());
        pstm.setString(5, dto.getEmp_id());

        return pstm.executeUpdate() > 0;
    }

    public AppoinmentDto searchAppoinment(String Appoinment_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appoinment WHERE appoinment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Appoinment_id);

        ResultSet resultSet = pstm.executeQuery();

        AppoinmentDto dto = null;

        if (resultSet.next()) {
            String patient_id = resultSet.getString(1);
            String appoinment_id = resultSet.getString(2);
            String appoinment_date = resultSet.getString(3);
            String appoinment_time = resultSet.getString(4);
            String emp_id = resultSet.getString(5);

            dto = new AppoinmentDto(patient_id, appoinment_id, appoinment_date, appoinment_time, emp_id);
        }

        return dto;
    }

    public List<AppoinmentDto> getAllAppoinment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appoinment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppoinmentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String patient_id = resultSet.getString(1);
            String appoinment_id = resultSet.getString(2);
            String appoinment_date = resultSet.getString(3);
            String appoinment_time = resultSet.getString(4);
            String emp_id = resultSet.getString(5);


            var dto = new AppoinmentDto(patient_id, appoinment_id, appoinment_date, appoinment_time, emp_id);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean deleteAppoiment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM appoinment WHERE appoinment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<AppoinmentDto> LoadAllAppoinment() throws SQLException {
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
                    resultSet.getString(5)
            ));
        }
        return dtoList;
    }
}