package lk.ijse.channelingCenter.model;

import lk.ijse.channelingCenter.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderModel {

    public String autoGenaratePatientId() throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM `order` ORDER BY order_id DESC LIMIT 1").executeQuery();
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
                String[] tempArray = current.split("OR");
                int id = Integer.parseInt(tempArray[1]);
                id++;
                if (9 >= id && id > 0) return "OR00" + id;
                else if (99 >= id && id > 9) return "OR0" + id;
                else return "OR" + id;
            }
            return "OR001";
        }
    }

