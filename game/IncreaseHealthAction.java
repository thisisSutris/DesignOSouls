package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class to increase health
 */
public class IncreaseHealthAction extends Action {

    /**
     * actor to increase health
     */
    protected Actor target;

    /**
     * Which actor increases health
     * @param target to increase health
     */
    public IncreaseHealthAction(Actor target) {
        this.target = target;
    }

    /**
     * Increases Health if executed
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the status of the purchase
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "Purchase Failed. Not Enough Souls.";
        if (target.asSoul().subtractSouls(200)) {
            target.increaseMaxHp(25);
            output = "Purchase Successful. Health Increased. 200 Souls are deducted.";
        }
        return output;
    }

    /**
     * Menu display for this action
     * @param actor The actor performing the action.
     * @return Menu display in the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Increase Health [200 Souls]";
    }
}
