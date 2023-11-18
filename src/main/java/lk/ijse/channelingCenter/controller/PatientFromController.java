package lk.ijse.channelingCenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.tm.PatientTm;
import lk.ijse.channelingCenter.model.PatientModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PatientFromController{

    public AnchorPane patientPane;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private TableColumn<?,?> colAge;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?,?> colBloodGroup;

    @FXML
    private TableColumn<?,?> colEmail;

    @FXML
    private TableColumn<?,?> colNumber;

    @FXML
    private TableColumn<?,?> colPatientID;

    @FXML
    private TableColumn<?,?> colPatientName;

    @FXML
    private TableColumn<?,?> colSex;

    PatientModel patientModel = new PatientModel();
private void setCellValueFactory(){
    colPatientID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
    colPatientName.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
    colNumber.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
    colBloodGroup.setCellValueFactory(new PropertyValueFactory<>("blood"));
    colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
    colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));

}

    public void initialize() {
    setCellValueFactory();
        loadAllPatients();

    }
    private void setFontAwesomeIcons() {
        tblPatient.getItems().forEach(item -> {
            Button deleteButton = item.getDeleteButton();
            Button updateButton = item.getUpdateButton();

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);

            deleteButton.setGraphic(deleteIcon);
            updateButton.setGraphic(updateIcon);
        });
    }


    public  void loadAllPatients() {
        ObservableList<PatientTm> obList = FXCollections.observableArrayList();

        try {
            List<PatientDto> dtoList = new PatientModel().getAllPatient();

            for(PatientDto dto : dtoList) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e)->{
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Patient?", yes, no).showAndWait();
                    if (result.orElse(no) == yes){
                        int selectedIndex = tblPatient.getSelectionModel().getSelectedIndex();
                        String code = (String) colPatientID.getCellData(selectedIndex);
                        deletePatient(code);
                        obList.remove(selectedIndex);
                        tblPatient.refresh();
                    }
                });
                updateButton.setOnAction((e)->{
                    int selectedIndex = tblPatient.getSelectionModel().getSelectedIndex();
                    String code = (String) colPatientID.getCellData(selectedIndex);
                    System.out.println(code);
                    try{
                        patientPane.getChildren().clear();
                        //patientPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/updatePatientFrom.fxml")));
                    }catch (Exception e1){
                    }
                });
                obList.add(
                        new PatientTm(
                              dto.getPatient_id(),
                                    dto.getPatient_name(),
                                    dto.getMobile_number(),
                                    dto.getAddress(),
                                    dto.getSex(),
                                    dto.getEmail(),
                                    dto.getAge(),
                                    dto.getBlood(),
                                    deleteButton,
                                    updateButton
                        )
                );
            }

            tblPatient.setItems(obList);
            setFontAwesomeIcons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deletePatient(String code) {
        try {
            boolean b = patientModel.deletePatient(code);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    @FXML
    void btnaddPatientOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/addPatientFrom.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("addPatinetFrom");
        stage.centerOnScreen();
        stage.show();

    }


    public void btnRefershOnAction(MouseEvent mouseEvent) {
        loadAllPatients();
    }

}
