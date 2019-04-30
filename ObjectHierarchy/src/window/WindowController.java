package window;

import core.Simulation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class WindowController {

    private Simulation simulation;
    private boolean paused = false;
    @FXML
    private Canvas canvas;
    @FXML
    private Button
        startButton,
        pauseButton,
        stopButton;
    @FXML
    private TextArea logTextArea;

    @FXML
    private void onStartButtonClicked(){
        start();
        this.stopButton.setDisable(false);
        this.pauseButton.setDisable(false);
        this.startButton.setDisable(true);
    }
    private void start() {
        this.logTextArea.setText(" > >>> Simulation started. <<< <\n");
        this.simulation = Simulation.getInstance(this.canvas, this.logTextArea);
        new Thread(this.simulation).start();
        this.simulation.startSimulation();
    }
    @FXML
    private void onPauseButtonClicked(){
        if(this.paused){
            this.pauseButton.setText("Pause");
            this.simulation.resumeSimulation();
        }
        else {
            this.pauseButton.setText("Resume");
            this.simulation.pauseSimulation();
        }
        this.paused = !this.paused;
    }
    @FXML
    private void onStopButtonClicked(){
        this.simulation.endSimulation();
        this.startButton.setDisable(false);
        this.pauseButton.setDisable(true);
        this.stopButton.setDisable(true);
    }

    public void stopSimulation(){
        this.simulation.endSimulation();
    }
}
