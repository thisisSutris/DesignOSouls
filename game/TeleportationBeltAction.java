package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

import java.util.Random;

/**
 * Class that represents the teleportation belt action
 */

public class TeleportationBeltAction extends Action {

    Random random = new Random();

    private MythicItem item;


    /**
     * creates a new action for the teleportation belt
     * @param item an instance of teleportation belt
     */
    public TeleportationBeltAction(TeleportationBelt item) {
        this.item = item;
    }

    /**
     * Moves the actor to a random location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string depicting the succesful teleportation of the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (item.hasCapability(Abilities.TELEPORT)) {
            TeleportationBelt belt = (TeleportationBelt) item;
            Location randomLocation = belt.getPossibleLocations().get(random.nextInt(belt.getPossibleLocations().size()));
            map.moveActor(actor, randomLocation);
        }
        return "Teleported to a random location.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Teleport randomly";
    }
}
