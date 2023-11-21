package lk.ijse.channelingCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.dto.LabReportDto;
import lk.ijse.channelingCenter.dto.MedicineDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.SupplierDto;
import lk.ijse.channelingCenter.dto.tm.MedicineTm;
import lk.ijse.channelingCenter.dto.tm.PatientTm;
import lk.ijse.channelingCenter.model.MedicineModel;
import lk.ijse.channelingCenter.model.PatientModel;
import lk.ijse.channelingCenter.model.SupplierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class MedicineFromController{
    public AnchorPane medicinePane;
    public Label lblCode;
    public TextField txtMedicineName;
    public TextField txtSupplierName;
    public TextField txtStock;
    public TableView tblMedicine;
    public TableColumn colMediCode;
    public TableColumn colMedicineName;
    public TableColumn colSupplierName;
    public TableColumn colStock;
    public Label lblSupName;
    public ComboBox cmbSupplierName;
    MedicineModel medicineModel = new MedicineModel();

    public void initialize() {
        setMedicineCode();
        //setsupplierName();
        loadAllMedicine();
        loadSupplierNames();
        setCellValueFactory();
    }

    public void btnaddMedicineOnAction(ActionEvent actionEvent) {


    }

    private void loadSupplierNames() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> supiList = new SupplierModel().getAllSupplier();

            for (SupplierDto dto : supiList) {
                obList.add(dto.getSupplier_name());
            }
            cmbSupplierName.setItems(obList);
            //cmbPatientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setMedicineCode() {
        try {
            lblCode.setText(new MedicineModel().autoGenarateMedicineId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private void setsupplierName() {
//        try {
//            lblSupName.setText(new SupplierModel().autoGenarateSupplierId());
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isMedicineValid = validateMedicine();

        if (isMedicineValid) {
            String id = lblCode.getText();
            String medicineName = txtMedicineName.getText();
            String supplierName = colSupplierName.getText();
            String stock = txtStock.getText();

            MedicineDto itemDto = new MedicineDto(id, medicineName, supplierName, stock);

            try {
                MedicineModel medicineModel = new MedicineModel();
                boolean isSaved = medicineModel.saveMedicine(itemDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Medicine saved!").show();
                    clearFields();
                    loadAllMedicine();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Medicine not saved!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Medicine Details").show();
            lblCode.requestFocus();
        }
    }
    private void setCellValueFactory() {
        colMediCode.setCellValueFactory(new PropertyValueFactory<>("medi_code"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicine_name"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
    private void clearFields() {
        lblCode.setText("");
        txtMedicineName.setText("");
        txtSupplierName.setText("");
        txtStock.setText("");

    }

    private boolean validateMedicine() {
        String nameText = txtMedicineName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Medicine Name").show();
            return false;
        }
//        String supplierText = txtSupplierName.getText();
//        boolean isSupplierNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(supplierText).matches();
//        if (!isSupplierNameValid) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name").show();
//            return false;
//        }
        String stockText = txtStock.getText();
        boolean isStockValid = Pattern.compile("^[0-9]+$").matcher(stockText).matches();
        if (!isStockValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Stock Count").show();
            return false;
        }
        return true;
    }

    public void loadAllMedicine() {
        ObservableList<MedicineTm> obList = FXCollections.observableArrayList();

        try {
            List<MedicineDto> dtoList = new MedicineModel().getAllMedicine();

            for (MedicineDto dto : dtoList) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Patient?", yes, no).showAndWait();
                    if (result.orElse(no) == yes) {
                        int selectedIndex = tblMedicine.getSelectionModel().getSelectedIndex();
                        String code = (String) colMediCode.getCellData(selectedIndex);
                        deleteMedicine(code);
                        obList.remove(selectedIndex);
                        tblMedicine.refresh();
                    }
                });
                updateButton.setOnAction((e) -> {
                    int selectedIndex = tblMedicine.getSelectionModel().getSelectedIndex();
                    String code = (String) colMediCode.getCellData(selectedIndex);
                    System.out.println(code);
                    try {
                        medicinePane.getChildren().clear();
                        //patientPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/updatePatientFrom.fxml")));
                    } catch (Exception e1) {
                    }
                });
                obList.add(
                        new MedicineTm(
                                dto.getMedi_code(),
                                dto.getStock(),
                                dto.getSupplier_id(),
                                dto.getLocation(),
                                deleteButton,
                                updateButton
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteMedicine(String code) {
        try {
            boolean b = medicineModel.deleteMedicine(code);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnClerOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}

