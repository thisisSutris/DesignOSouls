package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Capabilities;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
		this.addCapability(Abilities.BURNABLE);
	}


}
