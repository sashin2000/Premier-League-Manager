package model;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {

    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfDefeats;
    private int matchesPlayed;
    private int goalsScored;
    private int goalsAgainst;
    private int currentPoints;

    public FootballClub(String clubName, String clubLocation, String clubManager){
        super(clubName, clubLocation, clubManager);
        this.numberOfWins = 0;
        this.numberOfDraws = 0;
        this.numberOfDefeats = 0;
        this.matchesPlayed = 0;
        this.goalsScored = 0;
        this.goalsAgainst = 0;
        this.currentPoints = 0;
        //generateValues();
    }
    //This method is only for testing purpose
  /*  private void generateValues(){
        Random input = new Random();
        this.numberOfWins += input.nextInt(10);
        this.numberOfDraws += input.nextInt(10);
        this.currentPoints += numberOfDraws + numberOfWins*3;
        this.numberOfDefeats += input.nextInt(10);
        this.matchesPlayed += this.numberOfDefeats + this.numberOfDraws + this.numberOfWins;
        this.goalsScored += input.nextInt(10);
        this.goalsAgainst += input.nextInt(10);

    }*/

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getNumberOfDefeats() {
        return numberOfDefeats;
    }

    public void setNumberOfDefeats(int numberOfDefeats) {
        this.numberOfDefeats = numberOfDefeats;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getGoalDifference(){
        return goalsScored - goalsAgainst;
    }

    @Override
    public int compareTo(FootballClub otherClub) {
        int result = otherClub.currentPoints - this.currentPoints;
        if(result == 0){
            int thisClub = this.goalsScored-this.goalsAgainst;
            int comparingClub = otherClub.goalsScored-otherClub.goalsAgainst;
            result = comparingClub-thisClub;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Club name : " + getClubName() + "\nNumber of Wins :" + numberOfWins + "\nNumber of Draws : " + numberOfDraws + "\nNumber of Defeats : " + numberOfDefeats +
                "\nMatches Played : " + matchesPlayed + "\nGoals Scored : " + goalsScored + "\nGoals Received : " + goalsAgainst + "\nCurrent Points : " + currentPoints ;
    }
}
