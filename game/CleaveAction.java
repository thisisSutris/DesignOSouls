package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Special Action for attacking other Actors.
 */
public class CleaveAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

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
     * @param target the Actor to attack
     */
    public CleaveAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();
        String result = "";

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()){
                target = destination.getActor();
                int damage = weapon.damage()/2;
                result += System.lineSeparator() + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
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

                    if (target.getDisplayChar() == 'S'){
                        Skeleton skeleton = (Skeleton) target;
                        if (skeleton.getNumberOfDeaths() == 0) {
                            if (rand.nextInt(2) == 1) {
                                map.removeActor(skeleton);
                                result += System.lineSeparator() + skeleton + " is killed.";
                            } else {
                                skeleton.heal(100);
                                skeleton.increaseDeaths();
                                result += System.lineSeparator() + skeleton + " is killed and resurrects.";
                            }
                        }
                        else{
                            map.removeActor(skeleton);
                            result += System.lineSeparator() + skeleton + " is killed.";
                        }

                    }
                    else {
                        map.removeActor(target);
                        result += System.lineSeparator() + target + " is killed.";
                    }

                }
                if (actor.getDisplayChar() == '@' && target != destination.getActor()) {
                    target.asSoul().transferSouls(actor.asSoul());
                }
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " Spins to win";
    }

}