package lk.ijse.channelingCenter.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NavigationPannelFromController {
    public AnchorPane navigationPane;

    public void btnoverViewOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/overViewFrom.fxml")));
    }

    public void btnpatientOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/patientFrom.fxml")));

    }

    public void btnappoinmentOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/appoinmentFrom.fxml")));

    }

    public void btndoctorOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/doctorFrom.fxml")));

    }

    public void btnlabreportsOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/labReportsFrom.fxml")));
    }

    public void btnmedicineOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/medicineFrom.fxml")));
    }

    public void btnlogoutOnAction(MouseEvent mouseEvent) {

    }

    public void btnemployeeOnAction(MouseEvent mouseEvent) throws IOException {
        navigationPane.getChildren().clear();
        navigationPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/employeeFrom.fxml")));

    }
}
