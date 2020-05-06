package sim.components.foods;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sim.components.Food;
import sim.utils.Vector2;

import java.util.Random;

public class StandardFood implements Food {

    private Vector2 pos;
    private double healthIncrease;
    private double radius;

    public StandardFood(Vector2 pos, Random rand) {
        this.pos = pos;
        this.healthIncrease = 1 + (rand.nextDouble() * (10 - 1));
        this.radius = 5;
    }

    @Override
    public double getX() {
        return this.pos.getX();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.ORANGE);
        gc.fillOval(this.pos.getX(), this.pos.getY(),this.radius, this.radius);
    }

    @Override
    public double getY() {
        return this.pos.getY();
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public double consume() {
        return this.healthIncrease;
    }

    @Override
    public String toString() {
        return "StandardFood{" +
                "pos=" + pos +
                ", healthIncrease=" + healthIncrease +
                ", radius=" + radius +
                '}';
    }
}
