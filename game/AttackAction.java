package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	public AttackAction(Actor target){
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor

			if (target.hasCapability(Abilities.REVIVABLE)){
				Skeleton skeleton = (Skeleton) target;
				if (skeleton.getNumberOfDeaths() == 0) {
					if (rand.nextInt(2) == 1) {
						map.removeActor(skeleton);
					} else {
						skeleton.healTillFull();
						skeleton.increaseDeaths();
						result += System.lineSeparator() + skeleton + " is killed and resurrects.";
						return result;
					}
				}
				else{
					map.removeActor(skeleton);
				}

			}
			else {
				map.removeActor(target);
			}
			// move player
			// trying to get figure out how to check for player? as target.
		//	if(target. = )
		//	map.moveActor(target);
			result += System.lineSeparator() + target + " is killed.";
			if (actor.getDisplayChar() == '@') {
				target.asSoul().transferSouls(actor.asSoul());
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target.toString() + " at " + direction;
	}

}
