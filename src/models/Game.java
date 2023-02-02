package models;

import java.util.List;

public interface Game {

    enum GameType {
        ALL, BASKETBALL, TENNIS
    }

    //GETTERS================================

    //EFFECTS: get number of players
    int getNumPlayers();

    //EFFECTS: returns true or false if players need to bring their own gear
    boolean getBYOGear();

    //EFFECTS: return true if the game is outdoors
    boolean getOutdoorGame();

    //EFFECTS: return max number of players in the game
    int getMaxPlayers();

    //EFFECTS: get game type
    GameType getGameType();

    //EFFECTS: return list of current players
    List<Player> getPlayers();

    //SETTERS===============================

    //EFFECTS: set if the game is outdoors
    boolean setOutdoorGame();

    //EFFECTS: set number of players
    void setNumPlayers();

    //EFFECTS: set if players need to bring their own gear
    void setBYOGear();

    //EFFECTS: set game type
    void setGameType();

    //EFFECTS: set list of current players
    void setPlayers();

    //METHODS============================

    //EFFECTS: print game information
    void gameInfo();

}
