package sim.utils;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import sim.components.Simulation;

public class SimTimer extends AnimationTimer {

    private GraphicsContext gc;
    private Simulation sim;

    public SimTimer(GraphicsContext gc, Simulation sim) {
        this.gc = gc;
        this.sim = sim;
    }

    @Override
    public void handle(long l) {
        this.gc.clearRect(0, 0, this.gc.getCanvas().getWidth(),
                this.gc.getCanvas().getHeight());
        this.sim.update();
        this.sim.render(this.gc);
    }
}
