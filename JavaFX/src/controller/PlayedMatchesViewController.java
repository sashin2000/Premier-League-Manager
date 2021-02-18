package controller;

import model.Date;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DateComparator;
import model.Main;
import model.PlayedMatch;

import java.util.*;

public class PlayedMatchesViewController {

    public static TableView createTable(){
        TableView playedMatchesTable = new TableView();
        playedMatchesTable.setMinWidth(919);
        playedMatchesTable.setPlaceholder(new Label("No match statistics to display"));

        TableColumn<PlayedMatch, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("playedDate"));

        TableColumn<PlayedMatch, String> team1Column = new TableColumn<>("Team 1");
        team1Column.setCellValueFactory(new PropertyValueFactory<>("team1"));

        TableColumn<PlayedMatch, String> score1Column = new TableColumn<>("Score 1");
        score1Column.setCellValueFactory(new PropertyValueFactory<>("score1"));

        TableColumn<PlayedMatch, String> team2Column = new TableColumn<>("Team 2");
        team2Column.setCellValueFactory(new PropertyValueFactory<>("team2"));

        TableColumn<PlayedMatch, String> score2Column = new TableColumn<>("Score 2");
        score2Column.setCellValueFactory(new PropertyValueFactory<>("score2"));



        // Setting minimum widths for columns
        dateColumn.setMinWidth(200);
        team1Column.setMinWidth(250);
        score1Column.setMinWidth(100);
        team2Column.setMinWidth(250);
        score2Column.setMinWidth(100);

        // Disabling the resizing ability of columns

        //dateColumn.setResizable(false);
        team1Column.setResizable(false);
        score1Column.setResizable(false);
        team2Column.setResizable(false);
        score2Column.setResizable(false);

        playedMatchesTable.getColumns().addAll(dateColumn, team1Column, score1Column, team2Column, score2Column);

        return playedMatchesTable;
    }

    public static void populateTable(TableView playedMatchesTable){
        //At the beginning of population of tables, all the existing data (if exists) will be cleared out
        playedMatchesTable.getItems().clear();

        List<PlayedMatch> sortedPlayedMatchList = processRecords();

        for (PlayedMatch playedMatch: sortedPlayedMatchList) {
            playedMatchesTable.getItems().add(playedMatch);
        }
    }

    public static void populateTable(TableView playedMatchesTable, Date searchDate){
        //At the beginning of population of tables, all the existing data (if exists) will be cleared out
        playedMatchesTable.getItems().clear();

        List<PlayedMatch> sortedPlayedMatchList = processRecords();

        for (PlayedMatch playedMatch: sortedPlayedMatchList) {
            if ((playedMatch.getPlayedDate().toString()).equals(searchDate.toString())){
                playedMatchesTable.getItems().add(playedMatch);
            }

        }
    }

    public static List<PlayedMatch> processRecords(){
        //Populating the tables separately according to football club types
        HashMap<Date, ArrayList<PlayedMatch>> matchDates = Main.premierLeagueManager.getMatches();

        Object[] dateKeyArray = matchDates.keySet().toArray();
        List<Date> dateKeyList = new ArrayList<>();
        for (int i = 0; i < dateKeyArray.length; i++) {
            dateKeyList.add((Date) dateKeyArray[i]);
        }

        Collections.sort(dateKeyList, new DateComparator());

        List<PlayedMatch> sortedPlayedMatchList = new ArrayList<>();

        /*System.out.println("|\tDate\t|\t\tTeam 1\t\t|\tScore 1\t|\t\tTeam 2\t\t|\tScore 2\t|");
        System.out.println("-----------------------------------------------------------------------------");*/

        for (Date date: dateKeyList) {
            for (PlayedMatch playedMatch:matchDates.get(date)) {

                sortedPlayedMatchList.add(playedMatch);
             /*   System.out.format(
                        "%5s%20s%10d%20s%10d%1s",
                        date.toString(), playedMatch.getTeam1(), playedMatch.getScore1(), playedMatch.getTeam2(), playedMatch.getScore2(), "\n"
                );*/
            }
        }
        return sortedPlayedMatchList;
    }

}
