package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

public class PatrolBehaviour extends Action implements Behaviour {

    private int counter = 2;
    private int directionCounter = 0;
    Action nothing = new DoNothingAction();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<Action>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
            else {
                actions.add(nothing);
            }
        }

        if (counter == 4) {
            counter = 0;
            directionCounter += 1;
        }
        if (directionCounter%2 == 0){
            if (actions.get(2) != nothing) {
                counter += 1;
            }
            return actions.get(2);
        }
        if (actions.get(6) != nothing) {
            counter += 1;
        }
        return actions.get(6);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Raagrh...";
    }
}
