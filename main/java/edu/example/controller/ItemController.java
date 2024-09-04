package edu.example.controller;

import edu.example.dbConnection.DBConnection;
import edu.example.model.Item;
import edu.example.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService{
    @Override
    public void addItem(Item item) {
        String sql = "INSERT INTO item VALUES(?,?,?,?,?)";
        try {
            Boolean execute = CrudUtil.execute(sql, item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM item";
        try {
            ResultSet execute = CrudUtil.execute(sql);
            while (execute.next()){
                itemObservableList.add(new Item(
                        execute.getString("ItemCode"),
                        execute.getString("Description"),
                        execute.getString("PackSize"),
                        execute.getDouble("UnitPrice"),
                        execute.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemObservableList;
    }

    @Override
    public void deleteItem(String code) {
        String sql = "DELETE FROM item WHERE ItemCode = ?";
        try {
            Boolean execute =  CrudUtil.execute(sql,code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
