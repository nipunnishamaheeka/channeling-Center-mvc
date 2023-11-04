package lk.ijse.channelingCenter.model;


import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.SalaryDto;
import lk.ijse.channelingCenter.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
