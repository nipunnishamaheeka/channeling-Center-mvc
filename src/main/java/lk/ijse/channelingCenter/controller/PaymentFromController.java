package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentFromController {
    public AnchorPane paymentPane;

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/medicinePlaceOrder.fxml")));
    }

    public void btnCompleteOrdersOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/completeOrdersFrom.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage  = new Stage();
        stage.setScene(scene);
        stage.setTitle("addAppoinmentFrom");
        stage.centerOnScreen();
        stage.show();

    }
}