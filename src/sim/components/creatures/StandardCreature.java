package sim.components.creatures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sim.components.Creature;
import sim.utils.Vector2;

import java.util.Random;

public class StandardCreature implements Creature {

    private static final double MAX_SPEED = 5;
    private Random rand;
    private Vector2 pos;
    private double health;

    public StandardCreature(double x, double y, Random rand) {
        this.health = 100;
        this.pos = new Vector2(x, y);
        this.rand = rand;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.fillOval(this.pos.getX(), this.pos.getY(),3, 3);
        gc.strokeOval(this.pos.getX(), this.pos.getY(),3, 3);
    }

    @Override
    public Creature update(Canvas canvas) {
        this.health -=0.1;
        Vector2 addVelocity = Vector2.getRandomVectorBounds(MAX_SPEED, this.rand);
        Vector2 newPos = this.pos.copy().add(addVelocity);

        while(newPos.invalid(canvas)){
            addVelocity = Vector2.getRandomVectorBounds(MAX_SPEED, this.rand);
            newPos = this.pos.copy().add(addVelocity);
        }
        this.pos.set(newPos);
        return this;
    }

    @Override
    public double getHealth() {
        return this.health;
    }
}
