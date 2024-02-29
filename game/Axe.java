package game;

/**
 * Class representing Axe
 */
public abstract class Axe extends MeleeWeapon {
    /**
     * Price of the axe
     */
    private int price;

    /**
     * Creates new Axe
     * @param name = name of the axe
     * @param displayChar = display character for the axe
     * @param damage = damage for axes
     * @param verb = verb for axe
     * @param hitRate = hit rate for the axe
     * @param newPrice = price for the axe
     */
    public Axe(String name, char displayChar, int damage, String verb, int hitRate, int newPrice) {
        super(name, displayChar, damage, verb, hitRate);
        price = newPrice;
    }



    /**
     * Retrieves price
     * @return price
     */
    public int getPrice(){
        return price;
    }
}
