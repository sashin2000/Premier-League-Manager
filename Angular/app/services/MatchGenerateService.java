package services;

import models.Date;
import models.FootballClub;
import models.PremierLeagueManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* This is class MatchGenerateService
* This class is used to generate a random played match statistic
*
* Last modified date - 01/01/2021
*/

public class MatchGenerateService {

    private static final Random randInput = new Random();

    public static ArrayList generateMatch(){

        PremierLeagueManager premierLeagueManager = DatabaseConnectivityService.loadFromDatabase();

        if(premierLeagueManager.getClubList().size() < 2){
            throw new RuntimeException("There is no sufficient teams to generate a random match");
        }

        // Selecting a club category
        String category = selectCategory();

        // Retrieving match statistics related to selected club category
        List<FootballClub> categorizedFootballClubs = LeagueTableService.getMatchStats(category, "points");

        // Select the club category until the number of clubs in the particular category will be at least 2
        while (categorizedFootballClubs.size() < 2){
            category = selectCategory();
            categorizedFootballClubs = LeagueTableService.getMatchStats(category, "points");
        }

        // Select two different teams from selected club category
        int team1Index = selectTeam(categorizedFootballClubs);
        int team2Index = selectTeam(categorizedFootballClubs);

        while (team1Index == team2Index){
            team2Index = selectTeam(categorizedFootballClubs);
        }

        FootballClub team1 = categorizedFootballClubs.get(team1Index);
        FootballClub team2 = categorizedFootballClubs.get(team2Index);

        int team1Score = randInput.nextInt(6);
        int team2Score = randInput.nextInt(6);

        // Generating a random date
        Date matchDate = generateDate();

        // Adding the played match to the match hashmap that was declared in premierLeagueManager object
        premierLeagueManager.addToPlayedMatchList(team1.getClubName(), team1Score, team2.getClubName(), team2Score, matchDate);

        // Updating the changes done to the playedMatches hashmap in premierLeagueManager object
        DatabaseConnectivityService.saveToDatabase();

        ArrayList data = new ArrayList();
        data.add(team1.getClubName());
        data.add(team1Score);
        data.add(team2.getClubName());
        data.add(team2Score);
        data.add(matchDate.toString());
        return data;



    }

    private static String selectCategory(){
        int categoryIndex = randInput.nextInt(3);
        String[] categories = {"default","university","school"};
        return categories[categoryIndex];
    }

    private static int selectTeam(List<FootballClub> footballClubs){
        int club = randInput.nextInt(footballClubs.size());
        return club;
    }

    private static Date generateDate(){
        //Here, random dates are created between 01/01/2021 - 30/06/2021
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
