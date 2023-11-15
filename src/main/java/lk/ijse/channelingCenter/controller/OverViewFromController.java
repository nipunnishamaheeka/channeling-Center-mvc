package lk.ijse.channelingCenter.controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class OverViewFromController implements Initializable {

    public AnchorPane overViewPane;
    public PieChart pieChart;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblTimeMini;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateRealTime();
        ObservableList<PieChart.Data>observableList= FXCollections.observableArrayList(
        new PieChart.Data("Acetaminophen",10),
        new PieChart.Data("Adderall",20),
        new PieChart.Data("Amitriptyline",30),
        new PieChart.Data("Amlodiphine",40),
        new PieChart.Data("Amoxicillin",50),
        new PieChart.Data("Ativan",200)

                );
pieChart.setData(observableList);

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
