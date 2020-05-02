package sim;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import sim.components.Simulation;
import sim.utils.SimTimer;
import sim.utils.SimulationParameters;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adam's Petri Dish");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 300);

        Simulation sim = new Simulation(new SimulationParameters(5, canvas));
        AnimationTimer timer = new SimTimer(canvas.getGraphicsContext2D(), sim);

        timer.start();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
