package models;

/*
* This is interface Premiere League
* abstract methods below are the services that must be included with Premiere League Manager
*
* Last modified date - 25/12/2020
*/

public interface PremierLeague {
    void createNewClub(int clubType);
    void deleteClub();
    void displayStatistics();
    void displayTable();
    void addPlayedMatch();
}
