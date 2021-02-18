package model;

import java.io.Serializable;
import java.util.*;


public class PremierLeagueManager implements PremierLeague, Serializable {
    public static final long serialVersionUID = 3089284082585951165L;

    private List<FootballClub> clubList = new ArrayList<FootballClub>();
    private HashMap<Date, ArrayList<PlayedMatch>> matches= new HashMap<>();
    transient Scanner input = new Scanner(System.in);
    public PremierLeagueManager() {

    }

    public List<FootballClub> getClubList() {
        return clubList;
    }

    public void setClubList(List<FootballClub> clubList) {
        this.clubList = clubList;
    }

    public HashMap<Date, ArrayList<PlayedMatch>> getMatches() {
        return matches;
    }

    public void setMatches(HashMap<Date, ArrayList<PlayedMatch>> matches) {
        this.matches = matches;
    }

    @Override
    public void createNewClub(int clubType) {



        System.out.print("Enter club name : ");
        String clubName = input.nextLine();
        System.out.print("Enter the name of club manager : ");
        String clubManager = input.nextLine();
        System.out.print("Enter club location : ");
        String clubLocation = input.nextLine();

        switch (clubType){
            case 1:
                clubList.add(new FootballClub(clubName, clubLocation, clubManager));
                break;
            case 2:
                System.out.print("Enter university name : ");
                String uniName = input.nextLine();
                clubList.add(new UniversityFootballClub(clubName, clubLocation, clubManager, uniName));
                break;
            case 3:
                System.out.print("Enter school name : ");
                String schoolName = input.nextLine();
                clubList.add(new SchoolFootballClub(clubName, clubLocation, clubManager, schoolName));
                break;
        }
        System.out.println("Club added successfully");
    }

    @Override
    public void deleteClub() {
        int counter=1;

        for (SportsClub club: clubList) {
            System.out.println("Team " + counter++ + " : " + club.getClubName());
        }

        System.out.print("Enter the club name to delete : ");
        String clubName = input.nextLine();

        boolean isDeleted = false;
        for (SportsClub club: clubList) {
            if ((club.getClubName().toLowerCase()).equals(clubName.toLowerCase())){
                clubList.remove(club);
                System.out.println("Club deleted successfully");
                isDeleted =true;
                break;
            }
        }

        if(!isDeleted){
            System.out.println("There is no such a team called " + clubName + ". Please try again.");
        }
    }

    @Override
    public void displayStatistics() {
        System.out.print("Enter the club name to display statistics : ");
        String clubName = input.nextLine();

        boolean isExist = false;

        for (SportsClub club: clubList) {
            if ((club.getClubName().toLowerCase()).equals(clubName.toLowerCase())){
                System.out.println(club.toString());
                isExist =true;
                break;
            }
        }

        if(!isExist){
            System.out.println("There is no such a team called " + clubName + ". Please try again.");
        }
    }

    @Override
    public void displayTable() {
        Collections.sort((List)clubList);

        ArrayList<FootballClub> tempList = new ArrayList<>();


        System.out.println("\nSchool Football Club");
        System.out.println("--------------------\n");

        System.out.println("|\tPos\t|\t\tTeam\t\t|\tMP\t|\tW\t|\tL\t|\tD\t|\tGF\t|\tGA\t|\tGD\t|\tPts\t|");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (FootballClub club: clubList)  {

            if (club instanceof SchoolFootballClub){

                tempList.add(club);
            }
        }
        printRecord(tempList);
        tempList.clear();

        System.out.println("\nUniversity Football Club");
        System.out.println("--------------------\n");

        System.out.println("|\tPos\t|\t\tTeam\t\t|\tMP\t|\tW\t|\tL\t|\tD\t|\tGF\t|\tGA\t|\tGD\t|\tPts\t|");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (FootballClub club: clubList)  {

            if (club instanceof UniversityFootballClub){
                tempList.add(club);
            }
        }

        printRecord(tempList);
        tempList.clear();

        System.out.println("\nDefault Football Club");
        System.out.println("--------------------\n");

        System.out.println("|\tPos\t|\t\tTeam\t\t|\tMP\t|\tW\t|\tL\t|\tD\t|\tGF\t|\tGA\t|\tGD\t|\tPts\t|");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (FootballClub club: clubList)  {

            if (club instanceof FootballClub && !(club instanceof SchoolFootballClub || club instanceof UniversityFootballClub)){
                tempList.add(club);
            }
        }
        printRecord(tempList);
        System.out.println();


    }

