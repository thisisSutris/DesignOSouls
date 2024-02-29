package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class representing the estus flask
 */
public class EstusFlask extends Item {
    /**
     * Number of charges for the estus flask
     */
    private int numberOfCharges;

    /**
     * Maximum number of charges for the estus flask
     */
    private int maxNumberOfCharges;


    /**
     * Creates estus flask with 3 charges
     */
    public EstusFlask() {
        super("Estus Flask", ';', false);
        setNumberOfCharges(3);
        maxNumberOfCharges = numberOfCharges;
    }

    /**
     * Sets the number of charges for the estus flask.
     * @param newNumberOfCharges is the amount of the new estus flask charge
     */
    public void setNumberOfCharges(int newNumberOfCharges) {
        numberOfCharges = newNumberOfCharges;
    }

    /**
     * Drinks the estus flask and reduces charges by 1
     */
    public void drink() {
        numberOfCharges -= 1;
    }

    /**
     * Refills estus flask to max charges
     */
    public void refill(){
        numberOfCharges = getMaxNumberOfCharges();
    }

    /**
     * Retrieves the current number of charges
     * @return the current number of charges
     */
    public int getNumberOfCharges(){
        return numberOfCharges;
    }

    /**
     * Retrieves the maximum number of charges
     * @return the max number of charges
     */
    public int getMaxNumberOfCharges(){
        return maxNumberOfCharges;
    }
}
