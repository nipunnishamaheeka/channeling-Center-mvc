package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.dto.LoginDto;
import lk.ijse.channelingCenter.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountFromController {
    @FXML
    private AnchorPane createAccountPane;
    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField txtConfromPassword;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtFullname;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    void txtLoginOnMouseClicked(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"))));
        stage.show();

        Stage stage1 = (Stage) createAccountPane.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void btncreateYourAccountOnAction(ActionEvent event) throws IOException {
        String fullname = txtFullname.getText();
        String username = txtUsername.getText();
        String password = txtpassword.getText();
        String email = txtEmail.getText();

        LoginModel loginModel = new LoginModel();
        if (password.equals(txtConfromPassword.getText())) {

            try {
                boolean isSaved = loginModel.saveUser(new LoginDto(fullname, username, password,email));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully").show();
                    return;
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Password Not Matched").show();
        }

    }
}
