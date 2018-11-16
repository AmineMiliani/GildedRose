package edu.insightr.gildedrose;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



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
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        inventory = new Inventory();
        DisplayInventory();

    }

    public void DisplayInventory() {
        ObservableList<Item> itemList = FXCollections.observableArrayList(inventory.getItems());
        listViewItems.setItems(itemList);
    }

    public  void OnUpdate()
    {
        inventory.updateQuality();
        DisplayInventory();
    }
}
