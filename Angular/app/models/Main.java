package models;

import services.DatabaseConnectivityService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
* This is the main console. There are eight selections can be done by the user in this console.
*   Add football club
*   Delete football club
*   Displaying statistics of a selected football club
*   Display Premiere League table
*   Adding a played match
*   Saving
*   Opening the GUI
*   Quit console
*
* Last modified date - 01/01/2021
*/

public class Main {

    public static void main(String[] args) throws IOException {

        // Whenever the application starts it loads the data from the file.
        PremierLeagueManager premierLeagueManager = DatabaseConnectivityService.loadFromDatabase();

        System.out.println("*** Welcome to Football Premier League ***");
        System.out.println("----------------------------------------\n");

        while (true){

            // Here, try and catch blocks are used inorder to handle the error of input mismatches.
            try{
                System.out.println("1 --> Add football club");
                System.out.println("2 --> Delete football club");
                System.out.println("3 --> Display the various statistics for a selected club");
                System.out.println("4 --> Display the Premier League Table");
                System.out.println("5 --> Add a match");
                System.out.println("6 --> Save");
                System.out.println("7 --> Open GUI");
                System.out.println("8 --> Quit\n");

                System.out.print("Select an index from the options given above: ");
                Scanner input = new Scanner(System.in);

                switch (input.nextInt()){
                    case 1:
                        System.out.println("\nAdd Football Club");
                        System.out.println("-----------------\n");

                        System.out.println("1 --> Default football club");
                        System.out.println("2 --> University football club");
                        System.out.println("3 --> School football club\n");
                        System.out.print("Enter the type of football club : ");

                        switch (input.nextInt()){
                            case 1:
                                premierLeagueManager.createNewClub(1);
                                break;
                            case 2:
                                premierLeagueManager.createNewClub(2);
                                break;
                            case 3:
                                premierLeagueManager.createNewClub(3);
                                break;
                        }

                        break;
                    case 2:
                        System.out.println("\nDelete Football Club");
                        System.out.println("--------------------");

                        premierLeagueManager.deleteClub();
                        break;
                    case 3:
                        System.out.println("\nDisplay Statistics");
                        System.out.println("------------------");

                        premierLeagueManager.displayStatistics();
                        break;
                    case 4:
                        System.out.println("\nDisplay Premier League Table");
                        System.out.println("----------------------------");

                        premierLeagueManager.displayTable();
                        break;
                    case 5:
                        System.out.println("\nAdd a played match");
                        System.out.println("------------------");

                        premierLeagueManager.addPlayedMatch();
                        break;
                    case 6:
                        DatabaseConnectivityService.saveToDatabase();
                        break;
                    case 7:
                        String command = "cmd.exe /c start cmd.exe /k \"sbt run\n\"";
                        Runtime.getRuntime().exec(command);

                        break;
                    case 8:
                        System.out.println("Good Bye !!!");
                        System.exit(1);
                        break;
                    case 9:
                        for (FootballClub club: premierLeagueManager.getClubList()) {
                            club.setGoalsScored(0);
                            club.setGoalsAgainst(0);
                            club.setMatchesPlayed(0);
                            club.setCurrentPoints(0);
                            club.setNumberOfDefeats(0);
                            club.setNumberOfWins(0);
                            club.setNumberOfDraws(0);
                        }
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            }catch (InputMismatchException e){
                System.out.println("\nPlease enter integers only\n");
            }

        }

    }
}
