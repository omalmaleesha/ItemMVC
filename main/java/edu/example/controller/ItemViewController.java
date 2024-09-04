package edu.example.controller;

import com.jfoenix.controls.JFXTextField;
import edu.example.model.Item;
import edu.example.util.CrudUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemViewController implements Initializable {
    private ItemService itemService = new ItemController();

    @FXML
    private TableColumn<?, ?> colDescripton;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblview;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item item = new Item(txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText()));
        itemService.addItem(item);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Item> all = itemService.getAll();
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescripton.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));

        tblview.getSelectionModel().selectedItemProperty().addListener((((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                setSelectedItem((Item) newValue);
            }

        })));

        loadItemTable();
    }
    private void setSelectedItem(Item newValue) {
        txtItemCode.setText(newValue.getItemCode());
        txtDescription.setText(newValue.getDescription());
        txtPackSize.setText(newValue.getPackSize());
        txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
    }

    private void loadItemTable(){

        tblview.setItems(itemService.getAll());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        itemService.deleteItem(txtItemCode.getText());
    }

    @FXML
    void btnReloardOnAction(ActionEvent event) {
        loadItemTable();
    }

}
