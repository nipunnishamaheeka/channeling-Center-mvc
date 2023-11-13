package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import lk.ijse.channelingCenter.model.AppoinmentListModel;
import lombok.Getter;

import java.io.IOException;

public class AppoinmentFromController {
@Getter
private static AppoinmentFromController controller;
  //  public VBox VBox;

    public AppoinmentFromController(){controller = this;}

    @FXML
    private AnchorPane appoinmentPane;
//    @FXML
//    private VBox listLayout;

    /*public void idOnAction(ActionEvent actionEvent){
        try{
            if (AppoinmentListModel.saveAppoinment(new AppoinmentDto(

            ))){
                new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"error").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }*/

   /* public void loadData() {
        listLayout.getChildren().clear();
        try {
            List<AppoinmentDto> List = AppoinmentListModel.getAllAppoinment();
            for (int i = 0; i < List.size(); i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(AppoinmentListFromController.class.getResource("/view/appoinmetListFrom.fxml"));
                    Pane root = loader.load();
                    AppoinmentListFromController controller = loader.getController();
                    controller.setData(List.get(i));
                    listLayout.getChildren().add(root);
                } catch (IOException e) {
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
/*public void setSearchData(String text) {
        listLayout.getChildren().clear();
        try {
            List<AppoinmentDto> List = AppoinmentListModel.getAllFilter();//text
            for (int i = 0; i < List.size(); i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(AppoinmentListFromController.class.getResource("/view/appoinmetListFrom.fxml"));
                    Parent root = loader.load();
                    AppoinmentListFromController controller = loader.getController();
                    controller.setData(List.get(i));
                    listLayout.getChildren().add(root);
                } catch (IOException e) {
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
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


    public void btnaddAppoinmentOnAction(ActionEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/addAppoinmentFrom.fxml"));

        //create a new Scene
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        //set scene to the primary stage
        stage.setScene(scene);

        //set title and get center on screen stage
        stage.setTitle("addAppoinmentFrom");
        stage.centerOnScreen();

        //show stage to the crowd
        stage.show();

    }
}

/* List<AppoinmentListModel> appoinmentList = new ArrayList<>(appoinmentList());
        for (int i = 0; i < appoinmentList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/appoinmetListFrom.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                AppoinmentListFromController cic = fxmlLoader.getController();
                cic.setData(appoinmentList.get(i));
                listLayout.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }*/