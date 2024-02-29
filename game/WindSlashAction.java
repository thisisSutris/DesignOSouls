package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class WindSlashAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;


    public WindSlashAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Status.CHARGED);
        Weapon weapon = actor.getWeapon();
        target.addCapability(Status.STUNNED);
        int damage = weapon.damage()*2;
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
            target.asSoul().transferSouls(actor.asSoul());
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Slash Yhorm the Giant with the power of the wind";
    }
}
