package core;

import environment.Environment;
import environment.HierarchyFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;

public class SimulationEngine {

    private Canvas canvas;
    private TextArea logTextArea;
    private Environment environment;

    public SimulationEngine(Canvas canvas, TextArea logTextArea) {
        this.canvas = canvas;
        this.logTextArea = logTextArea;
        prepareEnvironment(HierarchyFactory.getInstance());
    }

    private void prepareEnvironment(HierarchyFactory hierarchyFactory) {
        try {
            this.environment = new Environment(hierarchyFactory.getObjects());
        } catch (NullPointerException e){
            System.err.println("Objects hierarchy has not been loaded or did not load properly.");
        }
    }

    public void update() {
        this.environment.update();
    }

    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0,0,this.canvas.getWidth(), this.canvas.getHeight());
        this.environment.draw(gc);
    }
}
