package test;

import exceptions.ClubAlreadyRequestedException;
import exceptions.ClubNotRequestedException;
import exceptions.PlayerNotFoundException;
import exceptions.PlayerNotInClubException;
import models.Club;
import models.MeetUp;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PlayerTest {
    Player player;
    Club club;

    @BeforeEach
    void setUp() {
        player = new Player("Torri", 27, "Vancouver");
        club = new Club("Sports Club", MeetUp.MeetUpType.ALL, player, false);
    }

    @Test
    void testAddToGamesPlayed() {
        player.addToGamesPlayed();
        assertEquals(1, player.getGamesPlayed());
    }

    @Test
    void testRequestJoinClub() {
        assertEquals(0, club.getJoinRequests().size());
        try {
            player.requestJoinClub(club);
        } catch (ClubAlreadyRequestedException e) {
            fail("ClubAlreadyRequestedException should not have been called");
        }
        assertEquals(1, club.getJoinRequests().size());
        assertEquals(1, player.getPendingRequests().size());
    }

    @Test
    void testLeaveClub() {
        Player player2 = new Player("Alison", 25, "Seattle");
        try {
            player2.requestJoinClub(club);
        } catch (ClubAlreadyRequestedException e) {
            fail("ClubAlreadyRequestedException should not have been called");
        }
        assertEquals(1, club.getJoinRequests().size());
        assertEquals(1, player2.getPendingRequests().size());
        try {
            club.addMember(player2, true);
        } catch (PlayerNotFoundException e) {
            fail("PlayerNotFoundException should not have been called");
        } catch (ClubNotRequestedException e) {
            fail("ClubNotRequestedException should not have been called");
        }
        assertEquals(1, player2.getCurrentClubs().size());
        assertEquals(2, club.getMembers().size());
        try {
            player2.leaveClub(club);
        } catch (PlayerNotInClubException e) {
            fail("PlayerNotInClubException should not have been called");
        } catch (PlayerNotFoundException e) {
            fail("PlayerNotFoundException should not have been called");
        }
        assertEquals(0, player.getCurrentClubs().size());
        assertEquals(1, club.getMembers().size());
    }

    @Test
    void testRemoveRequest() {
        try {
            player.requestJoinClub(club);
        } catch (ClubAlreadyRequestedException e) {
            fail("ClubAlreadyRequestedException should not have been called");
        }
        assertEquals(1, player.getPendingRequests().size());
        try {
            player.removeRequest(club);
        } catch (ClubNotRequestedException e) {
            fail("ClubNotRequestedException should not have been called");
        }
        assertEquals(0, player.getPendingRequests().size());
    }

    @Test
    void testJoinClub() {
        assertEquals(0, player.getCurrentClubs().size());
        player.joinClub(club);
        assertEquals(1, player.getCurrentClubs().size());
    }
}
