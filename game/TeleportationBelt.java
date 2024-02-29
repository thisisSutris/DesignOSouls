package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import game.enums.Abilities;
import game.enums.MythicItemType;

import java.util.ArrayList;

/**
 * Class that represents a teleportation belt
 */

public class TeleportationBelt extends MythicItem {


    /**
     * Maximum Range of teleportation
     */
    protected int range;

    /**
     * Actor holding the item
     */
    protected Actor holder;

    /**
     * Arraylist of possible locations to teleport in in the given range.
     */
    protected ArrayList<Location> possibleLocations = new ArrayList<>();


    /**
     * Constructor to create a new instance of teleportation belt
     * @param range range of possible teleportation
     */
    public TeleportationBelt(int range) {
        super("Teleportation Belt", 't', true);
        setType(MythicItemType.UTILITY);
        this.addCapability(Abilities.TELEPORT);
        this.range = range;
        setDescription("Allows the holder to teleport in a set radius based on range");
    }


    /**
     * Method to indicate possible locations in a rangeX2+1 x rangeX2+1 area around the player and adds it to the possible locations arraylist
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {

        holder = actor;

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


        possibleLocations.clear();


        for (int x = startX; x <= xs.max() && x >= 0 && x <= MAX_X; x++){
            for (int y = startY; y <= ys.max() && y >= 0 && y <= MAX_Y; y++){
                if (currentLocation.map().at(x,y).getGround().canActorEnter(holder)
                        && !(currentLocation.map().at(x,y).getGround().hasCapability(Abilities.NOTTELEPORTABLE))
                        && !(currentLocation.map().at(x,y).containsAnActor())) {
                    possibleLocations.add(currentLocation.map().at(x, y));
                }
            }
        }
        super.tick(currentLocation, actor);
    }

    /**
     * Returns arraylist of locations
     * @return locationarraylist
     */
    public ArrayList<Location> getPossibleLocations() {
        return possibleLocations;
    }

}
