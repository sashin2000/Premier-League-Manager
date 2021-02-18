package view;

import model.Date;
import controller.PlayedMatchesViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayedMatchesView {

    public static void display(){
        Stage playedMatchesWindow = new Stage();
        playedMatchesWindow.setTitle("Played Matches");
        playedMatchesWindow.initModality(Modality.APPLICATION_MODAL);

        TableView playedMatchesTable = PlayedMatchesViewController.createTable();

        PlayedMatchesViewController.populateTable(playedMatchesTable);

        Label lblHeader = new Label("Played Matches");
        lblHeader.getStyleClass().clear();
        lblHeader.getStyleClass().add("sub-header");

        TextField txtFldSearchBar = new TextField();

        Button btnSearch = new Button("Search");
        btnSearch.setId("search-btn");

        Button btnRefresh = new Button("Refresh");
        btnRefresh.setId("search-btn");

        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtFldSearchBar.getText().equals("")){
                    Date searchDate = new Date("0-0-0");
                    PlayedMatchesViewController.populateTable(playedMatchesTable, searchDate);
                }else {
                    Date searchDate = new Date(txtFldSearchBar.getText());
                    PlayedMatchesViewController.populateTable(playedMatchesTable, searchDate);
                }

            }
        });

        btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayedMatchesViewController.populateTable(playedMatchesTable);
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(playedMatchesWindow.getMaxWidth(), playedMatchesWindow.getMaxHeight());
        gridPane.setVgap(15);
        gridPane.setHgap(5);

        GridPane.setConstraints(lblHeader,0,1, 3,1);
        GridPane.setConstraints(btnSearch,1,3);
        GridPane.setConstraints(btnRefresh,2,3);
        GridPane.setConstraints(txtFldSearchBar,0,3);
        GridPane.setConstraints(playedMatchesTable,0,5,3,1);
        gridPane.getChildren().addAll(lblHeader, btnSearch, btnRefresh, txtFldSearchBar, playedMatchesTable);

        Scene scene = new Scene(gridPane,playedMatchesWindow.getMinWidth(), playedMatchesWindow.getMinHeight());
        scene.getStylesheets().addAll("css/mainStyles.css", "css/playedMatchesStyles.css");

        playedMatchesWindow.setMinHeight(550);
        playedMatchesWindow.setMinWidth(950);
        playedMatchesWindow.setMaxHeight(600);
        playedMatchesWindow.setMaxWidth(1000);

        playedMatchesWindow.setScene(scene);
        playedMatchesWindow.showAndWait();
    }

}
