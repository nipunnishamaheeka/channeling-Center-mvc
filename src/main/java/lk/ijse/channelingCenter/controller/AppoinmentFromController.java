package lk.ijse.channelingCenter.controller;

import com.almasb.fxgl.physics.box2d.dynamics.contacts.Contact;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.model.AppoinmentListModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppoinmentFromController implements Initializable {
    @FXML
    private AnchorPane appoinmentPane;
    @FXML
    private VBox listLayout;

    private List<AppoinmentListModel> appoinmentList() {
        List<AppoinmentListModel> ls = new ArrayList<>();
        AppoinmentListModel appoinmentList = new AppoinmentListModel();

        appoinmentList.setAppoinment_id("A002");
        appoinmentList.setTime("10.00");
        appoinmentList.setDate("2021-08-20");
        appoinmentList.setPatient_name("Kamal");
        appoinmentList.setPatient_age("25");
        appoinmentList.setDoctor("Dr. Perera");
        appoinmentList.setFee_status("Paid");

        ls.add(appoinmentList);

        appoinmentList = new AppoinmentListModel();
        appoinmentList.setAppoinment_id("A003");
        appoinmentList.setTime("11.00");
        appoinmentList.setDate("2022-09-20");
        appoinmentList.setPatient_name("Sunimal");
        appoinmentList.setPatient_age("25");
        appoinmentList.setDoctor("Dr. Perera");
        appoinmentList.setFee_status("Non Paid");

        ls.add(appoinmentList);

        appoinmentList = new AppoinmentListModel();
        appoinmentList.setAppoinment_id("A004");
        appoinmentList.setTime("12.00");
        appoinmentList.setDate("2021-10-20");
        appoinmentList.setPatient_name("Rathne");
        appoinmentList.setPatient_age("11");
        appoinmentList.setDoctor("Dr. Lakmal");
        appoinmentList.setFee_status("Paid");

        ls.add(appoinmentList);

        appoinmentList = new AppoinmentListModel();
        appoinmentList.setAppoinment_id("A005");
        appoinmentList.setTime("01.00");
        appoinmentList.setDate("2021-08-20");
        appoinmentList.setPatient_name("Ranil");
        appoinmentList.setPatient_age("25");
        appoinmentList.setDoctor("Dr. Radeer");
        appoinmentList.setFee_status("Paid");

        ls.add(appoinmentList);

        appoinmentList = new AppoinmentListModel();
        appoinmentList.setAppoinment_id("A006");
        appoinmentList.setTime("10.40");
        appoinmentList.setDate("2021-08-20");
        appoinmentList.setPatient_name("Frathi");
        appoinmentList.setPatient_age("24");
        appoinmentList.setDoctor("Dr. Perera");
        appoinmentList.setFee_status("Paid");

        ls.add(appoinmentList);
        return ls;

    }

    public void btnoverViewOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/overViewFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("OverView");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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
        stage.setTitle("loginFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
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

        Stage stage1 = (Stage) appoinmentPane.getScene().getWindow();
        stage1.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<AppoinmentListModel> appoinmentList = new ArrayList<>(appoinmentList());
        for (int i = 0; i < appoinmentList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/appoinmentListFrom.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                AppoinmentListFromController cic = fxmlLoader.getController();
                cic.setDate(appoinmentList.get(i));
                listLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
