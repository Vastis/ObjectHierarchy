package environment;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    void update();
    void draw(GraphicsContext gc);
}
