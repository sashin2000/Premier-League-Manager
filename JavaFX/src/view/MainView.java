package view;

import controller.MainViewController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends Application {

    private static TableView defaultFootballClubTable;
    private static TableView schoolFootballClubTable;
    private static TableView universityFootballClubTable;

    public static void display(){
        System.out.println("Opened");
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Adding a label as Header
        Label lblHeader = new Label("Football Premier League");
        lblHeader.setId("header");

        // Adding the football image to an Image view
        ImageView imgFootball = new ImageView(new Image("images/football.jpg"));
        imgFootball.opacityProperty().setValue(0.8);

        Label lblSortBy = new Label("Sort By :\t");

        ChoiceBox<String> sortByMenu = new ChoiceBox<>();

        HBox sortBox = new HBox(lblSortBy, sortByMenu);

        sortByMenu.getItems().addAll("No. of Points","No. of Goals","No. of Wins");
        sortByMenu.setValue("No. of Points");

        Button btnPlayedMatches = new Button("View Played Matches");
        Button btnGenerateMatch = new Button("Generate Random Match");
        Button btnExit = new Button("Exit");

        // Creating tabs for the tab pane
        Tab defaultFootballClubTab = new Tab("Default Football Club");
        Tab schoolFootballClubTab = new Tab("School Football Club");
        Tab universityFootballClubTab = new Tab("University Football Club");

        this.defaultFootballClubTable =  MainViewController.createTable();
        this.schoolFootballClubTable = MainViewController.createTable();
        this.universityFootballClubTable = MainViewController.createTable();

        // This method is populating the tables separately according to football club types
        populateTables(1);
        defaultFootballClubTab.setContent(defaultFootballClubTable);
        schoolFootballClubTab.setContent(schoolFootballClubTable);
        universityFootballClubTab.setContent(universityFootballClubTable);


        // Disable the closing feature of tabs
        defaultFootballClubTab.setClosable(false);
        schoolFootballClubTab.setClosable(false);
        universityFootballClubTab.setClosable(false);

        // Creating tab pane and inserting tabs into it
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(defaultFootballClubTab, schoolFootballClubTab, universityFootballClubTab);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setMargin(gridPane, new Insets(5,5,5,5));
        gridPane.setPrefSize(primaryStage.getMaxWidth(), primaryStage.getMaxHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        GridPane.setConstraints(lblHeader,0,0);
        GridPane.setConstraints(sortBox,1,0);
        GridPane.setConstraints(imgFootball,0,1);
        GridPane.setConstraints(btnPlayedMatches,0,2);
        GridPane.setConstraints(btnGenerateMatch,0,3);
        GridPane.setConstraints(btnExit,0,4);
        GridPane.setConstraints(tabPane,1,1,2,4);

        btnPlayedMatches.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayedMatchesView.display();
            }
        });

        btnGenerateMatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GenerateMatchView.display();
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

       sortByMenu.valueProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               MainViewController.sortByGivenCriteria(newValue);
           }
       });

        gridPane.getChildren().addAll(lblHeader, sortBox, imgFootball, btnPlayedMatches,btnGenerateMatch,btnExit, tabPane);
        Scene scene = new Scene(gridPane,primaryStage.getMinWidth(), primaryStage.getMinHeight());
        scene.getStylesheets().add("css/mainStyles.css");

        primaryStage.setTitle("PremierLeagueManager");
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1560);
        primaryStage.setMaxHeight(710);
        primaryStage.setMaxWidth(1600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void populateTables(int sortingIndex){
        MainViewController.populateTables(defaultFootballClubTable, schoolFootballClubTable, universityFootballClubTable, sortingIndex);
    }

}
