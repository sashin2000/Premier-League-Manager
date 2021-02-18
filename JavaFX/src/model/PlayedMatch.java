package model;

import java.io.Serializable;

public class PlayedMatch implements Serializable {
    private String team1;
    private int score1;
    private String team2;
    private int score2;
    private Date playedDate;

    public PlayedMatch(String team1, int score1, String team2, int score2, Date playedDate){
        this.team1 = team1;
        this.score1 = score1;
        this.team2 = team2;
        this.score2 = score2;
        this.playedDate = playedDate;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public Date getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(Date playedDate) {
        this.playedDate = playedDate;
    }
}
