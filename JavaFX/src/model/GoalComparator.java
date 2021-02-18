package model;

import java.util.Comparator;

public class GoalComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub club1, FootballClub club2) {
        /*
         * Here, it sorts according to the value that is returned by this method
         *      It returns -1 if the value of first argument(club1) is lesser than second argument(club2).
         *      It returns 0 if the value of first argument(club1) is equal to the second argument(club2).
         *      It returns 1 if the value of first argument(club1) is larger than second argument(club2).
         *
         * So, as I want to sort in descending order, I make to return the values in the opposite way.
         */

        return club2.getGoalsScored() - club1.getGoalsScored();
    }
}
