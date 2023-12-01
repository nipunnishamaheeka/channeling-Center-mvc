package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompleteOrderModel {
    public boolean saveOrder(String id, List<CartTm> list) throws SQLException {
        for (CartTm tm : list) {
            boolean isSaved = saveOrder(id, tm);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrder(String id, CartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "insert into completeorders values(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, tm.getM_Code());
        pstm.setString(2, id);
        pstm.setInt(3, (int) tm.getQty());
        return pstm.executeUpdate() > 0;
    }

    public String getAllOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) FROM completeorders";
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
