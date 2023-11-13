package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicineFromController {
   @FXML
    private AnchorPane medicinePane;

    @FXML
    void btnappoinmentOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/appoinmentFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("Appoinment");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();


    }

    @FXML
    void btndoctorOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/doctorFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("doctorFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void btnemployeeOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/employeeFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("employeeFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();


    }

    @FXML
    void btnlabreportsOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/labReportsFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("labReportsFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnlogoutOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("loginFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnmedicineOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/medicineFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("medicineFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnoverViewOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/overViewFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("overView");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btnpatientOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/patientFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("patientFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) medicinePane.getScene().getWindow();
        stage1.close();

    }

    public void btnaddMedicineOnAction(ActionEvent actionEvent) {
    }
}
