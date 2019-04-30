package environment;

import core.StateMachine;
import core.Status;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class SimObject extends StateMachine implements Drawable {

    public static final int radius = 15, diameter = 2 * radius, radiusSquared = radius * radius;

    private int id;
    private double posX, posY;
    private ArrayList<SimObject> subordinateObjects;
    private Color color;

    public SimObject(){
        super();
        this.subordinateObjects = new ArrayList<>();
        this.currentStatus = Status.RUNNING;
    }
    public SimObject(int id){
        super();
        this.id = id;
    }

    public void addSubordinateObject(SimObject object){
        this.subordinateObjects.add(object);
        //new Thread(object).start();
    }
    public void changeStatusIfContains(double x, double y){
        double deltaX = x - this.getCenterX();
        double deltaY = y - this.getCenterY();
        if((deltaX * deltaX + deltaY * deltaY) < SimObject.radiusSquared) {
            this.currentStatus = Status.PAUSED;
        } else {
            for(SimObject object : this.subordinateObjects)
                object.changeStatusIfContains(x, y);
        }
    }

    @Override
    public void update() {
        super.step();
        for(SimObject object : this.subordinateObjects)
            object.update();
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillOval(this.posX, this.posY, diameter, diameter);
        gc.fillText("" + id, this.posX, this.posY);
        for(SimObject object : this.subordinateObjects) {
            gc.strokeLine(this.getCenterX(), this.getCenterY(), object.getCenterX(), object.getCenterY());
            object.draw(gc);
        }
    }
    @Override
    protected void onIdle() {}
    @Override
    protected void onRunning() {
        this.color = Color.BLUE;
    }
    @Override
    protected void onPaused() {
        this.color = Color.SANDYBROWN;
        this.currentStatus = Status.IDLE;
    }
    @Override
    protected void onResumed() {
        this.color = Color.BLACK;
        this.currentStatus = Status.IDLE;
    }
    @Override
    protected void onEnded() {
        this.color = Color.RED;
        for(SimObject object : this.subordinateObjects)
            object.setStatus(Status.ENDED);
    }

    private String getName(){
        return "Object " + this.id;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(Status status){
        this.currentStatus = status;
    }public double getCenterX() {
        return this.posX + radius;
    }
    public double getCenterY() {
        return this.posY + radius;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }
}
