package game.interfaces;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import game.BuyMythicItem;
import game.BuyWeapon;
import game.MythicItem;

/**
 * Interface for vendor actions
 */

public interface Purchasable {


    /**
     * Method to sell instances of items
     *
     * @param actor actor merchant is interacting with
     * @param item item to be sold
     * @param price price of the item
     * @return new BuyWeapon action
     */
    default Action BuyItem(Actor actor, Item item, int price){
        return new BuyWeapon(actor,item,price);
    }

    /**
     *Overloaded method to sell instances of MythicItems
     *
     * @param actor actor merchant is interacting with
     * @param item item to be sold
     * @param price price of the item
     * @return new BuyMythicItem action
     */
    default Action BuyItem(Actor actor, MythicItem item, int price){
        return new BuyMythicItem(actor,item,price);
    }


}
