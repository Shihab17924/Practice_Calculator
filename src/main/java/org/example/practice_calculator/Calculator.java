package org.example.practice_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        // Set the application icon (logo)
        Image icon = new Image(getClass().getResourceAsStream("/Image/Loge.jpg")); // Adjusted path to "Image/Loge.jpg"
        stage.getIcons().add(icon);

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
