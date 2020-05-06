package sim.utils;

import javafx.geometry.Rectangle2D;
import sim.components.Creature;
import sim.components.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuadTree {

    private List<Creature> containedCreatures;
    private List<Food> containedFood;
    private Rectangle2D bound;
    private static final int CAPACITY = 5;

    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southWest;
    private QuadTree southEast;

    public QuadTree(Rectangle2D bound) {
        this.containedCreatures = new ArrayList<>();
        this.containedFood = new ArrayList<>();
        this.bound = bound;
    }

    private void subdivide(){
        this.northWest = new QuadTree(new Rectangle2D(this.bound.getMinX(),this.bound.getMinY(),
                this.bound.getWidth()/2, this.bound.getHeight()/2));
        this.northEast = new QuadTree(new Rectangle2D(this.bound.getMinX() + this.bound.getWidth()/2, this.bound.getMinY(),
                this.bound.getWidth()/2, this.bound.getHeight()/2));
        this.southWest = new QuadTree(new Rectangle2D(this.bound.getMinX(),
                this.bound.getMinY() + this.bound.getHeight()/2,
                this.bound.getWidth()/2, this.bound.getHeight()/2));
        this.southEast = new QuadTree(new Rectangle2D(this.bound.getMinX()+ this.bound.getWidth()/2,
                this.bound.getMinY()+this.bound.getHeight()/2,
                this.bound.getWidth()/2, this.bound.getHeight()/2));
    }

    public boolean insert(Creature creature) {

        //gc.setStroke(Color.BLACK);
        //gc.strokeRect(this.bound.getMinX(), this.bound.getMinY(), this.bound.getWidth(), this.bound.getHeight());

        if(!this.bound.contains(creature.getX(), creature.getY())){
            return false;
        }

        if(this.containedCreatures.size() < this.CAPACITY && this.northWest == null){
            this.containedCreatures.add(creature);
            return true;
        }

        if(northWest == null)
            subdivide();
        if(northWest.insert(creature))
            return true;
        if(northEast.insert(creature))
            return true;
        if(southWest.insert(creature))
            return true;
        if(southEast.insert(creature))
            return true;

        return false;
    }

    public ArrayList<Creature> queryRangeCreature(Rectangle2D bound) {
        return this.queryCreatures(bound, new ArrayList<>());
    }

    public ArrayList<Food> queryRangeFood(Rectangle2D bound) {
        return this.queryFood(bound, new ArrayList<>());
    }


    private ArrayList<Food> queryFood(Rectangle2D bound, ArrayList<Food> foods) {
        if (!this.bound.intersects(bound)){
            return foods;
        }

        this.containedFood = this.containedFood.stream()
                .filter(food -> {
                    if(bound.contains(food.getX(), food.getY())){
                        System.out.println("COLLIDED");
                        foods.add(food);
                        return false;
                    }
                    return true;
                }).collect(Collectors.toList());

        if (northWest == null)
            return foods;

        this.northWest.queryFood(bound, foods);
        this.northEast.queryFood(bound, foods);
        this.southWest.queryFood(bound, foods);
        this.southEast.queryFood(bound, foods);

        return foods;
    }
    private ArrayList<Creature> queryCreatures(Rectangle2D bound, ArrayList<Creature> creatures) {
        if (!this.bound.intersects(bound)){
            return creatures;
        }
        for(Creature creature : this.containedCreatures){
            if(bound.contains(creature.getX(), creature.getY()))
                creatures.add(creature);
        }

        if (northWest == null)
            return creatures;

        this.northWest.queryCreatures(bound, creatures);
        this.northEast.queryCreatures(bound, creatures);
        this.southWest.queryCreatures(bound, creatures);
        this.southEast.queryCreatures(bound, creatures);

        return creatures;
    }

    public boolean insert(Food food) {
        if(!this.bound.contains(food.getX(), food.getY())){
            return false;
        }

        if(this.containedFood.size() < this.CAPACITY && this.northWest == null){
            this.containedFood.add(food);
            return true;
        }

        if(northWest == null)
            subdivide();
        if(northWest.insert(food))
            return true;
        if(northEast.insert(food))
            return true;
        if(southWest.insert(food))
            return true;
        if(southEast.insert(food))
            return true;

        return false;
    }
}
