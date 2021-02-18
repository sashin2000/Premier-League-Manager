package controller;

import model.Date;
import model.FootballClub;
import model.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateMatchController {

    private static Random randInput = new Random();

    public static ArrayList generateMatch(){

        int category = selectCategory();
        List<FootballClub> categorizedFootballClubs = MainViewController.getFootBallClubs(category, 1);

        if (categorizedFootballClubs.size() < 2){
            return null;
        }else{
            int team1Index = selectTeam(categorizedFootballClubs);
            int team2Index = selectTeam(categorizedFootballClubs);

            while (team1Index == team2Index){
                team2Index = selectTeam(categorizedFootballClubs);
            }

            FootballClub team1 = categorizedFootballClubs.get(team1Index);
            FootballClub team2 = categorizedFootballClubs.get(team2Index);

            int team1Score = randInput.nextInt(10);
            int team2Score = randInput.nextInt(10);

            // Generating a random date
            Date matchDate = generateDate();

            // Adding the played match to the match hashmap that was declared in premierLeagueManager object
            Main.premierLeagueManager.addToPlayedMatchList(team1.getClubName(), team1Score, team2.getClubName(), team2Score, matchDate);

            // Updating the changes done to the playedMatches hashmap in premierLeagueManager object
            MainViewController.updateDetails();

            ArrayList data = new ArrayList();
            data.add(team1.getClubName());
            data.add(team1Score);
            data.add(team2.getClubName());
            data.add(team2Score);
            data.add(matchDate.toString());
            return data;
        }


    }

    private static int selectCategory(){
        int category = randInput.nextInt(3);
        return category;
    }

    private static int selectTeam(List<FootballClub> footballClubs){
        int club = randInput.nextInt(footballClubs.size());
        return club;
    }

    private static Date generateDate(){
        int day = randInput.nextInt(31) + 1;
        int month = randInput.nextInt(5) + 1;
        int year = 2021;

        Date date = new Date(day+"-"+month+"-"+year);

        while (!date.isValid()){
            day = randInput.nextInt(31) + 1;
            month = randInput.nextInt(5) + 1;
            year = 2021;

            date = new Date(day+"-"+month+"-"+year);
        }

        return date;
    }
}
