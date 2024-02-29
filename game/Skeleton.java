package game;


import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.Player;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

/**
 * An undead minion.
 */
public class Skeleton extends Actor implements Soul {
    // Will need to change this to a collection if Undead gets additional Behaviours.
    /**
     * All the behaviours the skeleton has
     */
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    /**
     * Random class instantiated
     */
    private Random rand = new Random();
    /**
     * Value of the skeleton
     */
    private final int SOUL_DROP_VALUE = 250;
    /**
     * Number of times skeleton dies.
     */
    public int numberOfDeaths = 0;

    Display display = new Display();

    /**
     * Constructor.
     * All Skeletons are represented by an 'S' and have 100 hit points.
     * @param name the name of this Undead
     */
    public Skeleton(String name, Actor Player) {
        super(name, 'S', 100);
        maxHitPoints = 100;
        this.addCapability(Abilities.REVIVABLE);
        this.addCapability(Status.HOSTILE);
        behaviours.add(new FollowBehaviour(Player));
        behaviours.add(new WanderBehaviour());
        if (rand.nextInt(2) == 1) {
            inventory.add(new Broadsword());
        }
        else {
            inventory.add(new GiantAxe());
        }
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

    /**
     * Figure out what to do next.
     */
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

    /**
     * Transfer souls to player if killed
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(SOUL_DROP_VALUE);
    }

    /**
     * Retrieves number of deaths by skeleton
     * @return number of deaths
     */
    public int getNumberOfDeaths(){
        return numberOfDeaths;
    }

    /**
     * increments death count by 1
     */
    public void increaseDeaths(){
        numberOfDeaths += 1;
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

    public void healTillFull(){
        while (getHitPoints() != getMaxHitPoints()){
            heal(1);
        }
    }
}