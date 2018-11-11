package edu.insightr.gildedrose;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    private ListView<?> listViewItems;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<?> listViewTestResult;

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
    public void initialize(URL arg0, ResourceBundle arg1)
        {

    }
}
