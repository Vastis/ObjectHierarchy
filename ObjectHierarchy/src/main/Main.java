package main;

import hierachy.SimObject;
import io.XMLParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import window.WindowController;

import java.util.ArrayList;

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
        launch(args);
        try {
            ArrayList<SimObject> objects =
                    new XMLParser("./resrc/hierarchy.xml").read();
            System.out.println(objects.size());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
