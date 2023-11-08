package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountFromController {
    public AnchorPane createAccountPane;

    @FXML
    void txtLoginOnMouseClicked(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"))));
        stage.show();

        Stage stage1 = (Stage) createAccountPane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    public void txtUserName(ActionEvent actionEvent) {
    }

    @FXML
    void btncreateYourAccountOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("Login");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

        Stage stage1 = (Stage) createAccountPane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    public void txtPassword(ActionEvent actionEvent) {
    }
}
