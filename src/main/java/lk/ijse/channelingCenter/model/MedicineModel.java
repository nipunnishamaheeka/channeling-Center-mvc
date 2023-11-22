package lk.ijse.channelingCenter.model;

import javafx.collections.ObservableList;
import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.dto.MedicineDto;
import lk.ijse.channelingCenter.dto.PatientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineModel {
    public boolean saveMedicine(final MedicineDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into medicine values(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getMedi_code());
        pstm.setString(2, dto.getMedicine_name());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getQty());
        pstm.setString(5, dto.getUnit_price());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateMedicine(final MedicineDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE medicine SET medicine_name = ?,description = ?,qty = ?, unit_price =? WHERE  = medi_code?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getMedi_code());
        pstm.setString(2, dto.getMedicine_name());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getQty());
        pstm.setString(5, dto.getUnit_price());


        return pstm.executeUpdate() > 0;
    }

    public MedicineDto searchMedicine(String Medi_code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM medicine WHERE medi_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Medi_code);

        ResultSet resultSet = pstm.executeQuery();

        MedicineDto dto = null;

        if (resultSet.next()) {
            String medi_code = resultSet.getString(1);
            String medicine_name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String unit_price = resultSet.getString(5);


            dto = new MedicineDto(medi_code, medicine_name, description, qty, unit_price);
        }

        return dto;
    }

    public boolean deleteMedicine(String medi_code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM medicine WHERE medi_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, medi_code);

        return pstm.executeUpdate() > 0;
    }

    public String autoGenarateMedicineId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM medicine ORDER BY medi_code DESC LIMIT 1").executeQuery();
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
            String[] tempArray = current.split("M");
            int id = Integer.parseInt(tempArray[1]);
            id++;
            if (9 >= id && id > 0) return "M00" + id;
            else if (99 >= id && id > 9) return "M0" + id;
            else return "M" + id;
        }
        return "M001";
    }

    public List<MedicineDto> getAllMedicine() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM medicine";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<MedicineDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String medi_code = resultSet.getString(1);
            String medicine_name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String unit_price = resultSet.getString(5);

            var dto = new MedicineDto(medi_code, medicine_name, description, qty, unit_price);
            dtoList.add(dto);
        }
        return dtoList;
    }
    public String getMedicineDescription(String value) throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM patient WHERE patient_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, value);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getString(3);
        } return null;

    }

    public String getMedicinePrice(String value) throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM patient WHERE patient_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, value);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getString(5);
        } return null;

    }
}

