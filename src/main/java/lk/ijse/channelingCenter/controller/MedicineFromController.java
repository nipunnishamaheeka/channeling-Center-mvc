package lk.ijse.channelingCenter.controller;

import animatefx.animation.Shake;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import lk.ijse.channelingCenter.dto.*;
import lk.ijse.channelingCenter.dto.tm.DoctorTm;
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

public class MedicineFromController {
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
    public TextField txtDescription;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn ColDelete;
    public TableColumn colUpdate;
    public TextField txtUniPrice;
    public TextField txtQty;
    MedicineModel medicineModel = new MedicineModel();

    public void initialize() throws SQLException {
        loadAllMedicine();
        setMedicineCode();
        setCellValueFactory();
    }

//    private void loadSupplierNames() {
//
//        ObservableList<String> obList = FXCollections.observableArrayList();
//        try {
//            List<SupplierDto> supiList = new SupplierModel().getAllSupplier();
//
//            for (SupplierDto dto : supiList) {
//                obList.add(dto.getSupplier_name());
//            }
//            //cmbSupplierName.setItems(obList);
//            //cmbPatientId.setItems(obList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void setMedicineCode() {
        try {
            lblCode.setText(new MedicineModel().autoGenarateMedicineId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isMedicineValid = validateMedicine();

        if (isMedicineValid) {
            String id = lblCode.getText();
            String medicineName = txtMedicineName.getText();
            String description = txtDescription.getText();
            String qty = txtQty.getText();
            String unitPrice = txtUniPrice.getText();

            MedicineDto itemDto = new MedicineDto(id, medicineName, description, qty, unitPrice);

            try {
                MedicineModel medicineModel = new MedicineModel();
                boolean isSaved = medicineModel.saveMedicine(itemDto);
                //System.out.println(isSaved);
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
            // new Alert(Alert.AlertType.ERROR, "Invalid Medicine Details").show();
            lblCode.requestFocus();
        }
    }

    private void clearFields() {
        lblCode.setText("");
        txtMedicineName.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUniPrice.setText("");

    }

    private boolean validateTextField(TextField textField, String patternRegex) {
        String text = textField.getText();
        boolean isValid = Pattern.compile(patternRegex).matcher(text).matches();
        if (!isValid) {
            textField.setStyle("-fx-border-color: red");
            new Shake(textField).play();
        } else {
            textField.setStyle("-fx-border-color: green");
        }
        return isValid;
    }

    private boolean validateMedicine() {
        return validateTextField(txtMedicineName, "^[A-Za-z0-9\\s\\-()&,.]+$")
                && validateTextField(txtQty, "^\\d+(\\.\\d+)?$")
                && validateTextField(txtUniPrice, "^\\d+(\\.\\d{1,2})?$");
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnClerOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }


    private void setCellValueFactory() {


        colMediCode.setCellValueFactory(new PropertyValueFactory<>("medi_code"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicine_name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        ColDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

    }

    private void setFontAwesomeIcons() {
        tblMedicine.getItems().forEach(item -> {
            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        });
    }

    private void loadAllMedicine() throws SQLException {
        try {
            List<MedicineDto> dtoList = medicineModel.getAllMedicine();

            ObservableList<MedicineTm> obList = FXCollections.observableArrayList();

            for (MedicineDto dto : dtoList) {
                Button deleteButton = new Button("");
                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                deleteButton.setGraphic(deleteIcon);
                deleteButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes) {
                        int selectedIndex = tblMedicine.getSelectionModel().getSelectedIndex();
                        String code = dto.getMedi_code(); // Use the code directly from the DTO

                        deleteItem(code);   // Delete item from the database

                        obList.remove(selectedIndex);   // Delete item from the JFX-Table
                    }
                });

                var tm = new MedicineTm(
                        dto.getMedi_code(),
                        dto.getMedicine_name(),
                        dto.getDescription(),
                        dto.getQty(),
                        dto.getUnit_price(),
                        deleteButton
                );
                obList.add(tm);
            }

            tblMedicine.setItems(obList);
            setFontAwesomeIcons();

            // Add a click event listener to the table rows
            tblMedicine.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Check for a single click
                    int selectedIndex = tblMedicine.getSelectionModel().getSelectedIndex();
                    if (selectedIndex != -1) {
                        MedicineTm selectedMedicine = obList.get(selectedIndex);

                        // Set the data to your text fields using the DTO
                        setFields(new MedicineDto(
                                selectedMedicine.getMedi_code(),
                                selectedMedicine.getMedicine_name(),
                                selectedMedicine.getDescription(),
                                selectedMedicine.getQty(),
                                selectedMedicine.getUnit_price()
                        ));
                    }
                }
            });


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String code) {
        try {
            boolean isDeleted = medicineModel.deleteMedicine(code);
            if (isDeleted) {
                //new Alert(Alert.AlertType.CONFIRMATION, "Medicine item deleted!").show();
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }

    public void medicineSearchOnAction(ActionEvent actionEvent) {
        String medicine = txtMedicineName.getText();

        try {
            MedicineDto dto = medicineModel.searchMedicine(medicine);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Medicine not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(MedicineDto dto) {
        lblCode.setText(dto.getMedi_code());
        txtMedicineName.setText(dto.getMedicine_name());
        txtDescription.setText(dto.getDescription());
        txtQty.setText(dto.getQty());
        txtUniPrice.setText(dto.getUnit_price());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }


}

