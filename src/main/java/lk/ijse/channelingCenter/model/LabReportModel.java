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

    public String autoGenarateLabReportId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM labreport ORDER BY patient_id DESC LIMIT 1").executeQuery();
        String current = null;
        while (resultSet.next()) {
            current = resultSet.getString(1);
            System.out.println(current);
            return splitId(current);
        }

        return splitId(null);
    }

    private String splitId(String current) {

        if (current != null) {
            String[] tempArray = current.split("LR");
            int id = Integer.parseInt(tempArray[1]);
            id++;
            if (9 >= id && id > 0) return "LR00" + id;
            else if (99 >= id && id > 9) return "LR0" + id;
            else return "LR" + id;
        }
        return "LR001";
    }
}
