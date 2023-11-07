package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.dto.LabDto;
import lk.ijse.channelingCenter.dto.LabReportDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LabReportModel {
    public boolean saveLabReport(final LabReportDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into LabReport values(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getL_report());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getTime());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;


    }

    public boolean updateLabReport(final LabReportDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE labReport SET l_report = ?,date = ?,time = ? WHERE  = patient_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getL_report());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getTime());

        return pstm.executeUpdate() > 0;
    }

    /*public EmployeeDto searchLabReport(String patient_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM labReport WHERE patient_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, patient_id);

        ResultSet resultSet = pstm.executeQuery();

        LabReportDto dto = null;

        if (resultSet.next()) {
            String labReport_id = resultSet.getString(1);

        }
    }*/
}
