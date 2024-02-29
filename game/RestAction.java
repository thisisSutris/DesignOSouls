package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class RestAction extends Action {

    protected Actor target;
    protected String name;
    protected Bonfire bonfire;

    public RestAction(Actor target, String name, Bonfire bonfire) {
          this.target = target;
          this.name = name;
          this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) target;
        EstusFlask item = player.getEstusFlask();
        item.refill();

        int healing = player.getMaxHp() - player.getHealth();
        target.heal(healing);

        for (Bonfire bonfire : Player.getBonfires()){
            bonfire.removeCapability(Status.RESPAWNPOINT);
        }
        bonfire.addCapability(Status.RESPAWNPOINT);

        return target + " rests at bonfire";
    }

    @Override
        public String menuDescription(Actor actor) {
            return "Rest at " + name;
        }
}

