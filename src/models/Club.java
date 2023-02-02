package models;

import java.util.ArrayList;
import java.util.List;

public class Club {

    private String name;
    private int numMembers;
    private List<Player> joinRequests;
    private List<Player> members;
    private int totalClubGames;
    private Game.GameType type;

    public Club(String name, Game.GameType type) {
        this.name = name;
        this.type = type;
        this.numMembers = 0;
        this.totalClubGames = 0;
        this.joinRequests = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    //GETTERS====================================
    public String getName() {
        return name;
    }

    public int getNumMembers() {
        return numMembers;
    }

    public List<Player> getJoinRequests() {
        return joinRequests;
    }

    public List<Player> getMembers() {
        return members;
    }

    public int getTotalClubGames() {
        return totalClubGames;
    }

    public Game.GameType getType() {
        return type;
    }

    //SETTERS=================================
    public void setName(String name) {
        this.name = name;
    }

    public void setType(Game.GameType type) {
        this.type = type;
    }

    public void setJoinRequests(List<Player> joinRequests) {
        this.joinRequests = joinRequests;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }

    public void setNumMembers(int numMembers) {
        this.numMembers = numMembers;
    }

    public void setTotalClubGames(int totalClubGames) {
        this.totalClubGames = totalClubGames;
    }

    //METHODS=====================================

    //REQUIRES: player is in joinRequests
    //MODIFIES: this, player
    //EFFECTS: if accepted, add given player to members. Remove from joinRequests
    public boolean addMember(Player player, boolean accepted) {
        if (accepted) {
            this.members.add(player);
            this.joinRequests.remove(player);
            player.removeRequest(this);
            player.joinClub(this);
            return true;
        } else {
            this.joinRequests.remove(player);
            player.removeRequest(this);
            return false;
        }
    }

    //REQUIRES: player is member
    //MODIFIES: this, player
    //EFFECTS: removes player from members list
    public void removePlayer(Player player) {
        this.members.remove(player);
    }

    //MODIFIES: this
    //EFFECTS: add player to requests
    public void newRequest(Player player) {
        this.joinRequests.add(player);
    }
}
