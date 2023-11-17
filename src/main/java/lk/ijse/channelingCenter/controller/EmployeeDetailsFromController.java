package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.model.EmployeeModel;

import javax.naming.ldap.PagedResultsControl;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeDetailsFromController {
    public AnchorPane employeeDetailsPane;
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

    private final EmployeeModel employeeModel = new EmployeeModel();

    public void idSearchOnAction(ActionEvent actionEvent) {
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

    }

    private void setFields(EmployeeDto dto) {
        txtId.setText(dto.getEmp_id());
        txtName.setText(dto.getEmp_name());
        txtNumber.setText(dto.getEmp_address());
        txtAddress.setText(dto.getMobile_number());
        txtJobRole.setText(dto.getJob_role());
        txtQulification.setText(dto.getQualification());
        txtSalary.setText(dto.getSalary());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String number = txtNumber.getText();
        String jobRole = txtJobRole.getText();
        String qualification = txtQulification.getText();
        String salary = txtSalary.getText();

       var EmployeeDto = new EmployeeDto(id, name, address, number, jobRole, qualification, salary);

        try {
            boolean isAdded = employeeModel.saveEmployee(EmployeeDto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee added").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        String id = txtId.getText();
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
        txtId.setText("");
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
}
