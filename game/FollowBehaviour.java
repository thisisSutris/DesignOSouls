package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

import java.util.Random;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FollowBehaviour implements Behaviour {

	private final Actor target;
	private Location previousThere;
	protected Action action;
	protected boolean aggressive = false;
	private int counter;
	QuikMaffs quikMaffs = new QuikMaffs();
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		action = null;

		double currentDistance = quikMaffs.manhattanDistance(here, there);
		double previousDistance = 3;
		if (counter == 1) {
			previousDistance = quikMaffs.euclideanDistance(here, previousThere);
		}
		counter = 1;
		if (previousDistance < 2) {
			aggressive = true;
		}
		if (rand.nextInt(2) == 1 && target.getWeapon().equals(new GiantAxe()) && aggressive){
			return new CleaveAction(actor);
		}
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				double newDistance = quikMaffs.manhattanDistance(destination, there);
				if (newDistance < currentDistance && aggressive) {
					action = new MoveActorAction(destination, exit.getName());
					currentDistance = newDistance;
				}
			}
			if (destination == there) {
				aggressive = true;
				return new AttackAction(target, "");

			}
		}
		previousThere = map.locationOf(target);
		return action;
	}
}