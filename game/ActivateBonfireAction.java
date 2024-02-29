package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

public class ActivateBonfireAction extends Action {

    protected Bonfire target;

    public ActivateBonfireAction(Bonfire target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        target.addCapability(Status.ACTIVE);
        for (Bonfire bonfire : Player.getBonfires()){
            bonfire.removeCapability(Status.RESPAWNPOINT);
        }
        target.addCapability(Status.RESPAWNPOINT);
        Player.addBonfire(target);
        return "Bonfire lit";
    }

    @Override
    public String menuDescription(Actor target) {
        return "Player lights the Bonfire";
    }
}
