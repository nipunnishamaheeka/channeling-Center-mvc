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
import lk.ijse.channelingCenter.dto.LoginDto;
import lk.ijse.channelingCenter.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFromController {
    public static String fullname;
    public AnchorPane loginPane;
    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField textpassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {

        String userName = txtUsername.getText();
        String password = textpassword.getText();
        LoginModel model = new LoginModel();
        try {
            boolean isIn= model.searchUser(new LoginDto(null,userName,password));
            if (!isIn){
                new Alert(Alert.AlertType.WARNING,"Invalid User Name or Password").show();
                return;
            }else  {
                navigateToMainWindow();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void navigateToMainWindow() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/overViewFrom.fxml"));
        Scene scene = new Scene(rootNode);

        loginPane.getChildren().clear();
        Stage primaryStage = (Stage) loginPane.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("ToDo");
    }

    public void txtcreateAccountOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/createAccountFrom.fxml"))));
        stage.show();

        Stage stage1 = (Stage) loginPane.getScene().getWindow();
        stage1.close();
    }
}


