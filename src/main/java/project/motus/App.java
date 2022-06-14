package project.motus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import project.motus.controller.UIController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("UI", stage), 550, 540);
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.show();
    }


    private static Parent loadFXML(String fxml, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        UIController uiController = new UIController(stage);
        fxmlLoader.setController(uiController);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
 

}