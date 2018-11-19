package edu.insightr.gildedrose;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.xml.soap.Text;


public class Controller implements Initializable {

    private Inventory inventory;
    @FXML
    private ListView<Item> listViewItems;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<String> listViewTestResult;

    @FXML
    private Button buttonTest;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private Label labelResult;

    @FXML
    private Label labelList;

    @FXML
    private TextField selInBox;

    @FXML
    private TextField nameBox;

    @FXML
    private TextField qualityBox;

    @FXML
    private Button buttonFileChooser;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        inventory = new Inventory();
        DisplayInventory();

    }

    public void DisplayInventory() {
        ObservableList<Item> itemList = FXCollections.observableArrayList(inventory.getItems());
        ObservableList<Item> newitemList = FXCollections.observableArrayList(inventory.getItems());
        newitemList.remove(0, newitemList.size());
        for (int i = 0; i  < itemList.size(); i++){

            if(itemList.get(i) != null)
            {
                newitemList.add(itemList.get(i));
            }
        }

        listViewItems.setItems(newitemList);
    }

    public  void OnUpdate()
    {
        inventory.updateQuality();
        DisplayInventory();
    }



    public  void OnDelete()
    {
        int selectedIdx = listViewItems.getSelectionModel().getSelectedIndex();
        inventory.Delete(selectedIdx);
        DisplayInventory();

    }


    public void OnAdd ()
    {
        try{
        String selIn = selInBox.getText();
        int selInInt = Integer.parseInt(selIn);
        String quality = qualityBox.getText();
        int qualityInt = Integer.parseInt(quality);
        String name = nameBox.getText();

            inventory.Add(name, selInInt, qualityInt);
            DisplayInventory();
        }
        catch(Exception e ) {
        }

    }

    public void OnEdit(){
        try{
            String selIn = selInBox.getText();
            int selInInt = Integer.parseInt(selIn);
            String quality = qualityBox.getText();
            int qualityInt = Integer.parseInt(quality);
            String name = nameBox.getText();
            int selectedIdx = listViewItems.getSelectionModel().getSelectedIndex();
            inventory.getItems()[selectedIdx].setName(name);
            inventory.getItems()[selectedIdx].setQuality(qualityInt);
            inventory.getItems()[selectedIdx].setSellIn(selInInt);
            DisplayInventory();
        }
        catch(Exception e ) {
        }

    }
    @FXML
    public void OnFileChooser() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        File f = fc.showOpenDialog(null);
    }
}







