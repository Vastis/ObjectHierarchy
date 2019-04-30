package environment;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SimObject implements Drawable {

    private int id;
    private double posX, posY;
    private ArrayList<SimObject> subordinateObjects;

    public SimObject(){
        this.subordinateObjects = new ArrayList<>();
        this.posX = 400*Math.random();
        this.posY = 400*Math.random();
    }
    public SimObject(int id){
        super();
        this.id = id;
    }

    public void addSubordinateObject(SimObject object){
        this.subordinateObjects.add(object);
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

    @Override
    public void update() {
        posX++;
        posY++;
        for(SimObject object : this.subordinateObjects)
            object.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillOval(posX, posY, 10, 10);
        for(SimObject object : this.subordinateObjects)
            object.draw(gc);
    }
}
