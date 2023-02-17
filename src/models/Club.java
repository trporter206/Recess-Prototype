package models;

import exceptions.ClubNotRequestedException;
import exceptions.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Club {

    private String name;
    private Player creator;
    private String about;
    private String preReqs;
    private boolean needPreReqs;
    private boolean privateClub;
    private int numMembers;
    private List<Player> joinRequests;
    private List<Player> members;
    private List<MeetUp> scheduledMeetUpd;
    private int totalClubGames;
    private MeetUp.MeetUpType type;

    public Club(String name, MeetUp.MeetUpType type, Player creator, boolean privateClub) {
        this.name = name;
        this.type = type;
        this.creator = creator;
        this.numMembers = 1;
        this.totalClubGames = 0;
        this.joinRequests = new ArrayList<>();
        this.members = new ArrayList<>();
        this.members.add(creator);
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

    public MeetUp.MeetUpType getType() {
        return type;
    }

    //SETTERS=================================
    public void setName(String name) {
        this.name = name;
    }

    public void setType(MeetUp.MeetUpType type) {
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

    //MODIFIES: this, player
    //EFFECTS: if accepted, add given player to members. Remove from joinRequests
    public boolean addMember(Player player, boolean accepted) throws PlayerNotFoundException, ClubNotRequestedException {
        if (!this.joinRequests.contains(player)) {
            throw new PlayerNotFoundException(player);
        }
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

    //MODIFIES: this, player
    //EFFECTS: removes player from members list
    public void removePlayer(Player player) throws PlayerNotFoundException {
        if (!this.members.contains(player)) {
            throw new PlayerNotFoundException(player);
        }
        this.members.remove(player);
    }

    //MODIFIES: this
    //EFFECTS: add player to requests
    public void newRequest(Player player) {
        this.joinRequests.add(player);
    }
}