    @Override
    public void addPlayedMatch() {
        System.out.print("Enter the match date (dd-mm-yyyy): ");
        String date = input.nextLine();
        Date matchDate = new Date(date);
        if(!matchDate.isValid()){
            System.out.println(matchDate.getErrorMsg());
            return;
        }
        System.out.print("Enter Team 1: ");
        String team1 = input.nextLine();
        System.out.print("Enter Score for Team 1: ");
        int score1 = input.nextInt();
        System.out.print("Enter Team 2: ");
        input.nextLine();
        String team2 = input.nextLine();
        System.out.print("Enter Score for Team 2: ");
        int score2 = input.nextInt();
        input.nextLine();


        addToPlayedMatchList(team1, score1, team2, score2, matchDate);
        /*updateMatchStatistics(team1, score1, team2, score2);
        PlayedMatch playedMatch = new PlayedMatch(team1, score1, team2, score2);*/


       /* if(!matches.containsKey(matchDate)){
            matches.put(matchDate, new ArrayList<PlayedMatch>());
        }

        matches.get(matchDate).add(playedMatch);
        System.out.println("Match Added");*/
    }

    public void addToPlayedMatchList(String team1, int team1Score,String team2, int team2Score, Date matchDate) {

        updateMatchStatistics(team1, team1Score, team2, team2Score);
        PlayedMatch playedMatch = new PlayedMatch(team1, team1Score, team2, team2Score, matchDate);

        if(!matches.containsKey(matchDate)){
            matches.put(matchDate, new ArrayList<PlayedMatch>());
        }

        matches.get(matchDate).add(playedMatch);

    }

    private void updateMatchStatistics(String team1, int score1, String team2, int score2){

        // Selecting which team has won
        if (score1 > score2){

            /*
             *If team1 has won,
             *Traversing through club list and find the team1 and updating winning statistics.
             *Traversing through club list and find the team2 and updating Defeat statistics.
             */
            updateWinningAndDefeatingScores(team1, score1, team2, score2);

        } else if (score1 == score2){

            //Select the two teams which has drawn
            //traversing through club list and find the relevant teams
            for (FootballClub club: clubList) {
                if(club.getClubName().equals(team1) || club.getClubName().equals(team2)){
                    int numberOfDraws = club.getNumberOfDraws();;
                    numberOfDraws++;

                    int currentPoints = club.getCurrentPoints() + 1;

                    int matchesPlayed = club.getMatchesPlayed();
                    matchesPlayed++;

                    int goalsScored = club.getGoalsScored() + score1;
                    int goalsAgainst = club.getGoalsAgainst() + score2;

                    club.setNumberOfDraws(numberOfDraws);
                    club.setCurrentPoints(currentPoints);
                    club.setMatchesPlayed(matchesPlayed);
                    club.setGoalsScored(goalsScored);
                    club.setGoalsAgainst(goalsAgainst);
                }
            }
        } else {

            /*
             *If team2 has won,
             *Traversing through club list and find the team2 and updating winning statistics.
             *Traversing through club list and find the team1 and updating Defeat statistics.
             */

            updateWinningAndDefeatingScores(team2, score2, team1, score1);
        }
    }

    private void updateWinningAndDefeatingScores(String teamWon, int winScore, String teamDefeated, int defeatScore) {
        for (FootballClub club: clubList) {
            if(club.getClubName().equals(teamWon)){
                int numberOfWins = club.getNumberOfWins();
                numberOfWins++;

                int currentPoints = club.getCurrentPoints() + 3;

                int matchesPlayed = club.getMatchesPlayed();
                matchesPlayed++;

                int goalsScored = club.getGoalsScored() + winScore;
                int goalsAgainst = club.getGoalsAgainst() + defeatScore;

                club.setNumberOfWins(numberOfWins);
                club.setCurrentPoints(currentPoints);
                club.setMatchesPlayed(matchesPlayed);
                club.setGoalsScored(goalsScored);
                club.setGoalsAgainst(goalsAgainst);
            }

            if(club.getClubName().equals(teamDefeated)){
                int numberOfDefeats = club.getNumberOfDefeats();
                numberOfDefeats++;

                int matchesPlayed = club.getMatchesPlayed();
                matchesPlayed++;

                int goalsScored = club.getGoalsScored() + defeatScore;
                int goalsAgainst = club.getGoalsAgainst() + winScore;

                club.setNumberOfDefeats(numberOfDefeats);
                club.setMatchesPlayed(matchesPlayed);
                club.setGoalsScored(goalsScored);
                club.setGoalsAgainst(goalsAgainst);
            }
        }
    }

    private void printRecord(List<FootballClub> clubList){
        int pos = 0;
        for (FootballClub club : clubList)
        System.out.format(
                "%5d%15s%13d%8d%8d%8d%8d%8d%8d%8d%2s",
                ++pos, club.getClubName(), club.getMatchesPlayed(), club.getNumberOfWins(),
                club.getNumberOfDefeats(), club.getNumberOfDraws(), club.getGoalsScored(),
                club.getGoalsAgainst(), (club.getGoalsScored()-club.getGoalsAgainst()), club.getCurrentPoints(),"\n"
        );
    }
}
