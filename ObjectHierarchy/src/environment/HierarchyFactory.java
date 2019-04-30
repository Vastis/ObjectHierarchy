package environment;

import io.InputManager;
import io.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class HierarchyFactory {

    private static HierarchyFactory hierarchyFactory;
    private ArrayList<SimObject> objects;
    private InputManager input;

    public HierarchyFactory(){
        try {
            this.input = new XMLParser("./resrc/hierarchy.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
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
