package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing the action to buy a mythic item
 */

public class BuyMythicItem extends Action {

    /**
     * The one buying the item
     */
    protected Actor target;

    /**
     * Mythic item to be sold
     */
    protected MythicItem mythicItem;

    /**
     * price of the mythic item
     */
    protected int price;

    /**
     * Constructor for the class
     * @param actor the one purchasing the mythic item
     * @param mythicItem mythic item to be sold
     * @param price price of the mythic item
     */
    public BuyMythicItem(Actor actor, MythicItem mythicItem, int price){
        this.target = actor;
        this.mythicItem = mythicItem;
        this.price = price;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "Purchase Failed. Not enough Souls.";
        if (target.asSoul().subtractSouls(price)) {
            target.addItemToInventory(mythicItem);
            output = "Purchase successful. " + mythicItem.toString() + " purchased. " + price + " souls deducted.";
        }
        return output;
    }

    @Override
    public String menuDescription(Actor actor) {
        String souls = "souls";
        if (price == 1){
            souls = "soul";
        }
        return "Buy " + mythicItem.toString() + "[" + price + " " +souls+"]";
    }
}
