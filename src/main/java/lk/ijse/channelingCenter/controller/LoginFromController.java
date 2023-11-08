package lk.ijse.channelingCenter.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.db.DbConnection;

import java.io.IOException;

public class LoginFromController {

    public AnchorPane loginPane;
    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField textpassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {

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

        Stage stage1 = (Stage) loginPane.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void txtPassword(ActionEvent event) {

    }

    @FXML
    void txtUserName(ActionEvent event) {

    }


    public void txtcreateAccountOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/createAccountFrom.fxml"))));
        stage.show();

        Stage stage1 = (Stage) loginPane.getScene().getWindow();
        stage1.close();
    }
}


