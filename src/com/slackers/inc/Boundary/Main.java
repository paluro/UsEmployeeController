package com.slackers.inc.Boundary;

import com.slackers.inc.Controllers.AccountController;
import com.slackers.inc.database.DerbyConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent login = FXMLLoader.load(getClass().getResource("FXML/outershell.fxml"));
        String cssDoc = getClass().getResource("CSS/custom.css").toExternalForm();
        login.getStylesheets().add(cssDoc);

        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(login));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
