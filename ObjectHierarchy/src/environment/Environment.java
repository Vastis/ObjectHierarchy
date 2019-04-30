package environment;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Environment implements Drawable {

    private ArrayList<SimObject> objects;

    public Environment(ArrayList<SimObject> objects) {
        this.objects = objects;
    }

    @Override
    public void update() {
        for(SimObject object : this.objects)
            object.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        for(SimObject object : this.objects)
            object.draw(gc);
    }
}
