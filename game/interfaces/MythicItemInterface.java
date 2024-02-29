package game.interfaces;

import game.enums.MythicItemType;

public interface MythicItemInterface {

     void setType(MythicItemType mythicItemType);

     MythicItemType getType();

     void setDescription(String mythicItemDescription);

     String getDescription();

}
