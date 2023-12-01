package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.LoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public boolean saveUser(LoginDto loginDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO login VALUES (?,?,?,?) ");

        pstm.setString(1, loginDto.getFullName());
        pstm.setString(2, loginDto.getUserName());
        pstm.setString(3, loginDto.getPassword());
        pstm.setString(4, loginDto.getEmail());


        return pstm.executeUpdate() > 0;
    }

    public boolean searchUser(LoginDto loginDto) throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM login WHERE user_name=? AND password=?");
        pstm.setString(1, loginDto.getUserName());
        pstm.setString(2, loginDto.getPassword());

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
    public LoginDto getUserByEmail(String email) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM login WHERE email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return new LoginDto(
                        resultSet.getString("full_name"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        }
        return null;
    }

    public void updateUser(LoginDto loginDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE login SET password = ? WHERE email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, loginDto.getPassword());
            pstm.setString(2, loginDto.getEmail());
            pstm.executeUpdate();
        }
    }

}
