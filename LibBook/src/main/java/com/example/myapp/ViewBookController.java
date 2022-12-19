package com.example.myapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewBookController extends BooksCatalogController {


    @FXML
    private ListView<String> ListViewId;


    @FXML
    void initialize() {
        initBook();
    }
    public void initBook(){
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            BufferedReader reader  = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    ("C:\\LibBook\\src\\main\\java\\BooksTxt\\" + res + ".txt"))));
            String line = reader.readLine();
            list.add(line);
            while ((line != null)){
                line = reader.readLine();
                if(line != null){
                    list.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ListViewId.setItems(list);
    }


}