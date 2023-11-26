package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PaymentFromController {
    public AnchorPane paymentPane;

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        paymentPane.getChildren().clear();
        paymentPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/AddPaymentFrom.fxml")));
    }
}