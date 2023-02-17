package models;

import exceptions.ClubAlreadyRequestedException;
import exceptions.ClubNotRequestedException;
import exceptions.PlayerNotFoundException;
import exceptions.PlayerNotInClubException;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int age;
    private String city;
    private String about;
    private int gamesPlayed;
    private List<Club> currentClubs;
    private List<Club> pendingRequests;
    private int numHostedGames;

    public Player(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.about = "";
        this.currentClubs = new ArrayList<>();
        this.pendingRequests = new ArrayList<>();
        this.gamesPlayed = 0;
        this.numHostedGames = 0;
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

    public int getNumHostedGames() {
        return numHostedGames;
    }

    public String getAbout() {
        return about;
    }

    public String getCity() {
        return city;
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

    public void setAbout(String about) {
        this.about = about;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumHostedGames(int numHostedGames) {
        this.numHostedGames = numHostedGames;
    }

    //METHODS=====================================

    //MODIFIES: this, club
    //EFFECTS: adds player request to join given club
    public void requestJoinClub(Club club) throws ClubAlreadyRequestedException {
        if (this.pendingRequests.contains(club)) {
            throw new ClubAlreadyRequestedException(club);
        }
        club.newRequest(this);
        this.pendingRequests.add(club);
    }

    //MODIFIES: this, club
    //EFFECTS: removes player from given club
    public void leaveClub(Club club) throws PlayerNotInClubException, PlayerNotFoundException {
        if (!this.currentClubs.contains(club)) {
            throw new PlayerNotInClubException(club);
        }
        this.currentClubs.remove(club);
        club.removePlayer(this);
    }

    //MODIFIES: this
    //EFFECTS: increment number of games played
    public void addToGamesPlayed() {
        this.gamesPlayed++;
    }

    //MODIFIES: this
    //EFFECTS: when request is accepted/declined, remove club from requests
    public void removeRequest(Club club) throws ClubNotRequestedException {
        if (!this.pendingRequests.contains(club)) {
            throw new ClubNotRequestedException(club);
        }
        this.pendingRequests.remove(club);
    }

    //MODIFIES: this
    //EFFECTS: if request is accepted, club added to clubs list
    public void joinClub(Club club) {
        this.currentClubs.add(club);
    }

}
