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

        MedicineDto dto = null;

        if (resultSet.next()) {
            String medi_code = resultSet.getString(1);
            String stock = resultSet.getString(2);
            String supplier_id = resultSet.getString(3);
            String location = resultSet.getString(4);

            dto = new MedicineDto(medi_code, stock, supplier_id, location);
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
                String stock = resultSet.getString(2);
                String supplier_id = resultSet.getString(3);
                String location = resultSet.getString(4);


                var dto = new MedicineDto(medi_code, stock, supplier_id, location);
                dtoList.add(dto);
            }
            return dtoList;
        }


    }


//    public ObservableList getAllMedicine() {
//
//    }

