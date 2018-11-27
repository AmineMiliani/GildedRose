package edu.insightr.gildedrose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            // TODO (PBZ) : using pixel size into your fxml is not a good idea (no responsive, no decoration isolation)
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Inventory.fxml")));
            primaryStage.setTitle("Main Window");
            Scene scene = new Scene(root, 1000, 400);
            scene.getStylesheets().add("styles.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e) {
        e.printStackTrace();
    }
    }
}
