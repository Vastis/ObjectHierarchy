package core;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;

public class SimulationEngine {

    private Canvas canvas;
    private TextArea logTextArea;

    private double x = 0, y = 0;

    public SimulationEngine(Canvas canvas, TextArea logTextArea) {
        this.canvas = canvas;
        this.logTextArea = logTextArea;
    }

    public void update() {
        x++;
        y++;
    }

    public void draw() {
        this.canvas.getGraphicsContext2D().clearRect(0,0,this.canvas.getWidth(), this.canvas.getHeight());
        canvas.getGraphicsContext2D().fillOval(x,y,10,10);
    }
}
