package sim.utils;

import javafx.scene.canvas.Canvas;

import java.util.Random;

public class Vector2 {
    private double x;
    private double y;

    public Vector2() {
    }

    public Vector2(Vector2 vec2) {
        set(vec2);
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 getRandomVectorBounds(double bound, Random rand) {
        return new Vector2(-bound + (rand.nextDouble() * (bound + bound)),
                -bound + (rand.nextDouble() * (bound + bound)));
    }

    public Vector2 add(Vector2 u){
        this.x += u.getX();
        this.y += u.getY();
        return this;
    }

    public Vector2 sub(Vector2 u){
        this.x -= u.getX();
        this.y += u.getY();
        return this;
    }

    public double dot(Vector2 u){
        return (this.x * u.getX())+(this.y*u.getY());
    }

    public Vector2 multiply(double s){
        this.x *= s;
        this.y *= s;
        return this;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2 copy () {
		return new Vector2(this);
	}

	public Vector2 set (Vector2 v) {
		x = v.x;
		y = v.y;
		return this;
	}

    public boolean invalid(Canvas canvas) {
        return this.x < 0 || this.x > canvas.getWidth() || this.y < 0 || this.y > canvas.getHeight();
    }
}
