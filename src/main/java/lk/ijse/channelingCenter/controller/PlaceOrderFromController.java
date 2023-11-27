package lk.ijse.channelingCenter.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.dto.MedicineDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.tm.CartTm;
import lk.ijse.channelingCenter.model.MedicineModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFromController {
    public TableColumn colTotal;
    public TableColumn colAction;
    public Label lblTotal;
//    public AnchorPane medicinePlaceOrderPane;
//    public Label lblOrderId;
//    public Label lblDate;
//    public ComboBox<String> cmbPatientId;
//    public Label lblPatientName;
//    public ComboBox <String> cmbMedicineId;
//    public Label lblDescription;
//    public TextField txtQty;
//    public Label lblUnitPrice;
//    @FXML
//    private TableView<?> tblMedicine;

    @FXML
    private TableColumn<?, ?> ColDelete;

    @FXML
    private ComboBox<String> cmbMedicineId;

    @FXML
    private ComboBox<String> cmbPatientId;

    @FXML
    private TableColumn<?, ?> colDiscription;

    @FXML
    private TableColumn<?, ?> colMediCode;

    @FXML
    private TableColumn<?, ?> colMedicineName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane medicinePlaceOrderPane;

    @FXML
    private TableView<CartTm> tblMedicine;

    @FXML
    private TextField txtQty;


    public void initialize() {
        setOrderId();
        this.lblDate.setText(generateRealTime());
        loadPatientsIds();
        loadMedicineIds();
        setCellValuefactory();
    }

    private void setCellValuefactory() {

        colMediCode.setCellValueFactory(new PropertyValueFactory<>("M_Code"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<>("Dis"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("U_price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private String generateRealTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    public void btnBackOnAction(MouseEvent mouseEvent) throws IOException {
        medicinePlaceOrderPane.getChildren().clear();
        medicinePlaceOrderPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/medicineFrom.fxml")));
    }

    private void setOrderId() {
//        try {
//            lblOrderId.setText(new PlaceOrderModel().autoGenaratePatientId());
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
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

    @FXML
    void cmbPatientOnAction(ActionEvent event) {
        try {
            String name = new PatientModel().getPatientName(cmbPatientId.getValue());
            lblPatientName.setText(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadMedicineIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<MedicineDto> cusList = new MedicineModel().getAllMedicine();

            for (MedicineDto dto : cusList) {
                obList.add(dto.getMedi_code());
            }
            cmbMedicineId.setItems(obList);
            //cmbPatientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbMedicineOnAction(ActionEvent event) {
        try {

            MedicineDto dto = new MedicineModel().searchMedicine(cmbMedicineId.getValue());
            lblDescription.setText(dto.getDescription());
            //String price = new MedicineModel().getMedicinePrice(cmbMedicineId.getValue());
            lblUnitPrice.setText(dto.getUnit_price());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void AddbtnOnActhion(ActionEvent actionEvent) {
        /*Button btn = new Button("remove");
        btn.setCursor(Cursor.HAND);

        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int index = tblMedicine.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                tblMedicine.refresh();
            }
        });
        int qty = Integer.parseInt(this.txtQty.getText());
        for (int i = 0; i < tblMedicine.getItems().size(); i++) {
            if (colMediCode.equals(colMediCode.getCellData(i))) {
                qty += (int) colQty.getCellData(i);

                obList.get(i).setQty(String.valueOf(qty));
                tblMedicine.refresh();
                return;
            }
        }
        String code = cmbMedicineId.getValue();
        String Qty = String.valueOf(qty);
        obList.add(new CartTm(code, lblDescription.getText(), Qty, lblUnitPrice.getText(), btn));

        tblMedicine.setItems(obList);
        //calculateNetTotal();
        txtQty.clear();

         */

        String code = (String) cmbMedicineId.getValue();
        String description = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;


        JFXButton btn = new JFXButton("remove");
        btn.setCursor(Cursor.HAND);

        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int index = tblMedicine.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                tblMedicine.refresh();

                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblMedicine.getItems().size(); i++) {
            if (code.equals(colMediCode.getCellData(i))) {
                qty += (int) colQty.getCellData(i);
                total = qty * unitPrice;

                obList.get(i).setQty(qty);
                obList.get(i).setTot(total);

                tblMedicine.refresh();
                calculateNetTotal();
                return;
            }
        }

        obList.add(new CartTm(
                code,
                description,
                qty,
                unitPrice,
                total,
                btn
        ));

        tblMedicine.setItems(obList);
        calculateNetTotal();
        txtQty.clear();
    }



    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblMedicine.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);

        }

        lblTotal.setText(String.valueOf(total));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }
}

