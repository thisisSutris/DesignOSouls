package game;

import edu.monash.fit2099.engine.*;

public class ResetAction extends Action{

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
