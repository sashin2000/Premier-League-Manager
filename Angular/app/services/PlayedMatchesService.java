package services;

import models.Date;
import models.PlayedMatch;
import models.PremierLeagueManager;
import utils.DateComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/*
* This is class PlayedMatchesService
* This class is used for retrieve all played matches with played date and match results
* Also this class is used for retrieve those results by searching for a particular date
*
* Last modified date - 30/12/2020
*/

public class PlayedMatchesService {

    public static List<PlayedMatch> getMatches(String searchDate){
        PremierLeagueManager premierLeagueManager = DatabaseConnectivityService.loadFromDatabase();

        HashMap<Date, ArrayList<PlayedMatch>> matchDates = premierLeagueManager.getMatches();

        // Get an array of keys of the hashmap matchDates
        Object[] dateKeyArray = matchDates.keySet().toArray();

        // Create a list an add those dates in it
        List<Date> dateKeyList = new ArrayList<>();
        for (int i = 0; i < dateKeyArray.length; i++) {
            dateKeyList.add((Date) dateKeyArray[i]);
        }

        // Sort the dates in the list in ascending order using DateComparator()
        Collections.sort(dateKeyList, new DateComparator());

        List<PlayedMatch> sortedPlayedMatchList = new ArrayList<>();
        List<PlayedMatch> searchedPlayedMatchList = new ArrayList<>();

        // Add all played matches into the list sortedPlayedMatchList according to the sorted date order
        for (Date date: dateKeyList) {
            for (PlayedMatch playedMatch:matchDates.get(date)) {

                sortedPlayedMatchList.add(playedMatch);
            }
        }

        /* If the searchDate parameter is not equals to default,
        * it means the user has passed a proper date to to search.
        * So, it adds the all the matches played in particular date to the list searchedPlayedMatchList
        */
        if(!searchDate.equals("default")){
            for (PlayedMatch playedMatch: sortedPlayedMatchList) {
                if ((playedMatch.getPlayedDate().toString()).equals(searchDate)){
                    searchedPlayedMatchList.add(playedMatch);
                }
            }
            return searchedPlayedMatchList;
        }


        return sortedPlayedMatchList;
    }
}
