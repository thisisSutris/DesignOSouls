package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.List;

public abstract class RangedWeapon extends WeaponItem {

    protected Location location;

    protected int range;

    protected Actor wielder;

    private ArrayList<Location> targetLocations = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */

    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate, int range) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
        portable = false;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {

        wielder = actor;

        int maximumX = (range * 2) + 1;
        int maximumY = (range * 2) + 1;

        int startX = currentLocation.x() - range;
        int startY = currentLocation.y() - range;

        final int MAX_X = currentLocation.map().getXRange().max();
        final int MAX_Y = currentLocation.map().getYRange().max();
        final int MIN_X = currentLocation.map().getXRange().min();
        final int MIN_Y = currentLocation.map().getYRange().min();

        if (currentLocation.x() + maximumX > MAX_X) {
            maximumX = MAX_X;
        }
        if (startX  < 0) {
            startX = MIN_X;
        }
        if (currentLocation.y() + maximumY > MAX_Y) {
            maximumY = MAX_Y;
        }
        if (startY  < 0) {
            startY = MIN_Y;
        }

        NumberRange xs = new NumberRange(startX, maximumX);
        NumberRange ys = new NumberRange(startY, maximumY);

        targetLocations.clear();


        for (int x = startX; x <= xs.max() && x >= 0 && x <= MAX_X; x++){
            for (int y = startY; y <= ys.max() && y >= 0 && y <= MAX_Y; y++){
                if (currentLocation.map().at(x, y).containsAnActor() && currentLocation.map().getActorAt(currentLocation.map().at(x, y)).hasCapability(Status.HOSTILE) &&
                        !(currentLocation.map().getActorAt(currentLocation.map().at(x, y)).equals(wielder))) {
                    targetLocations.add(currentLocation.map().at(x, y));
                }
            }
        }
        super.tick(currentLocation, actor);
    }

    public int getRange() {
        return range;
    }

    public ArrayList<Location> getTargetLocations() {
        return targetLocations;
    }


    public Actor getWielder() {
        return wielder;
    }
}
