package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A class that represents the bonfire.
 */
public abstract class Bonfire extends Ground {

    public final String name;

    public int x;
    public int y;

    /**
     * Creates bonfire
     */
    public Bonfire(String name, int x, int y) {
        super('B');
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Allows the actor acting to rest if they have the rest ability
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if(actor.hasCapability(Abilities.REST) && this.hasCapability(Status.ACTIVE)) {
            actions.add(new RestAction(actor, name, this));
            for (Bonfire bonfire : Player.getBonfires()) {
                if(bonfire != this && bonfire.hasCapability(Status.ACTIVE)) {
                    actions.add(new TeleportBonfireAction(bonfire, bonfire.name));
                }

            }
        }
        if(actor.hasCapability(Abilities.REST) && !(this.hasCapability(Status.ACTIVE))) {
            actions.add(new ActivateBonfireAction(this));
        }
        return actions;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}