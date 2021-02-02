package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;


public class Clorus extends Creature {
    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     * @param n
     */
    public Clorus(String n) {
        super(n);
    }

    @Override
    public void move() {

    }

    @Override
    public void attack(Creature c) {

    }

    @Override
    public Creature replicate() {
        return null;
    }

    @Override
    public void stay() {

    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        return null;
    }

    @Override
    public Color color() {
        return null;
    }
}
