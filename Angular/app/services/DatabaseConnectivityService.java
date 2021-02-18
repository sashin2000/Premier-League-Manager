package services;

import models.Date;
import models.FootballClub;
import models.PlayedMatch;
import models.PremierLeagueManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* This is class DatabaseConnectivityService
* This class is used for loading and saving data into the file
*
* Last modified date - 01/01/2021
*/

public class DatabaseConnectivityService {

    private static PremierLeagueManager premierLeagueManager;

    public static PremierLeagueManager loadFromDatabase() {

        // Getting the singleton instance of premiere league manager
        premierLeagueManager = PremierLeagueManager.getInstance();

        // Create a file named PremierLeagueManager.txt
        File file = new File("PremierLeagueManager.txt");

        // If file doesn't exist, create new file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        // Deserialize the object and assign club list and the match list to the singleton object
        try {
            FileInputStream fileReader = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileReader);
            PremierLeagueManager premierLeagueManagerDeserialize = (PremierLeagueManager) objIn.readObject();
            List<FootballClub> clubList = premierLeagueManagerDeserialize.getClubList();
            HashMap<Date, ArrayList<PlayedMatch>> matches = premierLeagueManagerDeserialize.getMatches();
            //System.out.println("Loaded");
            premierLeagueManager.setClubList(clubList);
            premierLeagueManager.setMatches(matches);
            objIn.close();

        } catch (
                EOFException e) {
            //System.out.println("Fresh Account");
        } catch (Exception e) {
            System.out.println(e);
        }

        return premierLeagueManager;
    }

    // This method is used to serialize the premiere league manager object
    public static void saveToDatabase(){
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
    }

}
