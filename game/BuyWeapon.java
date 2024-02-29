package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class BuyWeapon extends SwapWeaponAction {

    /**
     * Actor buying the weapon
     */
    protected Actor target;

    protected int price;

    protected Item weaponToSell;

    /**
     * Constructor
     *
     * @param weapon the new item that will replace the weapon in the Actor's inventory.
     */
    public BuyWeapon(Actor actor, Item weapon, int price) {
        super(weapon);
        this.target = actor;
        this.price = price;
        this.weaponToSell = weapon;
    }

    /**
     * Conditions for successful purchase:
     * - No similar weapon in inventory
     * - The end result of soul in Player class after subtracted is not lower than zero
     *
     * @param actor actor executing the command
     * @param map   current map
     * @return string displaying whether purchase was successful or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "Purchase Failed. Not enough Souls.";
        if (target.asSoul().subtractSouls(price)) {
            super.execute(actor, map);
            output = "Purchase successful. " + item.toString() + " purchased. " + price + " souls deducted.";
        }
        return output;
    }

    /**
     * @param actor The actor performing the action.
     * @return Display what is trying to be bought by the player
     */
    @Override
    public String menuDescription(Actor actor) {
        String souls = "souls";
        if (price == 1){
            souls = "soul";
        }
        return "Buy " + weaponToSell.toString() + " [" + price + " " + souls + "]";
    }
}
