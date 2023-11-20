package lk.ijse.channelingCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MedicineNewDesignFromController implements Initializable {


    @FXML
    private VBox itemList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        for (int i = 0; i > nodes.length; i++) {
            try {
                nodes[i] = (Node) FXMLLoader.load(getClass().getResource("view/mediListFrom.fxml"));
                itemList.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

   /* public void tableView(MouseEvent mouseEvent) {
        tableView.setOnMouseEntered(event -> {
            // Get the selected item (row) in the TableView
            String selectedItem = tableView.getSelectionModel().getSelectedItem();

            // Check if the selectedItem is not null (i.e., a row is selected)
            if (selectedItem != null) {
                // Perform your action here for the selected row
                System.out.println("Mouse entered row: " + selectedItem);
            }
        });
    }*/
}
