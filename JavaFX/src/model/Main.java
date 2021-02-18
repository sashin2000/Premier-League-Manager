package model;

import view.MainView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();

    public static void main(String[] args) {

        File file = new File("PremierLeagueManager.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println(e);
            }
        }

        try{
            FileInputStream fileReader = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileReader);
            PremierLeagueManager premierLeagueManagerDeserialize = (PremierLeagueManager)objIn.readObject();
            List<FootballClub> clubList = premierLeagueManagerDeserialize.getClubList();
            HashMap<Date, ArrayList<PlayedMatch>> matches= premierLeagueManagerDeserialize.getMatches();
            System.out.println("Loaded");
            premierLeagueManager.setClubList(clubList);
            premierLeagueManager.setMatches(matches);
            objIn.close();

        }catch (EOFException e){
            System.out.println("Fresh Account");
        } catch (Exception e){
            System.out.println(e);
        }

        System.out.println("*** Welcome to Football Premier League ***");
        System.out.println("----------------------------------------\n");

        while (true){
            System.out.println("1 --> Add football club");
            System.out.println("2 --> Delete football club");
            System.out.println("3 --> Display the various statistics for a selected club");
            System.out.println("4 --> Display the Premier League Table");
            System.out.println("5 --> Add a match");
            System.out.println("6 --> Save");
            System.out.println("7 --> Go for GUI");
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
                    try {
                        //Creating stream and writing the object
                        FileOutputStream fileWriter = new FileOutputStream("PremierLeagueManager.txt",false);
                        ObjectOutputStream objOut = new ObjectOutputStream(fileWriter);

                        objOut.writeObject(premierLeagueManager);
                        objOut.flush();
                        objOut.close();

                        System.out.println("Saved Successfully.\n");

                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 7:
                    MainView.display();
                    break;
                case 8:
                    System.out.println("Good Bye !!!");
                    System.exit(1);
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }

    }
}
