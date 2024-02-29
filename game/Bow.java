package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Capabilities;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.enums.Abilities;
import game.interfaces.Behaviour;

import java.util.ArrayList;

/**
 * Abstract class for Bow rangedweapon type
 */
public abstract class Bow extends RangedWeapon{
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Bow(String name, char displayChar, int damage, String verb, int hitRate, int range) {
        super(name, displayChar, damage, verb, hitRate, range);
    }
}
