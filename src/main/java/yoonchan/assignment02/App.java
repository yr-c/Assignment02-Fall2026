package yoonchan.assignment02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(App.class.getResourceAsStream("fonts/Outfit-Black.ttf"), 16);
        Font.loadFont(App.class.getResourceAsStream("fonts/Outfit-Bold.ttf"), 16);
        Font.loadFont(App.class.getResourceAsStream("fonts/Outfit-Light.ttf"), 16);
        Font.loadFont(App.class.getResourceAsStream("fonts/Outfit-Regular.ttf"), 16);

        scene = new Scene(loadFXML("Slideshow"), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}