package edu.example.controller;

import edu.example.model.Item;
import javafx.collections.ObservableList;

public interface ItemService {
    void addItem(Item item);
    ObservableList<Item> getAll();
    void deleteItem(String code);
}
