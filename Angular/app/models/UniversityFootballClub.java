package models;

/*
 * This is class UniversityFootballClub
 * It is a child class of class FootballClub

 * Last modified date - 25/12/2020
*/

public class UniversityFootballClub extends FootballClub {
    private String universityName;

    public UniversityFootballClub(String clubName, String clubLocation, String clubManager, String universityName) {
        super(clubName, clubLocation, clubManager);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "Club name : " + getClubName() + "\nManager : " + getClubManager() + "\nClub location : " + getClubLocation() + "\nUniversity : " + this.universityName + "\nNumber of Wins :" + getNumberOfWins() + "\nNumber of Draws : " + getNumberOfDraws() + "\nNumber of Defeats : " + getNumberOfDefeats() +
                "\nMatches Played : " + getMatchesPlayed() + "\nGoals Scored : " + getGoalsScored() + "\nGoals Received : " + getGoalsAgainst() + "\nCurrent Points : " + getCurrentPoints() ;
    }
}
