package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class TeleportBonfireAction extends Action {

    public final Bonfire target;
    public final String name;

    public TeleportBonfireAction(Bonfire target, String name) {
        this.target = target;
        this.name = name;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocation = map.at(target.getX(), target.getY());
        map.moveActor(actor, targetLocation);
        for (Bonfire bonfire : Player.getBonfires()){
            bonfire.removeCapability(Status.RESPAWNPOINT);
        }
        target.addCapability(Status.RESPAWNPOINT);
        return "Unkindled moved to " + name;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled moves to " + name;
    }
}
