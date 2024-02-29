package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.Skeleton;
import game.enums.Abilities;

public class BurnAction extends Action {

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    protected Display display = new Display();
    /**
     * Constructor.
     *
     */
    public BurnAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Abilities.BURNABLE)){
                destination.setGround(new Fire());
                }
            }
        return "Yhorm burns the ground around him";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " Spins to win";
    }

}