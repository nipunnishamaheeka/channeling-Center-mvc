package lk.ijse.channelingCenter.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class NavigationPannelFromController implements Initializable {
    public AnchorPane navigationPane;
    public Label lblDate;
    public Label lblTimeMini;

@Override
    public void initialize(URL location, ResourceBundle resources) {
        generateRealTime();
    }

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

    /*-----DATE AND TIME GENERATE------*/
    public String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
        //System.out.println(dateFormat.format(new Date()));
        return dateFormat.format(new Date()) ;
    }
    private void generateRealTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {

            lblTimeMini.setText(timeNow());
            // lblTime.setText(LocalDateTime.now().format(formatter));

        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
