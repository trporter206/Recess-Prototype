package test;

import models.Club;
import models.MeetUp;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        player.requestJoinClub(club);
        assertEquals(1, club.getJoinRequests().size());
        assertEquals(1, player.getPendingRequests().size());
    }

    @Test
    void testLeaveClub() {
        player.requestJoinClub(club);
        club.addMember(player, true);
        assertEquals(1, player.getCurrentClubs().size());
        assertEquals(1, club.getMembers().size());
        player.leaveClub(club);
        assertEquals(0, player.getCurrentClubs().size());
        assertEquals(0, club.getMembers().size());
    }

    @Test
    void testRemoveRequest() {
        player.requestJoinClub(club);
        assertEquals(1, player.getPendingRequests().size());
        player.removeRequest(club);
        assertEquals(0, player.getPendingRequests().size());
    }

    @Test
    void testJoinClub() {
        assertEquals(0, player.getCurrentClubs().size());
        player.joinClub(club);
        assertEquals(1, player.getCurrentClubs().size());
    }
}
