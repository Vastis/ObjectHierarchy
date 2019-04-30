package environment;

import io.InputManager;

import java.util.ArrayList;

public class HierarchyFactory {

    private static HierarchyFactory hierarchyFactory;
    private ArrayList<SimObject> objects;
    private InputManager input;

    private HierarchyFactory(InputManager input) {
        this.input = input;
    }

    public static HierarchyFactory getInstance(){
        return hierarchyFactory;
    }
    public static HierarchyFactory getInstance(InputManager input){
        if(hierarchyFactory == null)
            hierarchyFactory = new HierarchyFactory(input);
        return hierarchyFactory;
    }

    public ArrayList<SimObject> getObjects() {
        if(this.objects == null)
            this.objects = this.input.read();
        return objects;
    }
}
