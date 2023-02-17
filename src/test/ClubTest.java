package test;

import models.Club;
import models.MeetUp;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        player2.requestJoinClub(club);
        assertEquals(1, club.getJoinRequests().size());
        club.addMember(player2, true);
        assertEquals(2, club.getMembers().size());
    }

    @Test
    void testAddMemberFalse() {
        player2.requestJoinClub(club);
        assertEquals(1, club.getJoinRequests().size());
        club.addMember(player2, false);
        assertEquals(1, club.getMembers().size());
    }

    @Test
    void testRemovePlayer() {
        player.requestJoinClub(club);
        club.addMember(player, true);
        assertEquals(2, club.getMembers().size());
        club.removePlayer(player);
        assertEquals(1, club.getMembers().size());
    }

    @Test
    void testNewRequest() {
        club.newRequest(player);
        assertEquals(1, club.getJoinRequests().size());
    }

}
