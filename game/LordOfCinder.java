package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * The boss of Design o' Souls
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */
public abstract class LordOfCinder extends Actor{

    private CindersOfALord lordItem;

    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        maxHitPoints = hitPoints;
        lordItem = new CindersOfALord(name + " soul", '*', true );
        this.addCapability(Status.HOSTILE);
    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)&& !(otherActor.hasCapability(Status.RANGED))) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Retrieves current health
     * @return hitpoints
     */
    public int getHitPoints(){
        return hitPoints;
    }

    /**
     * Retrieves Max Health
     * @returns maxHealth
     */
    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    @Override
    public String toString() {
        return name + "(" + getHitPoints() + '/' + getMaxHitPoints() + ")(" + getWeapon() + ")";
    }

}
