package core;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;

public class Simulation extends StateMachine {

    private static Simulation simulation;
    private SimulationEngine simulationEngine;
    private Canvas canvas;
    private TextArea logTextArea;

    private int fps = 60;
    private double timePerTick = 1000000000.0 / fps;
    private double delta = 0.0;
    private long now;
    private long lastTime = System.nanoTime();

    private Simulation(Canvas canvas, TextArea logTextArea) {
        this.simulationEngine = new SimulationEngine(canvas);
        this.canvas = canvas;
        this.logTextArea = logTextArea;
        this.currentStatus = Status.IDLE;
    }
    public static Simulation getInstance(Canvas canvas, TextArea logTextArea){
        if(simulation != null)
            return simulation;
        return new Simulation(canvas, logTextArea);
    }

    private void setIdle(){
        this.currentStatus = Status.IDLE;
    }
    public void startSimulation() {
        this.currentStatus = Status.RUNNING;
    }
    public void pauseSimulation() {
        this.currentStatus = Status.PAUSED;
    }
    public void resumeSimulation() {
        this.currentStatus = Status.RESUMED;
    }
    public void endSimulation() {
        this.currentStatus = Status.ENDED;
    }

    @Override
    public void run() {
        this.fps = 60;
        this.timePerTick = 1000000000.0 / fps;
        this.delta = 0.0;
        this.lastTime = System.nanoTime();
        super.run();
    }

    @Override
    protected void onIdle() {
        this.lastTime = System.nanoTime();
    }
    @Override
    protected void onRunning() {
        this.now = System.nanoTime();
        this.delta += (this.now - this.lastTime) / this.timePerTick;
        this.lastTime = this.now;
        if(delta >= 1) {
            this.simulationEngine.update();
            this.simulationEngine.draw();
            this.delta--;
        }
    }
    @Override
    protected void onPaused() {
        log(">>> Simulation paused. <<< <");
        setIdle();
    }
    @Override
    protected void onResumed() {
        log(">>> Simulation resumed. <<< <");
        startSimulation();
    }
    @Override
    protected void onEnded() {
        log(">>> Simulation ended.  <<< <");
        simulation = null;
    }

    public void log(String message){
        StringBuilder sb = new StringBuilder();
        sb.append(this.logTextArea.getText());
        sb.append(" > ");
        sb.append(message);
        sb.append('\n');
        this.logTextArea.setText(sb.toString());
    }
}
