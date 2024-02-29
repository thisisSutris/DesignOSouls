package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.interfaces.Purchasable;

/**
 * Class representing vendor
 */
public class Vendor extends Actor implements Purchasable {

    protected Actor otherActor;
    /**
     * Creates Vendor
     */
    public Vendor() {
        super("Fire Keeper", 'F', 100);
    }

    /**
     * gets allowable action when in contact with actor who has the capability to trade
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        this.otherActor = otherActor;
        if (otherActor.hasCapability(Abilities.TRADE)) {
            actions.add(BuyItem(otherActor, new Broadsword(), 500));
            actions.add(BuyItem(otherActor, new GiantAxe(), 1000));
            actions.add(BuyItem(otherActor, new LightSaber(), 10));
            actions.add(BuyItem(otherActor, new TeleportationBelt(5), 500));
            actions.add(new IncreaseHealthAction(otherActor));
        }
        return actions;
    }



    /**
     * Does nothing
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return prints out does nothing
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
