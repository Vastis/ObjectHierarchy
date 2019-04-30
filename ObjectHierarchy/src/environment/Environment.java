package environment;

import core.Status;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Environment implements Drawable {

    private Canvas canvas;
    private ArrayList<SimObject> objects;

    public Environment(Canvas canvas, ArrayList<SimObject> objects) {
        this.canvas = canvas;
        this.objects = objects;
        /*for(SimObject object : objects)
            new Thread(object).start();*/
        canvas.setOnMouseClicked(e -> onMouseClicked(e));
    }

    private void onMouseClicked(MouseEvent e) {
        for(SimObject object : this.objects)
            object.changeStatusIfContains(e.getX(), e.getY());
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
