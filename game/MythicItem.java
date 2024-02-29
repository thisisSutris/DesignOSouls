package game;

import edu.monash.fit2099.engine.Item;
import game.enums.MythicItemType;
import game.interfaces.MythicItemInterface;

/**
 * Class representing MythicItem
 */

public abstract class MythicItem extends Item implements MythicItemInterface {

    private String abilityDescription;
    private MythicItemType mythicItemType;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MythicItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Description of the item
     * @param mythicItemDescription description of the mythic item
     */
    @Override
    public void setDescription(String mythicItemDescription) {
        this.abilityDescription = mythicItemDescription;
    }

    /**
     * Retrieves description of the item
     * @return description of the item
     */
    public String getDescription(){
        return abilityDescription;
    }

    /**
     * Sets the type of the item
     * @param mythicItemType mythic item type enum
     */
    @Override
    public void setType(MythicItemType mythicItemType) {
        this.mythicItemType = mythicItemType;
    }

    /**
     * Retrieves the type of the item
     * @return mythic item type
     */
    public MythicItemType getType() {
        return mythicItemType;
    }

    @Override
    public String toString() {
        return name + " " + "{"+ getType() + "}" + " <Ability: " + getDescription() + ">" ;
    }
}
