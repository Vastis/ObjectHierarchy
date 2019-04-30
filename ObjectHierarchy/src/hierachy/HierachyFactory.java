package hierachy;

import io.InputManager;

import java.util.ArrayList;

public class HierachyFactory<T> {

    private ArrayList<SimObject> objects;
    private InputManager input;

    public HierachyFactory(InputManager input) {
        this.input = input;
    }

    public ArrayList<SimObject> getObjects() throws Exception {
        if(this.objects == null)
            this.objects = this.input.read();
        return objects;
    }
}
