package lk.ijse.channelingCenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import lk.ijse.channelingCenter.model.AppoinmentListModel;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.tm.AppoinmentTm;
import lk.ijse.channelingCenter.model.AppoinmentModel;
import lk.ijse.channelingCenter.model.PatientModel;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppoinmentFromController {
    @Getter
    private static AppoinmentFromController controller;
    public AnchorPane appoinmentPane;
    public TableView tblAppointment;
    public TableColumn colId;
    public TableColumn colDate;
    public TableColumn colPatientName;

    public TableColumn colDoctor;

    public TableColumn colPatientId;
    public TableColumn colAge;
    public TableColumn colDoctorId;
    public TableColumn colUpdate;
    public TableColumn colDelete;

    AppoinmentModel appoinmentmodel = new AppoinmentModel();
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

    }*/

    @SneakyThrows
    public void initialize() {
        setCellValueFactory();
        loadAllAppoinments();
//        loadData();

    }

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

   /* public void loadAllAppoinments() {
        ObservableList<AppoinmentTm> obList = FXCollections.observableArrayList();

        try {
            List<AppoinmentDto> dtoList = new AppoinmentModel().getAllAppoinment();

            for (AppoinmentDto dto : dtoList) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Patient?", yes, no).showAndWait();
                    if (result.orElse(no) == yes) {
                        int selectedIndex = tblAppointment.getSelectionModel().getSelectedIndex();
                        String code = (String) colId.getCellData(selectedIndex);
                        deleteAppoinment(code);
                        obList.remove(selectedIndex);
                        tblAppointment.refresh();
                    }
                });
                updateButton.setOnAction((e) -> {
                    int selectedIndex = tblAppointment.getSelectionModel().getSelectedIndex();
                    String code = (String) colId.getCellData(selectedIndex);
                    System.out.println(code);
                    try {
                        appoinmentPane.getChildren().clear();
                        //patientPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/updatePatientFrom.fxml")));
                    } catch (Exception e1) {
                    }
                });

                obList.add(
                        new AppoinmentTm(
                                dto.getAppoinment_id(),
                                dto.getDate(),
                                dto.getPatinet_id(),
                                dto.getAge(),
                                dto.getId(),
                                dto.getDoctor_name(),
                                dto.getPatinetName(),
                                deleteButton,
                                updateButton
                        )
                );
            }

            tblAppointment.setItems(obList);
            //setFontAwesomeIcons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    /*private void setFontAwesomeIcons() {
        tblAppointment.getItems().forEach(item -> {
            Button deleteButton = item.getDeleteButton();
            Button updateButton = item.getUpdateButton();

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);

            deleteButton.setGraphic(deleteIcon);
            updateButton.setGraphic(updateIcon);
        });
    }*/
    void loadAllAppoinments() throws SQLException {
        var model = new AppoinmentModel();

        ObservableList<AppoinmentTm> list = FXCollections.observableArrayList();

        try {
            List<AppoinmentDto> dtos = model.getAllAppoinment();

            for (AppoinmentDto dto : dtos) {
                list.add(
                        new AppoinmentTm(
                                dto.getAppoinment_id(),
                                dto.getDate(),
                                dto.getPatinet_id(),
                                dto.getAge(),
                                dto.getId(),
                                dto.getDoctor_name(),
                                dto.getPatinetName()
                        ));

            }
            tblAppointment.setItems(list);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
//    private void setCellValueFactory(){
//        IDClom.setCellValueFactory(new PropertyValueFactory<>("id"));
//        NameColm.setCellValueFactory(new PropertyValueFactory<>("UserName"));
//    }
    /*private void deleteAppoinment(String code) {
        try {
            boolean b = appoinmentmodel.deleteAppoinment(code);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }*/

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("appoinment_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patinet_id"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        System.out.println("badu hri ");
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("age"));
        System.out.println("paka");
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("id"));
        System.out.println("awa");

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