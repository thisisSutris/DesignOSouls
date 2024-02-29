package game;

import edu.monash.fit2099.engine.*;

public class ValleyAction extends AttackAction {


    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public ValleyAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
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
            //TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
            map.removeActor(target);
            // move player
            // trying to get figure out how to check for player? as target.
            //	if(target. = )
            //	map.moveActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }

        return result;
    }
}
