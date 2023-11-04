package lk.ijse.channelingCenter.model;

import com.mysql.cj.util.StringInspector;
import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public boolean savePayment(final PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into doctor values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPayment_id());
        pstm.setString(2, dto.getPayment_email());
        pstm.setString(3, dto.getPayment_date());
        pstm.setString(4, dto.getPayment_time());
        pstm.setString(5, dto.getPayment_method());
        pstm.setString(6, dto.getAmount());
        pstm.setString(7, dto.getPatient_id());
        pstm.setString(8, dto.getAppoinment_id());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updatePayment(final PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE payment SET payment_id = ?,payment_email = ?,payment_email = ?,payment_date = ?,payment_time = ?,amount = ?,payment_method = ?,patient_id= ?,appointment_id = ?  WHERE  = payment_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPayment_id());
        pstm.setString(1, dto.getPayment_email());
        pstm.setString(1, dto.getPayment_date());
        pstm.setString(1, dto.getPayment_time());
        pstm.setString(1, dto.getAmount());
        pstm.setString(1, dto.getPayment_method());
        pstm.setString(1, dto.getPatient_id());
        pstm.setString(1, dto.getAppoinment_id());

        return pstm.executeUpdate() > 0;
    }

    public PaymentDto searchPayment(String Doc_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payment WHERE payment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Doc_id);

        ResultSet resultSet = pstm.executeQuery();

        PaymentDto dto = null;

        if (resultSet.next()) {
            String payment_id = resultSet.getString(1);
            String payment_email = resultSet.getString(2);
            String payment_date = resultSet.getString(3);
            String payment_time = resultSet.getString(4);
            String amount = resultSet.getString(6);
            String payment_method = resultSet.getString(7);
            String patient_id = resultSet.getString(8);
            String appoinment_id = resultSet.getString(9);


            dto = new PaymentDto(payment_id,payment_email,payment_date,payment_time,amount,payment_method,patient_id,appoinment_id);
        }

        return dto;
    }

    public boolean deletePayment(String payment_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM payment WHERE payment_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, payment_id);

        return pstm.executeUpdate() > 0;
    }

}
