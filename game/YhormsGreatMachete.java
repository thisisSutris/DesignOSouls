package game;

public class YhormsGreatMachete extends Axe{
    public YhormsGreatMachete(Boolean enrage) {
        super("Yhorm's Great Machete", 'y', 95, "slashed", 60, 0);
        if (enrage) {
            hitRate = 90;
        }
    }

}
