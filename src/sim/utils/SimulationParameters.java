package sim.utils;

import javafx.scene.canvas.Canvas;
import sim.components.Creature;
import sim.components.creatures.StandardCreature;

import java.util.ArrayList;
import java.util.Random;

public class SimulationParameters {

    private final Canvas canvas;
    private ArrayList<Creature> creatures;
    private Random rand;

    public SimulationParameters(int numberOfCreatures, Canvas canvas) {
        this.rand = new Random();
        this.creatures = new ArrayList<>();
        this.canvas = canvas;

        for(int i = 0; i < numberOfCreatures; i++){
            this.creatures.add(
                    new StandardCreature(this.getCoordWithin(canvas.getWidth()),
                            this.getCoordWithin(canvas.getHeight()),rand));
        }
    }

    private double getCoordWithin(double bound) {
        return this.rand.nextDouble()*bound;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public ArrayList<Creature> getCreatures() {
        return this.creatures;
    }
}
