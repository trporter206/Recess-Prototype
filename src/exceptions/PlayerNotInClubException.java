package exceptions;

import models.Club;

public class PlayerNotInClubException extends Exception {
    private Club club;

    public PlayerNotInClubException(Club club) {
        super("You are not a member of " + club.getName());
    }
}
