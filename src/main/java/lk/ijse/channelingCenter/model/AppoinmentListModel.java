package lk.ijse.channelingCenter.model;



import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.AppoinmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentListModel {
    /* public static boolean add(AppoinmentDto appoinmentDto) throws SQLException, ClassNotFoundException {
         return CrudUtil.crudUtil("INSERT INTO appoinment VALUES (?,?,?,?,?,?,?)",
                 appoinmentDto.getAppoinment_id(),
                 appoinmentDto.getTime(),
                 appoinmentDto.getDate(),
                appoinmentDto.getPatient_name(),
                 appoinmentDto.getAge(),
                 appoinmentDto.getDr_name(),
                 appoinmentDto.getFee_status()
         );
     }*/
    public static boolean saveAppoinment(final AppoinmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "insert into appoinment values(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getAppoinment_id());
        pstm.setString(2, dto.getTime());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getPatient_name());
        pstm.setString(5, dto.getDr_name());
        pstm.setString(6, dto.getFee_status());
        pstm.setString(7, dto.getAge());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    /* public static ArrayList<AppoinmentDto> getAll() throws SQLException {
         ArrayList<AppoinmentDto> list = new ArrayList<>();
         ResultSet set = CrudUtil.crudUtil("SELECT appoinment.appoinment_id,appoinment.time,appoinment.date,appoinment.patient_name,appoinment.age,appoinment.dr_name,appoinment.fee_status FROM appoinment");

         while (set.next()) {
             list.add(new AppoinmentDto(
                     set.getString(1),
                     set.getString(2),
                     set.getString(3),
                     set.getString(4),
                     set.getString(5),
                     set.getString(6),
                     set.getString(7)
             ));
         }
         return list;
     }*/
    public static List<AppoinmentDto> getAllAppoinment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appoinment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppoinmentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String appoinment_id = resultSet.getString(1);
            String time = resultSet.getString(2);
            String date = resultSet.getString(3);
            String patient_name = resultSet.getString(4);
            String dr_name = resultSet.getString(5);
            String fee_status = resultSet.getString(6);
            String age = resultSet.getString(7);

            var dto = new AppoinmentDto(appoinment_id, time, date, patient_name, dr_name, fee_status, age);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /*public static ArrayList<AppoinmentDto> getAllFilter(String text) throws SQLException {
        ArrayList<AppoinmentDto> list = new ArrayList<>();
        ResultSet set = CrudUtil.crudUtil("SELECT * FROM appoinment WHERE appoinment_id LIKE ? or time LIKE ? or date LIKE ? or patient_name LIKE ? or patient_age LIKE ? or doctor LIKE ? or fee_status LIKE ?",text+"%",text+"%",text+"%",text+"%",text+"%",text+"%",text+"%");

        while (set.next()) {
            list.add(new AppoinmentDto(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return list;
    }*/
    public static List<AppoinmentDto> getAllFilter() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = ("SELECT * FROM appoinment WHERE appoinment_id LIKE ? or time LIKE ? or date LIKE ? or patient_name LIKE ? or patient_age LIKE ? or doctor LIKE ? or fee_status LIKE ?");
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppoinmentDto> dtoList = new ArrayList<>();

        ResultSet set = pstm.executeQuery();

        while (set.next()) {
            dtoList.add(new AppoinmentDto(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return dtoList;

    }
}
