package lk.ijse.channelingCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientFromController {

    @FXML
    private Pane btnappoinment;

    @FXML
    private Pane btndoctor;

    @FXML
    private Pane btnemployee;

    @FXML
    private Pane btnlabReports;

    @FXML
    private Pane btnlogout;

    @FXML
    private Pane btnmedicine;

    @FXML
    private Pane btnoverView;

    @FXML
    private Pane btnpatient;


    @FXML
    private AnchorPane patientPane;

    public void btnoverViewOnAction(MouseEvent mouseEvent) throws IOException {
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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();
    }

    public void btnpatientOnAction(MouseEvent mouseEvent) throws IOException {
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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();
    }

    public void btnappoinmentOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/appoinmentFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("appoinmentFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();

    }

    public void btndoctorOnAction(MouseEvent mouseEvent) throws IOException {
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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();

    }

    public void btnlabreportsOnAction(MouseEvent mouseEvent) throws IOException {
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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();
    }

    public void btnmedicineOnAction(MouseEvent mouseEvent) throws IOException {
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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();
    }

    public void btnlogoutOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"));

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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();
    }

    public void btnemployeeOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/emplyeeFrom.fxml"));

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

        Stage stage1 = (Stage) patientPane.getScene().getWindow();
        stage1.close();
    }
}
