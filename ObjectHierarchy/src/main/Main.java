package main;

import environment.HierarchyFactory;
import io.XMLParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import window.WindowController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../window/window.fxml"));
        Parent root = loader.load();
        WindowController controller = loader.getController();
        primaryStage.setOnCloseRequest(e -> controller.stopSimulation());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        try {
            XMLParser input = new XMLParser("./resrc/hierarchy.xml");
            HierarchyFactory.getInstance(input);
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
