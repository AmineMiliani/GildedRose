package edu.insightr.gildedrose;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.chart.*;

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

    @FXML
    private PieChart pcItems;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        inventory = new Inventory();
        DisplayInventory();
        ObservableList<PieChart.Data> pieChartData = LoadPieChart();
        ApplyPieChartColors(pieChartData, "green", "red", "blue", "orange");
    }

    private int Count(String groupName)
    {
        Item [] items = inventory.getItems();
        int count = 0;
        for (Item item : items) {
            if (item.getName().contains(groupName)) {
                count++;
            }
        }
        return count;
    }

    private ObservableList<PieChart.Data> LoadPieChart(){
        int countLegendary = Count("Legendary");
        int countConjured = Count("Conjured");
        int countAged = Count("Aged");
        int countNormal = inventory.getItems().length  - (countAged + countConjured + countLegendary);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Legendary",countLegendary), new PieChart.Data("Conjured", countConjured), new PieChart.Data("Aged", countAged), new PieChart.Data("Normal", countNormal));
        pcItems.setData(pieChartData);
        return pieChartData;
    }

    private void ApplyPieChartColors(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 1;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle(
                "-fx-pie-color: " + pieColors[i % pieColors.length] + ";"
            );
            i++;
        }
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

    public void OnUpdate() {
        inventory.updateQuality();
        DisplayInventory();
    }


    public void OnDelete() {
        int selectedIdx = listViewItems.getSelectionModel().getSelectedIndex();
        inventory.Delete(selectedIdx);
        DisplayInventory();

    }


    public void OnAdd() throws Throwable {
        try {
            String selIn = selInBox.getText();
            int selInInt = Integer.parseInt(selIn);
            String quality = qualityBox.getText();
            int qualityInt = Integer.parseInt(quality);
            String name = nameBox.getText();

            inventory.Add(name, selInInt, qualityInt);
            DisplayInventory();
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }

    }

    public void OnEdit() throws Throwable {
        try {
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
        } catch (Exception e) {
            throw new Throwable(e.getMessage());

        }

    }

    @FXML
    public void OnFileChooser() throws Throwable {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        File f = fc.showOpenDialog(null);
        if (f.exists()) {
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream(f.getName());
                Reader reader = new InputStreamReader(is);
                JsonArray array = new JsonParser().parse(reader).getAsJsonArray();
                Item [] items = new Item[inventory.getItems().length + array.size()];
                ArrayList<Item> yourArray = new Gson().fromJson(array.toString(), new TypeToken<List<Item>>(){}.getType());
                int j = 0;
                for(int i = 0;  i < yourArray.size() + items.length; i++)
                {
                    if(i <inventory.getItems().length)
                    {
                        items[i] = inventory.getItems()[i];
                    }
                    else if(j < yourArray.size())
                    {
                        items[i] = yourArray.get(j);
                        j++;
                    }
                }
                inventory.setItems(items);
                DisplayInventory();
                ObservableList<PieChart.Data> pieChartData = LoadPieChart();
                ApplyPieChartColors(pieChartData, "green", "red", "blue", "orange");            }
            catch (Exception e) {
                throw new Throwable(e.getMessage());

            }
        }
    }

}








