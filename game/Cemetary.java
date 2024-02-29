package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Cemetary extends Ground {

    protected Random rand = new Random();

    public Cemetary() {
        super('c');
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    public void spawnUndead(){
        if(rand.nextInt(100) < 25){
            System.out.println("undead spawned");
        } else {
            System.out.println("undead not spawned");
        }
    }




}
