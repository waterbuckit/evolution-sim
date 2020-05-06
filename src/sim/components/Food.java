package sim.components;

import javafx.scene.canvas.GraphicsContext;

public interface Food {
    public double getX();
    public void render(GraphicsContext gc);
    public double getY();
    public double getRadius();
    public double consume();
}
