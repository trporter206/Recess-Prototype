package test;

import models.Club;
import models.Game;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClubTest {
    Club club;
    Player player;

    @BeforeEach
    void setUp() {
        club = new Club("Sports Club", Game.GameType.ALL);
        player = new Player("Torri Porter", 27);
    }

    @Test
    void testAddMemberTrue() {
        player.requestJoinClub(club);
        assertEquals(1, club.getJoinRequests().size());
        club.addMember(player, true);
        assertEquals(1, club.getMembers().size());
    }

    @Test
    void testAddMemberFalse() {
        player.requestJoinClub(club);
        assertEquals(1, club.getJoinRequests().size());
        club.addMember(player, false);
        assertEquals(0, club.getMembers().size());
    }

    @Test
    void testRemovePlayer() {
        player.requestJoinClub(club);
        club.addMember(player, true);
        assertEquals(1, club.getMembers().size());
        club.removePlayer(player);
        assertEquals(0 , club.getMembers().size());
    }

    @Test
    void testNewRequest() {
        club.newRequest(player);
        assertEquals(1, club.getJoinRequests().size());
    }

}
