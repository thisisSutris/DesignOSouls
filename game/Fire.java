package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.util.List;

public class Fire extends Ground{

    protected int counter = 3;

    public Fire() {
        super('V');
    }

    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().getDisplayChar() != 'Y'){
            location.getActor().hurt(25);
        }
        counter -= 1;
        if (counter == 0) {
           location.setGround(new Dirt());
        }
    }
}
