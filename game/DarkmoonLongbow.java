package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class DarkmoonLongbow extends Bow{

    Random rand = new Random();
    Display display = new Display();

    /**
     * Creates a new instance of darkmoon longbow
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", '}', 70, "shoots", 80, 3);
    }

    /**
     * Passive for DarkmoonLongbow (Critical Strike)
     * @return damage
     */
    @Override
    public int damage(){
        int output = damage;
        if (rand.nextInt(100) < 15){
            output = damage*2;
            display.println("CRITICAL HIT");
        }
        return output;
    }
}
