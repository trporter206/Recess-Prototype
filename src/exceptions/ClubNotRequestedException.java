package exceptions;

import models.Club;

public class ClubNotRequestedException extends Exception {
    private Club club;

    public ClubNotRequestedException(Club club) {
        super(club.getName() + "is not in requests");
    }
}
