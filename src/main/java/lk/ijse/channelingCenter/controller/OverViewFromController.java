package lk.ijse.channelingCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OverViewFromController {

    public Text customId;

    @FXML
    void btnpaymentOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("Payment");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

       /* Stage stage1 = (Stage) loginPane.getScene().getWindow();
        stage1.close();*/


    }

    @FXML
    void btnscheduleOnAction(MouseEvent event) {

    }

    public void btnhomeOnAction(MouseEvent mouseEvent) {
    }

    public void mouseExitedOnAction(MouseEvent mouseEvent) {
        customId.setStyle("-fx-font-size: 20px");
        customId.setStyle("-fx-font-weight: normal");
        customId.setStyle("-fx-font-family: 'popins', sans-serif");
        customId.setStyle("-fx-alignment: center");
        customId.setStyle("-fx-alignment: middle");
        customId.setText("");
    }

    public void mouseEnterdOnAction(MouseEvent mouseEvent) {
        customId.setText("OverView");
    }

    public void mouseEnterOnAction(DragEvent dragEvent) {

    }
}
