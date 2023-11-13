package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.model.EmployeeModel;

import java.sql.SQLException;

public class EmployeeDetailsFromController {
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

    public void idSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            EmployeeDto dto = EmployeeModel.searchEmployee(id);
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
        txtAddress.setText(dto.getEmail());
        txtJobRole.setText(dto.getJob_role());
        txtQulification.setText(dto.getQualification());
        txtSalary.setText(dto.getSalary());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtNumber.getText();
        String email = txtAddress.getText();
        String jobRole = txtJobRole.getText();
        String qualification = txtQulification.getText();
        String salary = txtSalary.getText();

       var EmployeeDto = new EmployeeDto(id, name, address, email, jobRole, qualification, salary);

        try {
            boolean isAdded = EmployeeModel.saveEmployee(EmployeeDto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee added").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = EmployeeModel.deleteEmployee(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtNumber.getText();
        String email = txtAddress.getText();
        String jobRole = txtJobRole.getText();
        String qualification = txtQulification.getText();
        String salary = txtSalary.getText();

        var EmployeeDto = new EmployeeDto(id, name, address, email, jobRole, qualification, salary);

        try {
            boolean isUpdated = EmployeeModel.updateEmployee(EmployeeDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated").show();
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
}
