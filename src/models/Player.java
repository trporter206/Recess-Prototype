package models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int age;
    private int gamesPlayed;
    private List<Club> currentClubs;
    private List<Club> pendingRequests;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.gamesPlayed = 0;
        this.currentClubs = new ArrayList<>();
        this.pendingRequests = new ArrayList<>();
    }

    //GETTERS=====================================
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public List<Club> getCurrentClubs() {
        return currentClubs;
    }

    public List<Club> getPendingRequests() {
        return pendingRequests;
    }

    //SETTERS=====================================
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentClubs(List<Club> currentClubs) {
        this.currentClubs = currentClubs;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setPendingRequests(List<Club> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    //METHODS=====================================

    //REQUIRES: club is not already in requests
    //MODIFIES: this, club
    //EFFECTS: adds player request to join given club
    public void requestJoinClub(Club club) {
        club.newRequest(this);
        this.pendingRequests.add(club);
    }

    //REQUIRES: player is member of given club
    //MODIFIES: this, club
    //EFFECTS: removes player from given club
    public void leaveClub(Club club) {
        this.currentClubs.remove(club);
        club.removePlayer(this);
    }

    //MODIFIES: this
    //EFFECTS: increment number of games played
    public void addToGamesPlayed() {
        this.gamesPlayed++;
    }

    //REQUIRES: club in joinRequests
    //MODIFIES: this
    //EFFECTS: when request is accepted/declined, remove club from requests
    public void removeRequest(Club club) {
        this.pendingRequests.remove(club);
    }

    //MODIFIES: this
    //EFFECTS: if request is accepted, club added to clubs list
    public void joinClub(Club club) {
        this.currentClubs.add(club);
    }

}
