package controller;

import model.*;
import view.MainView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainViewController {

    private static List<FootballClub> clubList = Main.premierLeagueManager.getClubList();
    private static HashMap<Date, ArrayList<PlayedMatch>> matches= Main.premierLeagueManager.getMatches();

    public static List<FootballClub> getFootBallClubs(int tableIndex, int sortingIndex){
        /*
        * Here is the mapping of sorting index to the sorting criteria
        *       1 --> Sort descending order according to number of points
        *       2 --> Sort descending order according to number of goals
        *       3 --> Sort descending order according to number of wins
        * */

        if(sortingIndex == 1){
            Collections.sort(clubList);
        }else if(sortingIndex == 2){
            Collections.sort(clubList, new GoalComparator());
        }else{
            Collections.sort(clubList, new WinsComparator());
        }

        /*
         *
         * Here I have used three indexes to separate three football club types
         * 0 - Default Football Club
         * 1 - School Football Club
         * 2 - University Football Club
         *
         */

        ArrayList<FootballClub> tempList = new ArrayList<>();

        for (FootballClub club: clubList)  {

            // If club type is School football club, add the club to the temporary list.
            if (club instanceof SchoolFootballClub){
                tempList.add(club);
            }
        }

        if (tableIndex == 1){
            return tempList;
        }

        tempList.clear();

        for (FootballClub club: clubList)  {

            // If club type is University football club, add the club to the temporary list.
            if (club instanceof UniversityFootballClub){
                tempList.add(club);
            }
        }

        if (tableIndex == 2){
            return tempList;
        }

        tempList.clear();

        for (FootballClub club: clubList)  {

            // If club type is Default football club, add the club to the temporary list.
            if (club instanceof FootballClub && !(club instanceof SchoolFootballClub || club instanceof UniversityFootballClub)){
                tempList.add(club);
            }
        }
        return tempList;
    }

    public static void sortByGivenCriteria(String criteria){

        if (criteria.equals("No. of Points")) {
            MainView.populateTables(1);
        }else if (criteria.equals("No. of Goals")){
            MainView.populateTables(2);
        }else {
            MainView.populateTables(3);
        }
    }

    protected static void updateDetails(){
        clubList = Main.premierLeagueManager.getClubList();
        matches= Main.premierLeagueManager.getMatches();
    }

    public static TableView createTable(){
        TableView footballClubTable = new TableView();
        footballClubTable.setMinWidth(1075);
        footballClubTable.setPlaceholder(new Label("No match statistics to display"));

        TableColumn<SportsClub, String> clubNameColumn = new TableColumn<>("Club");
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<SportsClub, String> playedColumn = new TableColumn<>("Played");
        playedColumn.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        TableColumn<SportsClub, String> wonColumn = new TableColumn<>("Won");
        wonColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));

        TableColumn<SportsClub, String> drawnColumn = new TableColumn<>("Drawn");
        drawnColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));

        TableColumn<SportsClub, String> lossColumn = new TableColumn<>("Loss");
        lossColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDefeats"));

        TableColumn<SportsClub, String> goalScoredColumn = new TableColumn<>("Goals Scored");
        goalScoredColumn.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));

        TableColumn<SportsClub, String> goalAgainstColumn = new TableColumn<>("Goals Against");
        goalAgainstColumn.setCellValueFactory(new PropertyValueFactory<>("goalsAgainst"));

        TableColumn<SportsClub, String> goalDifferenceColumn = new TableColumn<>("Goal Difference");
        goalDifferenceColumn.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));

        TableColumn<SportsClub, String> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("currentPoints"));

        // Setting minimum widths for columns
        clubNameColumn.setMinWidth(200);
        playedColumn.setMinWidth(100);
        wonColumn.setMinWidth(100);
        drawnColumn.setMinWidth(100);
        lossColumn.setMinWidth(100);
        goalScoredColumn.setMinWidth(125);
        goalDifferenceColumn.setMinWidth(125);
        goalAgainstColumn.setMinWidth(125);
        pointsColumn.setMinWidth(100);

        // Disabling the resizing ability of columns

        //clubNameColumn.setResizable(false);
        playedColumn.setResizable(false);
        wonColumn.setResizable(false);
        drawnColumn.setResizable(false);
        lossColumn.setResizable(false);
        goalScoredColumn.setResizable(false);
        goalDifferenceColumn.setResizable(false);
        goalAgainstColumn.setResizable(false);
        pointsColumn.setResizable(false);

        footballClubTable.getColumns().addAll(clubNameColumn, playedColumn, wonColumn, drawnColumn, lossColumn, goalScoredColumn, goalAgainstColumn, goalDifferenceColumn, pointsColumn);

        return footballClubTable;
    }

    public static void populateTables(TableView defaultFootballClubTable, TableView schoolFootballClubTable, TableView universityFootballClubTable, int sortingIndex){
        //At the beginning of population of tables, all the existing data (if exists) will be cleared out
        defaultFootballClubTable.getItems().clear();
        schoolFootballClubTable.getItems().clear();
        universityFootballClubTable.getItems().clear();

        //Populating the tables separately according to football club types
        for (FootballClub club: MainViewController.getFootBallClubs(0, sortingIndex)) {
            defaultFootballClubTable.getItems().add(club);
        }

        for (FootballClub club: MainViewController.getFootBallClubs(1, sortingIndex)) {
            schoolFootballClubTable.getItems().add(club);
        }

        for (FootballClub club: MainViewController.getFootBallClubs(2, sortingIndex)) {
            universityFootballClubTable.getItems().add(club);
        }
    }
}
