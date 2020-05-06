package sim.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sim.components.creatures.StandardCreature;
import sim.utils.QuadTree;

import java.util.ArrayList;

public interface Creature {
    static Creature procreate(StandardCreature standardCreature, Creature creature) {
        return null;
    }

    public void render(GraphicsContext gc);
    public Creature update(Canvas canvas);
    public double getHealth();
    public double getX();
    public double getY();

    ArrayList<Food> intersectFood(QuadTree quadTree);

    ArrayList<Creature> intersectCreature(QuadTree quadTree);
}
