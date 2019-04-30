package io;

import hierachy.SimObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLParser implements InputManager<Document> {

    private String path;
    private Document document;

    public XMLParser(String path) throws IOException, SAXException, ParserConfigurationException {
        this.path = path;
        this.document = getSource();
    }


    @Override
    public Document getSource() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(this.path));
        document.getDocumentElement().normalize();
        return document;
    }

    @Override
    public ArrayList<SimObject> read() throws Exception {
        ArrayList<SimObject> objects = new ArrayList<>();
        Node root = this.document.getChildNodes().item(0);
        ArrayList<Node> primalNodes = getChildNodes(root);
        for(Node node : primalNodes){
            SimObject object = getObjectFromNode(node);
            objects.add(object);
        }
        return objects;
    }
    private ArrayList<Node> getChildNodes(Node node){
        ArrayList<Node> childNodes = new ArrayList<>();
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); i++){
            Node child = children.item(i);
            if(!(child instanceof Text)){
                childNodes.add(child);
            }
        }
        return childNodes;
    }
    private SimObject getObjectFromNode(Node node){
        SimObject object = new SimObject();
        NodeList attributes = node.getChildNodes();
        for(int i = 0; i < attributes.getLength(); i++){
            Node n = attributes.item(i);
            switch(n.getNodeName().trim()){
                case "id":
                    int id = Integer.parseInt(
                            n.getChildNodes().item(0)
                                    .getNodeValue().trim());
                    object.setId(id);
                    break;
                case "subordinates":
                    ArrayList<Node> childNodes = getChildNodes(n);
                    for(Node child : childNodes){
                        SimObject subordinate = getObjectFromNode(child);
                        object.addSubordinateObject(subordinate);
                    }
                    break;
            }
        }
        return object;
    }

    @Override
    public void save(Document source, ArrayList<SimObject> data) throws Exception {

    }
}
