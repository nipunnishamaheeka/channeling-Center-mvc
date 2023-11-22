package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.db.DbConnection;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.model.EmployeeModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.naming.ldap.PagedResultsControl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class EmployeeDetailsFromController {
    public AnchorPane employeeDetailsPane;
    public Label lblEmpId;
    public TextField employeeId;
    public TextField txtEmpName;
    public TextField txtemployeeId;
    @FXML
    private TextField txtAddress;



    @FXML
    private TextField txtId;

    @FXML
    private TextField txtJobRole;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtQulification;

    @FXML
    private TextField txtSalary;
    EmployeeModel employeeModel = new EmployeeModel();

    public void initialize() throws SQLException {
        setEmployeeID();
        // validateEmployee();
    }

    private void setEmployeeID() {
        try {
            lblEmpId.setText(new EmployeeModel().autoGenarateEmployeeId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   /* public void idSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            EmployeeDto dto = employeeModel.searchEmployee(id);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }*/

    private boolean validateEmployee() {
        String nameText = txtName.getText();
        boolean isNameValid = nameText.matches("[A-Za-z][A-Za-z. ]{3,}");
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return false;
        }
        String numberText = txtNumber.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Doctor Number").show();
            return false;
        }
        return true;
    }


    private void setFields(EmployeeDto dto) {
        lblEmpId.setText(dto.getEmp_id());
        txtName.setText(dto.getEmp_name());
        txtNumber.setText(dto.getEmp_address());
        txtAddress.setText(dto.getMobile_number());
        txtJobRole.setText(dto.getJob_role());
        txtQulification.setText(dto.getQualification());
        txtSalary.setText(dto.getSalary());
    }
public void btnAddOnAction(ActionEvent actionEvent) {
    boolean isEmployeeValid = validateEmployee();

    if(isEmployeeValid) {
        String id = lblEmpId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String number = txtNumber.getText();
        String jobRole = txtJobRole.getText();
        String qualification = txtQulification.getText();
        String salary = txtSalary.getText();

        EmployeeDto itemDto = new EmployeeDto(id, name, address, number, jobRole, qualification, salary);

        try {
            EmployeeModel employeeModel = new EmployeeModel();
            boolean isAdded = employeeModel.saveEmployee(itemDto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee added",ButtonType.OK).show();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Employee not added",ButtonType.OK).show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }else{
        new Alert(Alert.AlertType.ERROR,"Invalid Employee Details", ButtonType.OK).show();
    }
}
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = employeeModel.deleteEmployee(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = lblEmpId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String number = txtNumber.getText();
        String jobRole = txtJobRole.getText();
        String qualification = txtQulification.getText();
        String salary = txtSalary.getText();

        var EmployeeDto = new EmployeeDto(id, name, address, number, jobRole, qualification, salary);

        try {
            boolean isUpdated = employeeModel.updateEmployee(EmployeeDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        //lblEmpId.setText("");
        txtEmpName.setText("");
        txtemployeeId.setText("");
        txtName.setText("");
        txtNumber.setText("");
        txtAddress.setText("");
        txtJobRole.setText("");
        txtQulification.setText("");
        txtSalary.setText("");
    }

    public void btnBackButtonOnAction(MouseEvent mouseEvent) throws IOException {
        employeeDetailsPane.getChildren().clear();
        employeeDetailsPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/employeeFrom.fxml")));

    }
    void ReportbtnOnActhion() throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/Reports/channelingCenterReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT * FROM employee WHERE emp_id = "+"\""+txtemployeeId.getText()+"\"");
        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
    public void btnGenarateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
ReportbtnOnActhion();

    }

    public void btnSearchEmpIdOnAction(ActionEvent actionEvent) {
        String id = txtemployeeId.getText();

        var model = new EmployeeModel();
        try {
            EmployeeDto dto = model.searchEmployee(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(EmployeeDto dto) {
        lblEmpId.setText(dto.getEmp_id());
        txtEmpName.setText(dto.getEmp_name());
        txtName.setText(dto.getEmp_name());
        txtAddress.setText(dto.getEmp_address());
        txtNumber.setText(dto.getMobile_number());
        txtJobRole.setText(dto.getJob_role());
        txtQulification.setText(dto.getQualification());
        txtSalary.setText(dto.getSalary());


    }

    public void btnClearReportOnAction(ActionEvent actionEvent) {
        clearFields();
    }
}
