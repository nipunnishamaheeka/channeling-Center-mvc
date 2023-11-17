package lk.ijse.channelingCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.tm.PatientTm;
import lk.ijse.channelingCenter.model.PatientModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientFromController{

    public AnchorPane patientPane;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private TableColumn<?,?> Age;

    @FXML
    private TableColumn<?,?> action;

    @FXML
    private TableColumn<?,?> bloodGroup;

    @FXML
    private TableColumn<?,?> email;

    @FXML
    private TableColumn<?,?> number;

    @FXML
    private TableColumn<?,?> patientID;

    @FXML
    private TableColumn<?,?> patientName;

    @FXML
    private TableColumn<?,?> sex;

private void setCellValueFactory(){
    patientID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
    patientName.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
    number.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
    email.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
    bloodGroup.setCellValueFactory(new PropertyValueFactory<>("blood"));
    sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
    Age.setCellValueFactory(new PropertyValueFactory<>("age"));

}

    public void initialize() {
    setCellValueFactory();
        loadAllPatients();

    }

    private void loadAllPatients() {
        var model = new PatientModel();

        ObservableList<PatientTm> obList = FXCollections.observableArrayList();

        try {
            List<PatientDto> dtoList = PatientModel.getAllPatient();

            for(PatientDto dto : dtoList) {
                obList.add(
                        new PatientTm(
                              dto.getPatient_id(),
                                    dto.getPatient_name(),
                                    dto.getMobile_number(),
                                    dto.getAddress(),
                                    dto.getSex(),
                                    dto.getEmail(),
                                    dto.getAge(),
                                    dto.getBlood()
                        )
                );
            }

            tblPatient.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnaddPatientOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/addPatientFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("addPatinetFrom");
        stage.centerOnScreen();
        stage.show();

    }


    public void btnRefershOnAction(MouseEvent mouseEvent) {
        loadAllPatients();
    }
}
