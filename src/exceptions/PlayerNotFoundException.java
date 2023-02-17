package exceptions;

import models.Player;

public class PlayerNotFoundException extends Exception {
    private Player player;

    public PlayerNotFoundException(Player player) {
        super(player.getName() + "not found");
    }
}
