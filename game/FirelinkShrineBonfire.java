package game;

import game.enums.Status;
import static game.Player.addBonfire;

public class FirelinkShrineBonfire extends Bonfire{

    public FirelinkShrineBonfire() {
        super("Firelink Shrine Bonfire", 38, 11);
        this.addCapability(Status.RESPAWNPOINT);
    }
}
