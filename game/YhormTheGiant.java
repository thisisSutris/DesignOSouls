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
public class YhormTheGiant extends LordOfCinder implements Soul {

    protected static boolean enrage = false;

    private final int SOUL_DROP_VALUE = 5000;

    final static CindersOfALord YHORM_SOUL = new CindersOfALord("Yhorm Soul",'*', true);

    final static Item YHORM_WEAPON = new YhormsGreatMachete(enrage);

    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor.
     */
    public YhormTheGiant(String name, char displayChar, int hitPoints, Actor player) {
        super(name, displayChar, hitPoints);
        maxHitPoints = hitPoints;
        inventory.add(YHORM_WEAPON);
        inventory.add(YHORM_SOUL);
        behaviours.add(new FollowBehaviour(player));
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
        if (this.hasCapability(Status.STUNNED)){
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }
        if (!enrage && getHitPoints() < getMaxHitPoints()/2){
            enrage = true;
            display.println("Yhorm the Giant become enraged");
            this.inventory.remove(this.getWeapon());
            this.inventory.add(new YhormsGreatMachete(enrage));
            return new BurnAction();
        }
        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(SOUL_DROP_VALUE);
    }

    @Override
    public String toString() {
        return name + "(" + getHitPoints() + '/' + getMaxHitPoints() + ")(" + getWeapon() + ")";
    }

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.CHARGED)) {
            actions.add(new WindSlashAction(this,direction));
        }
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }
}
