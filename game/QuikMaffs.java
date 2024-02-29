package game;

import edu.monash.fit2099.engine.Location;

public class QuikMaffs {

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    public double manhattanDistance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    public double euclideanDistance(Location a, Location b) {
        return Math.sqrt(Math.pow(a.x() - b.x(),2) + Math.pow(a.y() - b.y(),2));
    }
}
