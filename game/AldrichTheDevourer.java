package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

public class AldrichTheDevourer extends LordOfCinder implements Soul{

    private final int SOUL_DROP_VALUE = 5000;

    final static CindersOfALord ALDRICH_SOUL = new CindersOfALord("Aldrich Soul",'*', true);

    static Item ALDRICH_WEAPON = new DarkmoonLongbow();

    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    Random rand = new Random();

    /**
     * Constructor.
     *
     * @param name name of the boss
     * @param displayChar boss display in console
     * @param hitPoints boss hitpoints
     */
    public AldrichTheDevourer(String name, char displayChar, int hitPoints, Actor player) {
        super(name, displayChar, hitPoints);
        inventory.add(ALDRICH_WEAPON);
        inventory.add(ALDRICH_SOUL);
        behaviours.add(new FollowBehaviour(player));
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for(Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(SOUL_DROP_VALUE);
    }
}
