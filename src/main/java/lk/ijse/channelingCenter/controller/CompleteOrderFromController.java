package lk.ijse.channelingCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.dto.tm.CompleteOrdersTm;
import lk.ijse.channelingCenter.model.AppoinmentModel;

import java.sql.SQLException;
import java.util.List;

public class CompleteOrderFromController {
    @FXML
    private AnchorPane CompleteOrderPane;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAppoinment_id;

    @FXML
    private TableColumn<?, ?> colDocName;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableView<CompleteOrdersTm> tblMedicine;

    public void initialize() {
        setValueFactories();
        loadCompleteOrders();
    }

    private void setValueFactories() {
        colAppoinment_id.setCellValueFactory(new PropertyValueFactory<>("appoinment_id"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadCompleteOrders() {
        AppoinmentModel appoinmentModel = new AppoinmentModel();
        try {
            List<AppoinmentDto> allAppoinment = appoinmentModel.getAllAppoinment();
            ObservableList<CompleteOrdersTm> list = FXCollections.observableArrayList();
            for (AppoinmentDto dto : allAppoinment) {
                if (dto.getStatus().equals("complete")) {
                    Button btn = new Button("Generate");
                    btn.setCursor(Cursor.HAND);

                    btn.setOnAction(event -> {
                        try {
                            new Alert(Alert.AlertType.CONFIRMATION, "Report Generate").show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    list.add(new CompleteOrdersTm(dto.getAppoinment_id(), dto.getPatinetName(), dto.getDoctor_name(), btn));
                }
                tblMedicine.setItems(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
