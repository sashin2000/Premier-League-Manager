package services;

import models.*;

import java.util.ArrayList;
import java.util.List;

/*
* This is class LeagueTableService
* This class is used for retrieving the filtered data,
* inorder to populate them in 3 separate tables of GUI
*
* Last modified date - 01/01/2021
*/

public class LeagueTableService {

    public static List<FootballClub> getMatchStats(String clubType, String sortingCriteria) {

        PremierLeagueManager premierLeagueManager = DatabaseConnectivityService.loadFromDatabase();

        List<FootballClub> clubList = SortService.sort(premierLeagueManager, sortingCriteria);
        /* Here is a small description on parameter sorting criteria
         *       points --> Sort descending order according to number of points
         *       goals --> Sort descending order according to number of goals
         *       wins --> Sort descending order according to number of wins
         */

        List<FootballClub> tempList = new ArrayList<>();

        for (FootballClub club : clubList) {

            // If club type is School football club, add the club to the temporary list.
            if (club instanceof SchoolFootballClub) {
                tempList.add(club);
            }
        }

        if (clubType.toLowerCase().equals("school")) {
            return tempList;
        }

        tempList.clear();

        for (FootballClub club : clubList) {

            // If club type is University football club, add the club to the temporary list.
            if (club instanceof UniversityFootballClub) {
                tempList.add(club);
            }
        }

        if (clubType.toLowerCase().equals("university")) {
            return tempList;
        }

        tempList.clear();

        for (FootballClub club : clubList) {

            // If club type is Default football club, add the club to the temporary list.
            if (club instanceof FootballClub && !(club instanceof SchoolFootballClub || club instanceof UniversityFootballClub)) {
                tempList.add(club);
            }
        }
        return tempList;
    }
}
