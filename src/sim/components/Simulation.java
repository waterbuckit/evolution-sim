package sim.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import sim.components.foods.StandardFood;
import sim.utils.QuadTree;
import sim.utils.SimulationParameters;
import sim.utils.Vector2;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {
    private List<Creature> creatures;
    private List<Food> food;
    private SimulationParameters simulationParameters;
    private QuadTree quadTree;

    private int time;

    // JUST FOR TESTING
    private GraphicsContext gc;

    public Simulation(SimulationParameters simulationParameters,GraphicsContext gc) {
        this.simulationParameters = simulationParameters;
        this.creatures = simulationParameters.getCreatures();
        this.food = new ArrayList<Food>();
        this.advanceDay();
        this.gc = gc;
        this.time = 0;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public void update() {
        if((this.time++) == 600){
            System.out.println("Day advanced");
            this.advanceDay();
        }

        this.quadTree = new QuadTree(new Rectangle2D(0, 0,
                this.simulationParameters.getCanvas().getWidth(),
                this.simulationParameters.getCanvas().getHeight()));

        this.food.forEach(this.quadTree::insert);

        List<Creature> newCreatures = new ArrayList<>();

        for (Creature creature : this.creatures = this.creatures.stream()
                .map(creature -> creature.update(this.simulationParameters.getCanvas()))
                .filter(creature -> creature.getHealth() >= 0)
                .collect(Collectors.toList())) {
            this.quadTree.insert(creature);

            ArrayList<Food> intersectingFood = creature.intersectFood(quadTree);

            this.food = this.food.stream()
                    .filter(food -> !intersectingFood.contains(food))
                    .collect(Collectors.toList());

            creature.intersectCreature(quadTree).forEach(newCreatures::add);
        }
        this.creatures.addAll(newCreatures);
    }

    private void advanceDay() {
        this.time = 0;
        this.food.clear();
        for(int i = 0 ; i < (10+(this.simulationParameters.getRand().nextInt(15))); i++){
            this.food.add(new StandardFood(
                    Vector2.getRandomVectorBounds(this.simulationParameters.getCanvas().getWidth()
                    ,this.simulationParameters.getRand()), this.simulationParameters.getRand()));
        }
    }


    public void render(GraphicsContext gc) {
        this.creatures.forEach(creature -> creature.render(gc));
        this.food.forEach(food -> food.render(gc));
    }
}
