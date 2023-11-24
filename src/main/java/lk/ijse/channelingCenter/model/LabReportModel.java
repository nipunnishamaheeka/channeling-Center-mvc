package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.dto.LabDto;
import lk.ijse.channelingCenter.dto.LabReportDto;
import lk.ijse.channelingCenter.dto.PatientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabReportModel {
    public boolean saveLabReport(final LabReportDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into LabReport values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getLab_reportid());
        pstm.setString(2, dto.getPatient_id());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getDoctor_id());
        pstm.setString(5, dto.getDoctor_name());
        pstm.setString(6, dto.getAge());
        pstm.setString(7, dto.getGender());
        pstm.setString(8, dto.getPatient_name());
        pstm.setString(9, dto.getTest_name());
        pstm.setString(10, dto.getTest_result());
        pstm.setString(11, dto.getUnits());
        pstm.setString(12, dto.getOthers());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;


    }

    /*public boolean updateLabReport(final LabReportDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE labReport SET l_report = ?,date = ?,time = ? WHERE  = patient_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPatient_id());
        pstm.setString(2, dto.getL_report());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getTime());

        return pstm.executeUpdate() > 0;
    }*/

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

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM labreport ORDER BY lab_reportId DESC LIMIT 1").executeQuery();
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

    public LabReportDto searchLabReport(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM labreport WHERE lab_reportId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        LabReportDto dto = null;

        if (resultSet.next()) {

            String lab_reportId = resultSet.getString(1);
            String patient_id = resultSet.getString(2);
            String date = resultSet.getString(3);
            String doctor_id = resultSet.getString(4);
            String doctor_name = resultSet.getString(5);
            String age = resultSet.getString(6);
            String gender = resultSet.getString(7);
            String patient_name = resultSet.getString(8);
            String test_name = resultSet.getString(9);
            String test_result = resultSet.getString(10);
            String units = resultSet.getString(11);
            String others = resultSet.getString(12);


            dto = new LabReportDto(lab_reportId,patient_id,doctor_name,date,doctor_id,age,gender,patient_name,test_name,test_result,units,others);
        }

        return dto;
    }

    public List<LabReportDto> getAllReports() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM labreport";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<LabReportDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String lab_reportId = resultSet.getString(1);
            String patient_id = resultSet.getString(2);
            String date = resultSet.getString(3);
            String doctor_id = resultSet.getString(4);
            String doctor_name = resultSet.getString(5);
            String age = resultSet.getString(6);
            String gender = resultSet.getString(7);
            String patient_name = resultSet.getString(8);
            String test_name = resultSet.getString(9);
            String test_result = resultSet.getString(10);
            String units = resultSet.getString(11);
            String others = resultSet.getString(12);


            var dto = new LabReportDto(lab_reportId,patient_id,date,doctor_id,doctor_name,age,gender,patient_name,test_name,test_result,units,others);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public boolean updateLabReport(LabReportDto ReportDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE labreport SET patient_id = ?,date = ?,doctor_id = ?,doctor_name = ?,age = ?, gender = ? patient_id = ?,test_name = ?, test_result = ?, units = ? , others = ? WHERE  = lab_reportId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, ReportDto.getLab_reportid());
        pstm.setString(2, ReportDto.getPatient_id());
        pstm.setString(3, ReportDto.getDate());
        pstm.setString(4, ReportDto.getDoctor_id());
        pstm.setString(5, ReportDto.getDoctor_name());
        pstm.setString(6, ReportDto.getAge());
        pstm.setString(7, ReportDto.getGender());
        pstm.setString(8, ReportDto.getPatient_name());
        pstm.setString(9, ReportDto.getTest_name());
        pstm.setString(10, ReportDto.getTest_result());
        pstm.setString(11, ReportDto.getUnits());
        pstm.setString(12, ReportDto.getOthers());


        return pstm.executeUpdate() > 0;

    }

    public boolean deleteReports(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM labreport WHERE lab_reportId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        return pstm.executeUpdate() > 0;
    }
}
