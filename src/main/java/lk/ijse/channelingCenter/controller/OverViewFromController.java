package lk.ijse.channelingCenter.controller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.tm.AppoinmentTm;
import lk.ijse.channelingCenter.dto.tm.DoctorTm;
import lk.ijse.channelingCenter.model.AppoinmentModel;
import lk.ijse.channelingCenter.model.DoctorModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OverViewFromController implements Initializable {

    public AnchorPane overViewPane;
    public PieChart pieChart;
    public TableColumn colDrId;
    public TableColumn colDrName;
    public TableColumn colStatus;
    public TableColumn colAppointmentId;
    public TableColumn colPatientName;
    public TableColumn colDate;
    public TableColumn colAction;
    public TableView tblBookedAppoinment;
    public TableView tblDoctorList;
    AppoinmentModel appoinmentModel = new AppoinmentModel();
    DoctorModel doctorModel = new DoctorModel();

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(
                new PieChart.Data("Acetaminophen", 10),
                new PieChart.Data("Adderall", 20),
                new PieChart.Data("Amitriptyline", 30),
                new PieChart.Data("Amlodiphine", 40),
                new PieChart.Data("Amoxicillin", 50),
                new PieChart.Data("Ativan", 100)
        );

        // Set colors for each category
//        observableList.get(0).getNode().setStyle("-fx-pie-color: #1f77b4;");  // Acetaminophen - Blue
//        observableList.get(1).getNode().setStyle("-fx-pie-color: #ff7f0e;");  // Adderall - Orange
//        observableList.get(2).getNode().setStyle("-fx-pie-color: #2ca02c;");  // Amitriptyline - Green
//        observableList.get(3).getNode().setStyle("-fx-pie-color: #d62728;");  // Amlodiphine - Red
//        observableList.get(4).getNode().setStyle("-fx-pie-color: #9467bd;");  // Amoxicillin - Purple
//        observableList.get(5).getNode().setStyle("-fx-pie-color: #8c564b;");  // Ativan - Brown

        pieChart.setData(observableList);
        loadAllAppoinments();
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() throws SQLException {
        try {
            List<DoctorDto> dtoList = doctorModel.getAllDoctor();

            ObservableList<DoctorTm> obList = FXCollections.observableArrayList();

            for (DoctorDto dto : dtoList) {
                Button deleteButton = new Button("");
                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                deleteButton.setGraphic(deleteIcon);
                deleteButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes) {
                        int selectedIndex = tblDoctorList.getSelectionModel().getSelectedIndex();
                        String code = dto.getId(); // Use the code directly from the DTO

                        deleteItem(code);   // Delete item from the database

                        obList.remove(selectedIndex);   // Delete item from the JFX-Table
                    }
                });

                var tm = new DoctorTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getNumber(),
                        dto.getType(),
                        String.valueOf(dto.getDrFee()),
                        deleteButton
                );
                obList.add(tm);
            }

            tblDoctorList.setItems(obList);
            setFontAwesomeIcons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllAppoinments() throws SQLException {
        try {
            List<AppoinmentDto> dtoList = appoinmentModel.getAllAppoinment();

            ObservableList<AppoinmentTm> obList = FXCollections.observableArrayList();

            for (AppoinmentDto dto : dtoList) {
                Button deleteButton = new Button("");
                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                deleteButton.setGraphic(deleteIcon);
                deleteButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no) == yes) {
                        int selectedIndex = tblBookedAppoinment.getSelectionModel().getSelectedIndex();
                        String code = dto.getAppoinment_id(); // Use the code directly from the DTO

                        deleteItem(code);   // Delete item from the database

                        obList.remove(selectedIndex);   // Delete item from the JFX-Table
                    }
                });

                var tm = new AppoinmentTm(
                        dto.getAppoinment_id(),
                        dto.getDate(),
                        dto.getPatinet_id(),
                        dto.getAge(),
                        dto.getId(),
                        dto.getDoctor_name(),
                        dto.getPatinetName(),
                        dto.getStatus(),
                        deleteButton

                );
                obList.add(tm);
            }

            tblBookedAppoinment.setItems(obList);
            setFontAwesomeIcons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setFontAwesomeIcons() {
        tblBookedAppoinment.getItems().forEach(item -> {
            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        });
        tblDoctorList.getItems().forEach(item -> {
            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        });
    }

    private void deleteItem(String code) {
        try {
            boolean b = appoinmentModel.deleteAppoinment(code);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void deleteDoctor(String code) {
        try {
            boolean b = doctorModel.deleteDoctor(code);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void setCellValueFactory() {
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appoinment_id"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        colDrId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDrName.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

    }

    public void btnPatientOnAction(MouseEvent mouseEvent) throws IOException {
        overViewPane.getChildren().clear();
        overViewPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/patientFrom.fxml")));

    }

    public void btnAppoinmentOnAction(MouseEvent mouseEvent) throws IOException {
        overViewPane.getChildren().clear();
        overViewPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/appoinmentFrom.fxml")));

    }

    public void btnDoctorOnAction(MouseEvent mouseEvent) throws IOException {
        overViewPane.getChildren().clear();
        overViewPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/doctorFrom.fxml")));
    }

    public void btnMedicineOnAction(MouseEvent mouseEvent) throws IOException {
        overViewPane.getChildren().clear();
        overViewPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/medicineFrom.fxml")));

    }
}
