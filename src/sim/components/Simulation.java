package sim.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import sim.utils.QuadTree;
import sim.utils.SimulationParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {
    private List<Creature> creatures;
    private SimulationParameters simulationParameters;
    private QuadTree quadTree;

    public Simulation(SimulationParameters simulationParameters) {
        this.simulationParameters = simulationParameters;
        this.creatures = simulationParameters.getCreatures();
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public void update() {
        // remove if
        this.quadTree = new QuadTree(new Rectangle2D(0, 0,
                this.simulationParameters.getCanvas().getWidth(),
                this.simulationParameters.getCanvas().getHeight()));

        for (Creature creature : this.creatures = this.creatures.stream()
                .map(creature -> creature.update(this.simulationParameters.getCanvas()))
                .filter(creature -> creature.getHealth() >= 0)
                .collect(Collectors.toList())) {
            this.quadTree.insert(creature);
        }
        // from our new set of creatures: check if they can reproduce
    }

    public void render(GraphicsContext gc) {
        this.creatures.forEach(creature -> creature.render(gc));
    }
}
