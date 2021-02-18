package services;

import models.FootballClub;
import models.PremierLeagueManager;
import utils.GoalComparator;
import utils.WinsComparator;

import java.util.Collections;
import java.util.List;

/*
* This is class SortService
* This class is used to retrieve sorted match statistics according to a given sorting criteria
*
* Last modified date - 30/12/2020
*/

public class SortService {

    public static List<FootballClub> sort(PremierLeagueManager premierLeagueManager, String sortingCriteria){

        if (sortingCriteria.toLowerCase().equals("points")) {
            Collections.sort(premierLeagueManager.getClubList());
        } else if (sortingCriteria.toLowerCase().equals("goals")) {
            Collections.sort(premierLeagueManager.getClubList(), new GoalComparator());
        } else {
            Collections.sort(premierLeagueManager.getClubList(), new WinsComparator());
        }

        return premierLeagueManager.getClubList();
    }
}
