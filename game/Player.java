package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.MythicItemType;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {
    /**
     * Estus flask
     */
    private EstusFlask estusFlask = new EstusFlask();
    /**
     * Broadsword
     */
    private Broadsword broadsword = new Broadsword();
    /**
     * Souls owned by the player
     */
    private int soulAmount;

    public static List<Bonfire> bonfires = new ArrayList<>();

    Location startingLocation;

    private final Menu menu = new Menu();

    public int charge = 0;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Abilities.TRADE);
        this.addCapability(Abilities.REST);
        this.addCapability(Abilities.FALL);
        this.addCapability(Status.FRIENDLY);
        inventory.add(estusFlask);
        inventory.add(new Glock());
        soulAmount = 10000;
        ActorLocations actorsStartingLocation = new ActorLocations();
        startingLocation = actorsStartingLocation.locationOf(this);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        isplayerDied();
        display.println("Current Turn");
        display.println("Unkindled Status: \nHealth: (" + hitPoints + "/" + maxHitPoints + ")" +
                "\nEstus Flask: (" + estusFlask.getNumberOfCharges() + "/" + estusFlask.getMaxNumberOfCharges() + ")" +
                "\nInventory: " + getInventory() +
                "\nSouls: " + getSoulAmount());
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }
        if (!(getWeapon() instanceof RangedWeapon)) {
            this.removeCapability(Status.RANGED);
        }
        if (getWeapon() instanceof RangedWeapon) {
            this.addCapability(Status.RANGED);
            for (Location location : ((RangedWeapon) getWeapon()).getTargetLocations()) {
                actions.add(new RangedAttackAction(location.getActor(), (RangedWeapon) getWeapon()));
            }
        }

        TeleportationBelt placeholder;
        for (Item item : getInventory()) {
            if (item.hasCapability(Abilities.TELEPORT)) {
                placeholder = (TeleportationBelt) item;
                actions.add(new TeleportationBeltAction(placeholder));
            }
        }

        // return/print the console menu
        actions.add(new DrinkAction());
        actions.add(new Ouch());

        if (getWeapon() instanceof GiantAxe) {
            actions.add(new CleaveAction(this));
        }
        if (getWeapon() instanceof StormRuler && !hasCapability(Status.CHARGED)) {
            actions.add(new ChargeAction(this, charge));
        }
        return menu.showMenu(this, actions, display);
    }

    @Override
    public void resetInstance() {
        this.hitPoints = getMaxHp();
        ActorLocations ActorLocations = new ActorLocations();
        // trying to move actor to bonfire
        ActorLocations.move(this, startingLocation);
    }

    @Override
    public boolean isExist() {
        return true;
    }

    private static class Ouch extends Action {
        @Override
        public String execute(Actor actor, GameMap map) {
            actor.hurt(50);
            return actor + " got a heart attack. Health Reduced by 50";
        }

        @Override
        public String menuDescription(Actor actor) {
            return "Random heart attack";
        }
    }

    private class DrinkAction extends Action {
        @Override
        public String execute(Actor actor, GameMap map) {
            String output = "Healing failed. Out of Estus Flasks";
            final int initialHealth = getHealth();
            if (estusFlask.getNumberOfCharges() > 0) {
                int healing = (int) (getMaxHp() * 0.4);
                actor.heal(healing);
                int increase = getHealth() - initialHealth;
                output = actor + " healed for " + increase + " points.";
                estusFlask.drink();
            }
            return output;
        }

        @Override
        public String menuDescription(Actor actor) {
            return "Get on the Estus flask (" + estusFlask.getNumberOfCharges() + "/" + estusFlask.getMaxNumberOfCharges() + ")";
        }
    }

    public int getHealth() {
        return hitPoints;
    }

    public int getMaxHp() {
        return maxHitPoints;
    }

    public void isplayerDied() {
        if (!isConscious()) {
            System.out.println("YOU DIED");
            resetInstance();
        }
    }

    public EstusFlask getEstusFlask() {
        return estusFlask;
    }

    public int getSoulAmount() {
        return soulAmount;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulAmount);
        soulAmount = 0;
    }

    @Override
    public boolean addSouls(int souls) {
        soulAmount += souls;
        return true;
    }

    @Override
    public boolean subtractSouls(int souls) {
        boolean output = false;
        int copy = soulAmount;
        copy -= souls;
        if (copy >= 0) {
            output = true;
            soulAmount -= souls;
        }
        return output;
    }

    public void increaseCharge() {
        charge += 1;
    }

    public void resetCharge() {
        charge = 0;
    }

    public static List<Bonfire> getBonfires() {
        return Collections.unmodifiableList(bonfires);
    }

    public static void addBonfire(Bonfire bonfire) {
        bonfires.add(bonfire);
    }

}
