package exceptions;

import models.Club;

public class ClubAlreadyRequestedException extends Exception {
    private Club club;

    public ClubAlreadyRequestedException(Club club) {
        super(club.getName() + " not found");
    }
}
