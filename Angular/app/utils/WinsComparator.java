package utils;

import models.FootballClub;

import java.util.Comparator;

/*
 * This class is WinsComparator
 * This class is used to compare number of wins in descending order
 *
 * Last modified date - 25/12/2020
 */

public class WinsComparator implements Comparator<FootballClub> {
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
        return club2.getNumberOfWins() - club1.getNumberOfWins();
    }
}
