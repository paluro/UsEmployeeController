package com.slackers.inc.Boundary.BoundaryControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable{

    @FXML private AnchorPane mainContainer;
    @FXML private AnchorPane results;
    @FXML private AnchorPane search;
    @FXML private AnchorPane applications;
    @FXML private AnchorPane form;
    @FXML private AnchorPane settings;

    SearchController searchController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the main controller. Success!");
        try {
            results = FXMLLoader.load(getClass().getResource("../FXML/results.fxml"));
            applications = FXMLLoader.load(getClass().getResource("../FXML/applications.fxml"));
            form =  FXMLLoader.load(getClass().getResource("../FXML/form.fxml"));
            settings = FXMLLoader.load(getClass().getResource("../FXML/settings.fxml"));

            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("../FXML/search.fxml"));
            search = searchLoader.load();
            SearchController searchController = searchLoader.getController();
            searchController.setMainController(this);


            mainContainer.getChildren().setAll(applications);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applicationClick(){
        mainContainer.getChildren().setAll(applications);
    }

    @FXML
    private void searchClick(){
        mainContainer.getChildren().setAll(search);
    }

    @FXML
    private void settingsClick(){
        mainContainer.getChildren().setAll(settings);
    }

    @FXML
    public void  resultsClick(){
        mainContainer.getChildren().setAll(results);
    }




}
