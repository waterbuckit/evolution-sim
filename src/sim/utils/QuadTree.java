package sim.utils;

import javafx.geometry.Rectangle2D;
import sim.components.Creature;

import java.util.ArrayList;

public class QuadTree {

    private ArrayList<Creature> containedCreatures;
    private Rectangle2D bound;
    private static final int CAPACITY = 4;

    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southWest;
    private QuadTree southEast;

    public QuadTree(Rectangle2D bound) {
        this.containedCreatures = new ArrayList<>();
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


}
