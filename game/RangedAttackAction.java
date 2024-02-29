package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

import java.util.Random;

public class RangedAttackAction extends AttackAction {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    protected int range;

    protected RangedWeapon weapon;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    protected Display display = new Display();

    /**
     * Creates a new ranged attack action
     * @param target target to be attacked
     * @param rangedWeapon instance of the rangedweapon
     */

    public RangedAttackAction(Actor target, RangedWeapon rangedWeapon){
        super(target);
        this.target = target;
        this.weapon = rangedWeapon;
        this.range = rangedWeapon.getRange();
    }

    /**
     * Targets all actors within the range
     * @param actor holder
     * @param map current map
     * @return string depicting attack to enemy
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if(map.at(x, y).getGround().blocksThrownObjects() && !(weapon.hasCapability(Abilities.PIERCING)))
                        return "Attack to " + target.toString() + " is blocked";
                }
            }
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " Attack " + target + " from a distance.";
    }
}
