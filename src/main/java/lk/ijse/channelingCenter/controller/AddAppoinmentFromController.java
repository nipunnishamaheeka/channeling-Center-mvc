package lk.ijse.channelingCenter.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.model.AppoinmentModel;
import lk.ijse.channelingCenter.model.DoctorModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AddAppoinmentFromController {
    public Label lblPatientId;
    @FXML
    private AnchorPane addPatient;

    @FXML
    private JFXComboBox<String> cmbPatientId;

    @FXML
    private JFXComboBox<String> cmbDoctorId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblAppoinmentId;

    @FXML
    private TextField txtAge;

    public void initialize() throws SQLException {
        setAppoinmentId();
        //setPatientID();
        loadPatientsIds();
        loadDoctorIds();
        //loadAPatientIds();

    }

    private void setAppoinmentId() {
        try {
            lblAppoinmentId.setText(new AppoinmentModel().autoGenarateId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  /*  private void setPatientID(){
        try{
            cmbPatientId.setId(new AppoinmentModel().autoGenarateId());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }*/
//  private void setPatientID() {
//      try {
//          lblPatientId.setId(new AppoinmentModel().patientautoGenarateId());
//      } catch (SQLException e) {
//          System.out.println(e.getMessage());
//      }
//  }

    @FXML
    void btnClerOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        setAppoinmentId();
       // setPatientID();


    }

    @FXML
    void cmbPatientOnAction(ActionEvent event) {
        try {
            String name = new PatientModel().getPatientName(cmbPatientId.getValue());
            lblPatientName.setText(name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadPatientsIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PatientDto> cusList = new PatientModel().getAllPatient();

            for (PatientDto dto : cusList) {
                obList.add(dto.getPatient_id());
            }
            cmbPatientId.setItems(obList);
            //cmbPatientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDoctorIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<DoctorDto> cusList = new DoctorModel().getAllDoctor();

            for (DoctorDto dto : cusList) {
                obList.add(dto.getId());
            }

            cmbDoctorId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   /*private void loadAPatientIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PatientDto> cusList = new PatientModel().getAllPatient();

            for (PatientDto dto : cusList) {
                obList.add(dto.getPatient_id());
            }

            lblPatientId.setId(obList.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    public void btnaddOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/addAppoinmentPatinetDetials.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("addAppoinmentFrom");
        stage.centerOnScreen();
        stage.show();
    }
}
