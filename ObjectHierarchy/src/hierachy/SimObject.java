package hierachy;

import java.util.ArrayList;

public class SimObject {

    private int id;
    private ArrayList<SimObject> subordinateObjects;

    public SimObject(){
        this.subordinateObjects = new ArrayList<>();
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
}
