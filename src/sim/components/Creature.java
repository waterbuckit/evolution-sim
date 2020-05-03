package sim.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface Creature {
    public void render(GraphicsContext gc);
    public Creature update(Canvas canvas);
    public double getHealth();
    public double getX();
    public double getY();
}
