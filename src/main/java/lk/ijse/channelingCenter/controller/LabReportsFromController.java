package lk.ijse.channelingCenter.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.*;
import lk.ijse.channelingCenter.dto.tm.LabReportTm;
import lk.ijse.channelingCenter.email.Email;
import lk.ijse.channelingCenter.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class LabReportsFromController {
    public AnchorPane labReportsPane;
    public Label lblReportId;
    public Label lblPatientName;
    public Label lblDoctorName;
    public Label lblGender;
    public Label lblAge;
    public TableView tblReport;
    public JFXComboBox<String> cmboPationId;
    public JFXComboBox<String> cmbDoctor;
    public TextField txtSearchId;
    public TextField txtPatientName;
    public TextField txtReportName;
    public TextField txtReportResult;
    public TextField txtUnits;
    public TextField txtOthers;
    @FXML
    private DatePicker datefelid;
    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colLabRecord;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableColumn<?, ?> colReportId;
LabReportModel labReportModel =new LabReportModel();

    public void initialize() throws SQLException {
        setLabReportsID();
        loadPatientsIds();
        loadDoctorIds();
        setCellValueFactory();
        loadAllReports();
    }

    void loadAllReports() throws SQLException {
        var model = new LabReportModel();

        ObservableList<LabReportTm> list = FXCollections.observableArrayList();

        try {
            List<LabReportDto> dtos = model.getAllReports();

            for (LabReportDto dto : dtos) {
                list.add(
                        new LabReportTm(
                                dto.getLab_reportid(),
                                dto.getPatient_id(),
                                dto.getDate(),
                                dto.getDoctor_id(),
                                dto.getDoctor_name(),
                                dto.getAge(),
                                dto.getGender(),
                                dto.getPatient_name(),
                                dto.getTest_name(),
                                dto.getTest_result(),
                                dto.getUnits(),
                                dto.getOthers()
                        ));

            }
            tblReport.setItems(list);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isReportValid = validateLabReport();

        if (isReportValid) {
            try {
                String id = lblReportId.getText();
                String PatientId = cmboPationId.getValue();
                String Date = datefelid.getValue().toString();
                String DId = cmbDoctor.getValue();
                String DName = lblDoctorName.getText();
                String Age = lblAge.getText();
                String Gender = lblGender.getText();
                String PName = lblPatientName.getText();
                String TestName = txtReportName.getText();
                String TestResult = txtReportResult.getText();
                String Units = txtUnits.getText();
                String Others = txtOthers.getText();

                LabReportModel labReportModel = new LabReportModel();
                boolean isSaved = labReportModel.saveLabReport(new LabReportDto(id, PatientId, Date, DId, DName, Age, Gender, PName, TestName, TestResult, Units, Others));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully").show();
                    loadAllReports();
                    clearFields();
                    return;
                } else {
                    new Alert(Alert.AlertType.ERROR, "LabReport not saved!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            lblReportId.requestFocus();
        }
    }

    private void clearFields() {
        lblReportId.setText("");
        datefelid.setValue(null);
        lblDoctorName.setText("");
        lblAge.setText("");
        //lblGender.setText("");
        lblPatientName.setText("");
        txtReportName.setText("");
        txtReportResult.setText("");
        txtUnits.setText("");
        txtOthers.setText("");
    }


    private boolean validateLabReport() {
        String testName = txtReportName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(testName).matches();
        if (!isNameValid) {
            txtReportName.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        String testResult = txtReportResult.getText();
        boolean isResultValid = Pattern.compile("[A-Za-z]{3,}").matcher(testResult).matches();
        if (!isResultValid) {
            txtReportResult.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        String units = txtUnits.getText();
        boolean isUnitsValid = Pattern.compile("^[A-Za-z0-9\\s\\-()&,.]+$").matcher(units).matches();
        if (!isUnitsValid) {
            txtUnits.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        return true;
    }

    private void setLabReportsID() {

        try {
            lblReportId.setText(new LabReportModel().autoGenarateLabReportId());
            //tblReport.refresh();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadPatientsIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PatientDto> cusList = new PatientModel().getAllPatient();

            for (PatientDto dto : cusList) {
                obList.add(dto.getPatient_id());
            }
            cmboPationId.setItems(obList);
            //cmbPatientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDoctorIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<DoctorDto> cusList = new DoctorModel().getAllDoctor();

            for (DoctorDto dto : cusList) {
                obList.add(dto.getId());
            }
            cmbDoctor.setItems(obList);
            //cmbPatientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbPatientIdOnAction(ActionEvent event) {
        try {
            String name = new PatientModel().getPatientName(cmboPationId.getValue());
            lblPatientName.setText(name);

            String gender = new PatientModel().getPatientGender(cmboPationId.getValue());
            lblGender.setText(gender);

            String age = new PatientModel().getPatientAge(cmboPationId.getValue());
            lblAge.setText(age);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (validateLabReport()) {
            String id = lblReportId.getText();
            String PatientId = cmboPationId.getValue();
            String Date = datefelid.getValue().toString();
            String DId = cmbDoctor.getValue();
            String DName = lblDoctorName.getText();
            String Age = lblAge.getText();
            String Gender = lblGender.getText();
            String PName = lblPatientName.getText();
            String TestName = txtReportName.getText();
            String TestResult = txtReportResult.getText();
            String Units = txtUnits.getText();
            String Others = txtOthers.getText();
            try {
                boolean isUpdated = labReportModel.updateLabReport(new LabReportDto(id,PatientId,Date,DId,DName,Age,Gender,PName,TestName,TestResult,Units,Others));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Patient updated").show();
                    clearFields();
                    //loadAllPatients();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void cmbDoctorIdOnAction(ActionEvent actionEvent) {
        try {
            String name = new DoctorModel().getname(cmbDoctor.getValue());
            lblDoctorName.setText(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void ReportbtnOnActhion() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/Reports/LabReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT * FROM labreport WHERE lab_reportId = " + "\"" + txtSearchId.getText() + "\"");
        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void btnPrintReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        ReportbtnOnActhion();
    }

    public void btnSearchIdOnAction(ActionEvent actionEvent) {
        String id = txtSearchId.getText();

        var model = new LabReportModel();
        try {
            LabReportDto dto = model.searchLabReport(id);

            if (dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Report not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(LabReportDto dto) {
        txtPatientName.setText(dto.getPatient_name());
    }

    private void setCellValueFactory() {
        colReportId.setCellValueFactory(new PropertyValueFactory<>("lab_reportid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
        colLabRecord.setCellValueFactory(new PropertyValueFactory<>("lab_record"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));


    }

    public void btnEmailOnAction(MouseEvent mouseEvent) throws SQLException {
        //loadAllReports();
        String title = "Lab Report";
        sendMail("Thank you for choosing our service !", "Your Order Is Successfully.", "ashannvn@gmail.com");

    }

    private boolean sendMail(String title, String message, String gmail) {
        try {
            new Email().sendMail(title, message, gmail);
            return true;
        } catch (IOException | MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }
}

