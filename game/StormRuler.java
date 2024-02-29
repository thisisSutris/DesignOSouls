package game;


import edu.monash.fit2099.engine.*;

/**
 * Class representing Storm Ruler
 */
public class StormRuler extends Sword{

    /**
     * Creates Stormruler
     */
    public StormRuler() {
        super("Storm Ruler", '7', 70, "slashed", 60, 500);
        portable = true;
    }

    public SwapWeaponAction getPickUpAction(Actor actor) {
        if(portable)
            return new SwapWeaponAction(this);

        return null;
    }
}
