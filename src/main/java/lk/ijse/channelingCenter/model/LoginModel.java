package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.LoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginModel {
    public boolean saveUser(LoginDto loginDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO users VALUES (?,?,?) ");

        pstm.setString(1,loginDto.getFullName());
        pstm.setString(2,loginDto.getUserName());
        pstm.setString(3, loginDto.getPassword());


        return pstm.executeUpdate()>0;
    }
}
