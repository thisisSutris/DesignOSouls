package game;

import game.interfaces.Soul;

/**
 * A class representing a broadsword
 */
public class Broadsword extends Sword{

    /**
     * Creates a new broadsword
     */
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashed", 80, 500);
    }

    /**
     * Retrieves price of the broadsword
     * @return price for broadsword
     */
    @Override
    public int getPrice(){
        return super.getPrice();
    }

    /**
     * Returns the name of the item
     * @return name of the item
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
