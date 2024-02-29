package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.enums.Abilities;
import game.enums.Status;

public class ChargeAction extends Action{

    protected int charge;
    protected Player player;

    public ChargeAction(Player target, int charge) {
        this.player = target;
        this.charge = charge;
    }

    public String execute(Actor actor, GameMap map) {
        player.increaseCharge();
        player.removeCapability(Status.HOSTILE_TO_ENEMY);
        if (charge == 2) {
            actor.addCapability(Status.HOSTILE_TO_ENEMY);
            actor.addCapability(Status.CHARGED);
            player.resetCharge();
            return "Storm Ruler is charged";
        }
        return actor + "is charging storm ruler";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "charges Storm Ruler";
    }
}
