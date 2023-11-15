package lk.ijse.channelingCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.tm.DoctorTm;
import lk.ijse.channelingCenter.model.DoctorModel;

import javax.swing.border.EmptyBorder;
import java.sql.SQLException;
import java.util.List;

import static lk.ijse.channelingCenter.model.DoctorModel.deleteDoctor;

public class DoctorFromController {
    public AnchorPane doctorPane;
    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblEmail;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblNumber;

    @FXML
    private TableColumn<?, ?> tblType;

    @FXML
    private TableView<DoctorTm> tblDoctor;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtType;

public void initialize() throws SQLException {
    setCellValueFactory();
    loadAllItems();

    }
    private void setCellValueFactory(){
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
       tblNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));


    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String number = txtNumber.getText();
        String type = txtType.getText();

        var DoctorDto = new DoctorDto(id, name, address, email, number, type);

        try {
            boolean isSaved = DoctorModel.saveDoctor(DoctorDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Doctor saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String number = txtNumber.getText();
        String type = txtType.getText();

        try {
            boolean isUpdated = DoctorModel.updateDoctor(new DoctorDto(id, name, address, email, number, type));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Doctor updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = deleteDoctor(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Doctor deleted!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void idSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            DoctorDto dto = DoctorModel.searchDoctor(id);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Doctor not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void setFields(DoctorDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtEmail.setText(dto.getEmail());
        txtNumber.setText(dto.getNumber());
        txtType.setText(dto.getType());
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtNumber.clear();
        txtType.clear();
    }
    private void loadAllItems() throws SQLException {
        var model = new DoctorModel();

        ObservableList<DoctorTm> obList = FXCollections.observableArrayList();

        List<DoctorDto> dtoList = DoctorModel.getAllDoctor();

        for(DoctorDto dto : dtoList) {
            obList.add(
                    new DoctorTm(
                            dto.getId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getEmail(),
                            dto.getNumber(),
                            dto.getType()
                    )
            );
        }

        tblDoctor.setItems(obList);


    }
    public void btnClerOnAction(ActionEvent actionEvent) {
        clearFields();
    }


    public void btnRefershOnAction(MouseEvent mouseEvent) throws SQLException {
    loadAllItems();
    }
}
