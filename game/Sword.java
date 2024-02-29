package game;

import java.util.Random;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.WeaponItem;
import game.interfaces.Soul;

/**
 * A class representing a Sword weapon type
 */
public abstract class Sword extends MeleeWeapon {
    /**
     * Instantiates random class
     */
    Random rand = new Random();
    /**
     * Instantiates display class
     */
    Display display = new Display();
    /**
     * Price for sword
     */
    private int price;

    public Sword(String name, char displayChar, int damage, String verb, int hitRate, int newPrice){
        super(name, displayChar, damage, verb, hitRate);
        price = newPrice;
    }

    /**
     * Passive for swords (Critical Strike)
     * @return damage
     */
    @Override
    public int damage(){
        int output = damage;
        if (rand.nextInt(100) < 20){
            output = damage*2;
            display.println("CRITICAL HIT");
        }
        return output;
    }

    /**
     * Gets the price of the Sword
     * @return the Sword's price
     */
    public int getPrice(){
        return price;
    }
}


