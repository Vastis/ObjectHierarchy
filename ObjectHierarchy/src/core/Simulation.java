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
        this.simulationEngine = new SimulationEngine(canvas, logTextArea);
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
        setStatus(Status.IDLE);
    }
    public void startSimulation() {
        setStatus(Status.RUNNING);
    }
    public void pauseSimulation() {
        setStatus(Status.PAUSED);
    }
    public void resumeSimulation() {
        setStatus(Status.RESUMED);
    }
    public void endSimulation() {
        setStatus(Status.ENDED);
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
        this.now = System.nanoTime();
        this.lastTime = this.now;
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
        log(this.logTextArea, ">>> Simulation paused. <<< <");
        setIdle();
    }
    @Override
    protected void onResumed() {
        log(this.logTextArea, ">>> Simulation resumed. <<< <");
        startSimulation();
    }
    @Override
    protected void onEnded() {
        log(this.logTextArea, ">>> Simulation ended.  <<< <");
        simulation = null;
    }

    public static void log(TextArea textArea, String message){
        StringBuilder sb = new StringBuilder();
        sb.append(textArea.getText());
        sb.append(" > ");
        sb.append(message);
        sb.append('\n');
        textArea.setText(sb.toString());
    }
}
