package view;

import controller.GenerateMatchController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GenerateMatchView {
    public static void display(){
        Stage generateMatchWindow = new Stage();
        generateMatchWindow.setTitle("Generate a New Match");

        Label header = new Label("Generate Match");
        header.getStyleClass().clear();
        header.getStyleClass().add("sub-header");

        Label lblTeam1 = new Label("Team1");
        lblTeam1.paddingProperty().setValue(new Insets(0,0,0,100));
        lblTeam1.getStyleClass().clear();
        lblTeam1.getStyleClass().add("lbl-team-name");

        Label lblVs = new Label("Vs");
        lblVs.paddingProperty().setValue(new Insets(50,100,50, 100));
        lblVs.getStyleClass().clear();
        lblVs.getStyleClass().add("lbl-vs");

        Label lblTeam2 = new Label("Team2");
        lblTeam2.getStyleClass().clear();
        lblTeam2.getStyleClass().add("lbl-team-name");

        Label lblTeam1Score = new Label("Score1");
        lblTeam1Score.paddingProperty().setValue(new Insets(0,0,0,100));
        lblTeam1Score.getStyleClass().clear();
        lblTeam1Score.getStyleClass().add("lbl-team-score1");

        Label lblColan = new Label(":");
        lblColan.paddingProperty().setValue(new Insets(50,100,50,100));
        lblColan.getStyleClass().clear();
        lblColan.getStyleClass().add("lbl-vs");

        Label lblTeam2Score = new Label("Score2");
        lblTeam2Score.getStyleClass().clear();
        lblTeam2Score.getStyleClass().add("lbl-team-score2");


        Label dateDisplay = new Label("Date Display");

        Button btnGenerate = new Button("Generate");
        btnGenerate.setId("generate-btn");

        btnGenerate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList data = GenerateMatchController.generateMatch();

                while (data == null){
                    data = GenerateMatchController.generateMatch();
                }
                //GenerateMatchController.generateMatch();
                lblTeam1.setText(data.get(0).toString());
                lblTeam2.setText(data.get(2).toString());
                lblTeam1Score.setText(data.get(1).toString());
                lblTeam2Score.setText(data.get(3).toString());
                dateDisplay.setText("Date : " + data.get(4).toString());

                MainView.populateTables(1);
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setMargin(gridPane, new Insets(5,5,5,5));
        gridPane.setPrefSize(generateMatchWindow.getMaxWidth(), generateMatchWindow.getMaxHeight());
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        GridPane.setConstraints(header,0,0, 3,1);
        GridPane.setConstraints(lblTeam1,0,4);
        GridPane.setConstraints(lblVs,1,4);
        GridPane.setConstraints(lblTeam2,2,4);
        GridPane.setConstraints(lblTeam1Score,0,5);
        GridPane.setConstraints(lblColan,1,5);
        GridPane.setConstraints(lblTeam2Score,2,5);
        GridPane.setConstraints(dateDisplay,0,6,3,1);
        GridPane.setConstraints(btnGenerate,0,8,3,1);

        gridPane.getChildren().addAll(header, lblTeam1, lblTeam2, lblVs, lblTeam1Score, lblColan, lblTeam2Score, dateDisplay, btnGenerate);
        Scene scene = new Scene(gridPane,generateMatchWindow.getMaxWidth(), generateMatchWindow.getMaxHeight());
        scene.getStylesheets().addAll("css/mainStyles.css","css/generateMatchStyles.css");

        generateMatchWindow.setMinHeight(600);
        generateMatchWindow.setMinWidth(900);
        generateMatchWindow.setMaxHeight(800);
        generateMatchWindow.setMaxWidth(1200);
        generateMatchWindow.setScene(scene);
        generateMatchWindow.showAndWait();
    }
}
