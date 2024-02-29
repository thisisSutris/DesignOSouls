package game;

import game.enums.Abilities;

public abstract class PiercingWeapons extends RangedWeapon{

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param range       range of the weapon
     */
    public PiercingWeapons(String name, char displayChar, int damage, String verb, int hitRate, int range) {
        super(name, displayChar, damage, verb, hitRate, range);
        this.addCapability(Abilities.PIERCING);
    }
}
