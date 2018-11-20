package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.json.JSONObject;


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

    private void DisplayInventory() {
        ObservableList<Item> itemList = FXCollections.observableArrayList(inventory.getItems());
        ObservableList<Item> newItemList = FXCollections.observableArrayList(inventory.getItems());
        newItemList.remove(0, newItemList.size());
        for (Item anItemList : itemList) {

            if (anItemList != null) {
                newItemList.add(anItemList);
            }
        }

        listViewItems.setItems(newItemList);
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


    public void OnAdd() throws Throwable
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
            throw new Throwable(e.getMessage());
        }

    }

    public void OnEdit() throws Throwable{
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
            throw new Throwable(e.getMessage());

        }

    }
    @FXML
    public void OnFileChooser() throws Throwable {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        File f = fc.showOpenDialog(null);
        if (f.exists()){
            try{
                InputStream is = new FileInputStream(f);
                String jsonTxt = IOUtils.toString(is,"UTF-8");
                JSONObject json = new JSONObject(jsonTxt);
                Item[] items = new Item[json.length()];
                for(int i = 0; i < json.length(); i++)
                {
                    String name = json.getString("name");
                    int sellIn = json.getInt("sellIn");
                    int quality = json.getInt("quality");
                    Item item = new Item(name,sellIn,quality);
                    items[i] = item;
                }
                inventory.setItems(items);
                DisplayInventory();
            }
            catch(Exception e)
            {
                throw new Throwable(e.getMessage());

            }

        }
}}







