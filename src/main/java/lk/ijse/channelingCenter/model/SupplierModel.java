package lk.ijse.channelingCenter.model;


import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.SalaryDto;
import lk.ijse.channelingCenter.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public boolean saveSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into supplier values(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupplier_id());
        pstm.setString(2, dto.getSupplier_name());
        pstm.setString(3, dto.getLocation());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET supplier_id = ?,supplier_name = ?,location = ?  WHERE  = supplier_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupplier_id());
        pstm.setString(1, dto.getSupplier_name());
        pstm.setString(1, dto.getLocation());


        return pstm.executeUpdate() > 0;
    }

    public SupplierDto searchSupplier(String Supplier_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Supplier_id);

        ResultSet resultSet = pstm.executeQuery();

        SupplierDto dto = null;

        if (resultSet.next()) {
            String supplier_id = resultSet.getString(1);
            String supplier_name = resultSet.getString(2);
            String location = resultSet.getString(3);


            dto = new SupplierDto(supplier_id, supplier_name, location);
        }

        return dto;
    }

    public boolean deleteSupplier(String supplier_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplier_id);

        return pstm.executeUpdate() > 0;
    }

    public String autoGenarateSupplierId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM supplier ORDER BY supplier_id DESC LIMIT 1").executeQuery();
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
            String[] tempArray = current.split("S");
            int id = Integer.parseInt(tempArray[1]);
            id++;
            if (9 >= id && id > 0) return "S00" + id;
            else if (99 >= id && id > 9) return "S0" + id;
            else return "S" + id;
        }
        return "S001";
    }

    public List<SupplierDto> getAllSupplier() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM supplier";
            PreparedStatement pstm = connection.prepareStatement(sql);

            List<SupplierDto> dtoList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                String Supplier_id= resultSet.getString(1);
                String Supplier_Name = resultSet.getString(2);
                String Location = resultSet.getString(3);


                var dto = new SupplierDto(Supplier_id, Supplier_Name, Location);
                dtoList.add(dto);
            }
            return dtoList;
        }

    }
