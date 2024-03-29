package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.PatientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel {
    public static String getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) FROM doctor";
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
    public boolean saveDoctor(DoctorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into doctor values(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getNumber());
        pstm.setString(6, dto.getType());
        pstm.setDouble(7, dto.getDrFee());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean updateDoctor(final DoctorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE doctor SET doctor_name = ?,address = ?,email = ?,number = ?,type = ?, drFee =? WHERE  id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getNumber());
        pstm.setString(5, dto.getType());
        pstm.setObject(6, dto.getDrFee());
        pstm.setString(7, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public DoctorDto searchDoctor(String Doc_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM doctor WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Doc_id);

        ResultSet resultSet = pstm.executeQuery();

        DoctorDto dto = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String number = resultSet.getString(5);
            String type = resultSet.getString(6);
            double fee = resultSet.getDouble(7);

            dto = new DoctorDto(id, name, address, email, number, type,fee);
        }

        return dto;
    }

    public boolean deleteDoctor(String doc_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM doctor WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, doc_id);

        return pstm.executeUpdate() > 0;
    }
    public List<DoctorDto> loadAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM doctor";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<DoctorDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new DoctorDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }
    public List<DoctorDto> getAllDoctor() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM doctor";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<DoctorDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String number = resultSet.getString(5);
            String type = resultSet.getString(6);
            double fee = resultSet.getDouble(7);

            var dto = new DoctorDto(id, name, address, email, number, type,fee);
            dtoList.add(dto);
        }
        return dtoList;
    }
    public String autoGenarateDoctorId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM doctor ORDER BY id DESC LIMIT 1").executeQuery();
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
            String[] tempArray = current.split("D");
            int id = Integer.parseInt(tempArray[1]);
            id++;
            if (9 >= id && id > 0) return "D00" + id;
            else if (99 >= id && id > 9) return "D0" + id;
            else return "D" + id;
        }
        return "D001";
    }

    public String getname(String value) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT doctor_name FROM doctor WHERE id=?");
        pstm.setString(1,value);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next())return resultSet.getString(1);
        return null;
    }

    public DoctorDto searchDoctorByNumber(String number) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM doctor WHERE number = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, number);

        ResultSet resultSet = pstm.executeQuery();

        DoctorDto dto = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String mobileNumber = resultSet.getString(5);
            String type = resultSet.getString(6);
            double fee = resultSet.getDouble(7);

            dto = new DoctorDto(id, name, address, email, mobileNumber, type,fee);
        }

        return dto;
    }

    public double getfee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT drFee FROM doctor WHERE id=?");
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next())return resultSet.getDouble(1);
        return 0;
    }
}
