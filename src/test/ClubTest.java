package test;

import exceptions.ClubAlreadyRequestedException;
import exceptions.ClubNotRequestedException;
import exceptions.PlayerNotFoundException;
import models.Club;
import models.MeetUp;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ClubTest {
    Club club;
    Player player;
    Player player2;

    @BeforeEach
    void setUp() {
        player = new Player("Torri Porter", 27, "Vancouver");
        player2 = new Player("Alison Parker", 25, "Seattle");
        club = new Club("Sports Club", MeetUp.MeetUpType.ALL, player, false);
    }

    @Test
    void testAddMemberTrue() {
        try {
            player2.requestJoinClub(club);
        } catch (ClubAlreadyRequestedException e) {
            fail("ClubAlreadyRequestedException should not have been called");
        }
        assertEquals(1, club.getJoinRequests().size());
        try {
            club.addMember(player2, true);
        } catch(PlayerNotFoundException | ClubNotRequestedException e) {
            fail("Exception should not have been called");
        }
        assertEquals(2, club.getMembers().size());
    }

    @Test
    void testAddMemberFalse() {
        try {
            player2.requestJoinClub(club);
        } catch (ClubAlreadyRequestedException e) {
            fail("ClubAlreadyRequestedException should not have been called");
        }
        assertEquals(1, club.getJoinRequests().size());
        try {
            club.addMember(player2, false);
        } catch (PlayerNotFoundException | ClubNotRequestedException e) {
            fail("Exception should not have been called");
        }
        assertEquals(1, club.getMembers().size());
    }

    @Test
    void testRemovePlayer() {
        try {
            player.requestJoinClub(club);
            club.addMember(player, true);
        } catch (PlayerNotFoundException | ClubNotRequestedException e) {
            fail("Exception should not have been called");
        } catch (ClubAlreadyRequestedException e) {
            fail("ClubAlreadyRequestedException should not have been called");
        }
        assertEquals(2, club.getMembers().size());
        try {
            club.removePlayer(player);
        } catch(PlayerNotFoundException e) {
            fail("PlayerNotFoundException should not have been called");
        }
        assertEquals(1, club.getMembers().size());
    }

    @Test
    void testNewRequest() {
        club.newRequest(player);
        assertEquals(1, club.getJoinRequests().size());
    }

}
