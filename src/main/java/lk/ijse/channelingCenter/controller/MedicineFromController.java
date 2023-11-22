package lk.ijse.channelingCenter.controller;

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
            new Alert(Alert.AlertType.ERROR, "Invalid Medicine Details").show();
            lblCode.requestFocus();
        }
    }

    private void clearFields() {
        //lblCode.setText("");
        txtMedicineName.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUniPrice.setText("");

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
//        String stockText = txtStock.getText();
//        boolean isStockValid = Pattern.compile("^[0-9]+$").matcher(stockText).matches();
//        if (!isStockValid) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Stock Count").show();
//            return false;
//        }
        return true;
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnClerOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }


    private void setCellValueFactory() {
//        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));

        colMediCode.setCellValueFactory(new PropertyValueFactory<>("medi_code"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicine_name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

//        ColDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
//        colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));

    }

    /*public void loadAllMedicine() {
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
                                dto.getMedicine_name(),
                                dto.getDescription(),
                                dto.getQty(),
                                dto.getUnit_price(),
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

    }*/

    private void loadAllMedicine() throws SQLException {

        try {
            List<MedicineDto> dtoList = medicineModel.getAllMedicine();

            ObservableList<MedicineTm> obList = FXCollections.observableArrayList();

            for (MedicineDto dto : dtoList) {
                Button btn = new Button("remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes) {
                        int selectedIndex = tblMedicine.getSelectionModel().getSelectedIndex();
                        String code = (String) colMediCode.getCellData(selectedIndex);

                        deleteItem(code);   //delete item from the database

                        obList.remove(selectedIndex);   //delete item from the JFX-Table
                        tblMedicine.refresh();
                    }
                });

                var tm = new MedicineTm(
                        dto.getMedi_code(),
                        dto.getMedicine_name(),
                        dto.getDescription(),
                        dto.getQty(),
                        dto.getUnit_price()

                );
                obList.add(tm);
            }
            tblMedicine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteItem(String code) {
        try {
            boolean isDeleted = medicineModel.deleteMedicine(code);
            if (isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "medicine Item deleted!").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }
//public AnchorPane medicinePane;
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        medicinePane.getChildren().clear();
        medicinePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/MedicinePlaceOrder.fxml")));


    }
}

