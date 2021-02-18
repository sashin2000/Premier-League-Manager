package models;

import java.io.Serializable;

// This is class SportsClub
// Last modified date - 25/12/2020

public abstract class SportsClub implements Serializable{
    public static final long serialVersionUID = 5210124774668903778L;

    private String clubName;
    private String clubLocation;
    private String clubManager;

    public SportsClub(String clubName, String clubLocation, String clubManager) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubManager = clubManager;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    public String getClubManager() {
        return clubManager;
    }

    public void setClubManager(String clubManager) {
        this.clubManager = clubManager;
    }

    @Override
    public String toString() {
        return "SportsClub{" + "clubName= " + clubName + ", clubLocation= " + clubLocation + "}";
    }
}
