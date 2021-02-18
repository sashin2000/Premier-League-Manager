package models;

/*
 * This is class SchoolFootballClub
 * It is a child class of class FootballClub

 * Last modified date - 25/12/2020
 */

public class SchoolFootballClub extends FootballClub {
    private String schoolName;

    public SchoolFootballClub(String clubName, String clubLocation, String clubManager, String schoolName) {
        super(clubName, clubLocation, clubManager);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "Club name : " + getClubName() + "\nManager : " + getClubManager() + "\nClub location : " + getClubLocation() +"\nSchool name : " + this.schoolName + "\nNumber of Wins :" + getNumberOfWins() + "\nNumber of Draws : " + getNumberOfDraws() + "\nNumber of Defeats : " + getNumberOfDefeats() +
                "\nMatches Played : " + getMatchesPlayed() + "\nGoals Scored : " + getGoalsScored() + "\nGoals Received : " + getGoalsAgainst() + "\nCurrent Points : " + getCurrentPoints() ;
    }
}
