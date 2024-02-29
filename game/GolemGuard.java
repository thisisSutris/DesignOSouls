package game;


import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import game.interfaces.Resettable;

import java.util.ArrayList;

/**
 * An undead minion.
 */
public class GolemGuard extends Actor implements Resettable, Soul {
    // Will need to change this to a collection if Undead gets additional Behaviours.
    /**
     * List of behaviours for undead
     */
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    /**
     * Value of Undead
     */
    private final int SOUL_DROP_VALUE = 200;

    /**
     * Constructor.
     * All Undeads are represented by an 'u' and have 30 hit points.
     * @param name the name of this Undead
     */
    public GolemGuard(String name, Actor Player) {
        super(name, 'G', 200);
        maxHitPoints = 200;
        behaviours.add(new FollowBehaviour(Player));
        behaviours.add(new PatrolBehaviour());
        addCapability(Status.HOSTILE);
    }

    /**return super.execute(actor, map);
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(otherActor.hasCapability(Status.RANGED))) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        this.hitPoints = maxHitPoints;
        this.behaviours.set(0, new FollowBehaviour(null));

    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(SOUL_DROP_VALUE);
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
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80,"slams");
    }

    @Override
    public String toString() {
        return name + "(" + getHitPoints() + '/' + getMaxHitPoints() + ")(No Weapon)";
    }
}
