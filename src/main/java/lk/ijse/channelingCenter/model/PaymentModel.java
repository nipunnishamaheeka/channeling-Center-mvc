package lk.ijse.channelingCenter.model;

import com.mysql.cj.util.StringInspector;
import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.PaymentDto;

import java.sql.*;

public class PaymentModel {
    public boolean savePayment(final PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into payment values(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPayment_id());
        pstm.setDate(2, dto.getPayment_date());
        pstm.setTime(3, dto.getPayment_time());
        pstm.setDouble(4, dto.getAmount());
        pstm.setString(5, dto.getAppoinment_id());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updatePayment(final PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE payment SET payment_id = ?,payment_date = ?,payment_time = ?,amount = ?,appointment_id = ?  WHERE  = payment_id?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPayment_id());
        pstm.setDate(2, dto.getPayment_date());
        pstm.setTime(3, dto.getPayment_time());
        pstm.setDouble(4, dto.getAmount());
        pstm.setString(5, dto.getAppoinment_id());

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
            Date payment_date = resultSet.getDate(2);
            Time payment_time = resultSet.getTime(3);
            Double amount = resultSet.getDouble(4);
            String appoinment_id = resultSet.getString(5);


            dto = new PaymentDto(payment_id,payment_date,payment_time,amount,appoinment_id);
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
    public String autoGenaratePatientId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM payment ORDER BY payment_id DESC LIMIT 1").executeQuery();
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
            String[] tempArray = current.split("PA");
            int id = Integer.parseInt(tempArray[1]);
            id++;
            if (9 >= id && id > 0) return "PA00" + id;
            else if (99 >= id && id > 9) return "PA0" + id;
            else return "PA" + id;
        }
        return "PA001";
    }


}
