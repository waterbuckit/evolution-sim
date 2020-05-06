package sim.components.creatures;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sim.components.Creature;
import sim.components.Food;
import sim.utils.QuadTree;
import sim.utils.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class StandardCreature implements Creature {

    private static final double MAX_SPEED = 5;
    private Random rand;
    private Vector2 pos;
    private double health;
    private double radius;
    private double diameter;

    public StandardCreature(double x, double y, Random rand) {
        this.health = 100;
        this.pos = new Vector2(x, y);
        this.rand = rand;
        this.radius = 3;
        this.diameter = radius*2;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(this.pos.getX(), this.pos.getY(),this.radius, this.radius);
    }

    @Override
    public Creature update(Canvas canvas) {
        //this.health -=0.1;
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

    @Override
    public double getX() {
        return this.pos.getX();
    }

    @Override
    public double getY() {
        return this.pos.getY();
    }

    @Override
    public ArrayList<Food> intersectFood(QuadTree quadTree) {
        ArrayList<Food> intersecting;
        for (Food food : intersecting = quadTree.queryRangeFood(
                new Rectangle2D(this.getX(), this.getY(), this.diameter, this.diameter))) {
            this.health += food.consume();
        }
        return intersecting;
    }

    @Override
    public ArrayList<Creature> intersectCreature(QuadTree quadTree) {

        ArrayList<Creature> babies = new ArrayList<>();
        for (Creature creature : quadTree.queryRangeCreature(
                new Rectangle2D(this.getX(), this.getY(), this.diameter, this.diameter))) {
            babies.add(Creature.procreate(this, creature));
        }
        return babies;
    }
}
