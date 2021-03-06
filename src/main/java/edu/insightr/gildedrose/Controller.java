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
import java.time.LocalDate;
import java.util.*;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.chart.*;
import java.lang.String;


public class Controller implements Initializable {

    private ArrayList<Transaction> transactions = new ArrayList<>();

    private Inventory inventory;
    @FXML
    private ListView<Item> listViewItems;

    @FXML
    private ListView<Transaction> transactionListView;

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

    @FXML
    private BarChart bcSellIn;

    @FXML
    private NumberAxis yAxisSellin;

    @FXML
    private BarChart bcDate;

    @FXML
    private NumberAxis yAxisDate;

    @FXML
    private BarChart bcTransactions;

    @FXML
    private NumberAxis yAxisTransaction;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        inventory = new Inventory();
        for (Item item:inventory.getItems())
        {
            Transaction transa = new Transaction("bought", item);
            transactions.add(transa);
        }
        DisplayInventory();
        LoadPieChart();
        LoadPieChart();
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

    private void LoadPieChart(){
        int countLegendary = Count("Legendary");
        int countConjured = Count("Conjured");
        int countAged = Count("Aged");
        int countNormal = inventory.getItems().length  - (countAged + countConjured + countLegendary);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Legendary",countLegendary), new PieChart.Data("Conjured", countConjured), new PieChart.Data("Aged", countAged), new PieChart.Data("Normal", countNormal));
        pcItems.setData(pieChartData);
    }
    @SuppressWarnings("unchecked")
    private void LoadBarChartSellIn()
    {
        bcSellIn.getData().clear();

        class SellInHist{

            private int count;
            private int sellIn;

            private SellInHist(int count, int sellIn)
            {
                this.count = count;
                this.sellIn = sellIn;
            }
            private int getSellIn() {
                return sellIn;
            }
            private int getCount() {
                return count;
            }
        }
        List<SellInHist> list = new ArrayList<>();

        for (Item item: inventory.getItems())
        {
            int count = 0;
            if(list.size()==0)
            {
                SellInHist a = new SellInHist(1,item.getSellIn());
                list.add(a);
            }
            for (SellInHist element: list)
            {
                try
                {
                    if(item.getSellIn() == element.getSellIn())
                    {
                        element.count++;
                        break;
                    }
                    else
                    {
                        count++;
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            if(count == list.size())
            {
                SellInHist a = new SellInHist(1,item.getSellIn());
                list.add(a);
            }
        }

        //Arrangement de la liste
        list.get(0).count--;
        //BarChart
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(true);

        XYChart.Series serie = new XYChart.Series();
        for (SellInHist item: list)
        {
            serie.getData().add(new XYChart.Data(String.valueOf(item.getSellIn()), item.getCount()));
        }
        bcSellIn.getData().add(serie);
        bcSellIn.setCategoryGap(15);
    }
    @SuppressWarnings("unchecked")
    private void LoadBarChartDate()
    {
        bcDate.getData().clear();

        class dateHist
        {
            private int count;
            private String date;

            private dateHist(int count, String date)
            {
                this.count=count;
                this.date=date;
            }

            private int getCount()
            {
                return count;
            }
            private String getDate()
            {
                return date;
            }
        }

        List<dateHist> list = new ArrayList<>();
        for (Item item: inventory.getItems())
        {
            int count = 0;
            if(list.size() == 0)
            {
                dateHist a = new dateHist(1,item.getCreationDate());
                list.add(a);
            }
            for (dateHist element: list)
            {
                try
                {
                    if(item.getCreationDate().equals(element.getDate()))
                    {
                        element.count++;
                        break;
                    }
                    else
                    {
                        count++;
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            if(count == list.size())
            {
                dateHist a = new dateHist(1,item.getCreationDate());
                list.add(a);
            }
        }
        //Arrangement de la liste
        list.get(0).count--;
        //BarChart
        XYChart.Series serie = new XYChart.Series();
        for (dateHist item: list)
        {
            serie.getData().add(new XYChart.Data(item.getDate(), item.getCount()));
        }
        bcDate.getData().add(serie);
    }
    @SuppressWarnings("unchecked")
    private void LoadBarChartTrail()
    {
        bcTransactions.getData().clear();
        class ItemDate
        {
            private int count;
            private String transactionDate;

            private ItemDate(int count, String transactionDate)
            {
                this.count = count;
                this.transactionDate = transactionDate;
            }
            private int getCount()
            {
                return count;
            }
            private String getTransactionDate()
            {
                return transactionDate;
            }
        }
        List<ItemDate> bought = new ArrayList<>();
        List<ItemDate> sold = new ArrayList<>();

        if(transactions.size() != 0)
        {

            for (Transaction item: transactions)
            {
                int count = 0;
                switch (item.getAction()) {
                    case "bought":
                        if (bought.size() == 0) {
                            ItemDate a = new ItemDate(1, item.getTransactionDate());
                            bought.add(a);
                        }
                        for (ItemDate element : bought) {
                            if (item.getTransactionDate().equals(element.getTransactionDate())) {
                                element.count++;
                                break;
                            } else
                                count++;
                        }
                        if (bought.size() == count) {
                            ItemDate a = new ItemDate(1, item.getTransactionDate());
                            bought.add(a);
                        }
                        break;
                    case "sold":
                        if (sold.size() == 0) {
                            ItemDate a = new ItemDate(1, item.getTransactionDate());
                            sold.add(a);
                        }
                        for (ItemDate element : sold) {
                            if (item.getTransactionDate().equals(element.getTransactionDate())) {
                                element.count++;
                                break;
                            } else
                                count++;
                        }
                        if (sold.size() == count) {
                            ItemDate a = new ItemDate(1, item.getTransactionDate());
                            sold.add(a);
                        }
                        break;
                    default:
                        System.out.println("Item nor bought nor sold");
                        break;
                }
            }
            if(bought.size() != 0)
            {
                bought.get(0).count--;
            }
            if(sold.size() != 0)
            {
                sold.get(0).count--;
            }

            XYChart.Series BYE = new XYChart.Series();
            BYE.setName("BOUGHT");
            XYChart.Series SELL = new XYChart.Series();
            SELL.setName("SOLD");
            for (ItemDate item: bought)
            {
                BYE.getData().add(new XYChart.Data(item.getTransactionDate(), item.getCount()));
            }
            for (ItemDate item: sold)
            {
                SELL.getData().add(new XYChart.Data(item.getTransactionDate(), item.getCount()));
            }
            bcTransactions.getData().add(BYE);
            bcTransactions.getData().add(SELL);
        }
    }

    private void DisplayInventory()
    {

        ObservableList<Item> itemList = FXCollections.observableArrayList(inventory.getItems());

        //protection to delete the null items (normally already deleted in the function delete of Inventory)
        ObservableList<Item> newItemList = FXCollections.observableArrayList(inventory.getItems());
        newItemList.remove(0, newItemList.size());
        for (Item anItemList : itemList) {

            if (anItemList != null) {
                newItemList.add(anItemList);
            }
        }
        if(transactions != null){
        ObservableList<Transaction> transactionList = FXCollections.observableArrayList(transactions);
        transactionListView.setItems(transactionList);
        }
        listViewItems.setItems(newItemList);
        LoadPieChart();
        LoadBarChartSellIn();
        LoadBarChartDate();
        LoadBarChartTrail();
    }

    public void OnUpdate() {
        inventory.updateQuality();
        DisplayInventory();
    }


    public void OnDelete() {
        int selectedIdx = listViewItems.getSelectionModel().getSelectedIndex();
        Item item = inventory.getItems()[selectedIdx];
        inventory.Delete(selectedIdx);
        Transaction transaction = new Transaction("sold" ,item);
        transactions.add(transaction);
        DisplayInventory();


    }


    public void OnAdd() throws Throwable {
        try {
            String selIn = selInBox.getText();
            int selInInt = Integer.parseInt(selIn);
            String quality = qualityBox.getText();
            int qualityInt = Integer.parseInt(quality);
            String name = nameBox.getText();
            String date = LocalDate.now().toString();
            inventory.Add(name, selInInt, qualityInt, date);

            Item item = new Item (name, selInInt, qualityInt, date);
            Transaction transaction = new Transaction("bought" ,item);
            transactions.add(transaction);

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
        }
            catch (Exception e) {
                throw new Throwable(e.getMessage());

            }
        }
    }

}








