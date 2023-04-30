package handin2.ModelPart;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

<<<<<<< HEAD
import handin2.ModelPart.Dijkstra.DijkstraSP;
import handin2.ModelPart.Dijkstra.Graph;


=======
import handin2.ModelPart.Objects.Area;
import handin2.ModelPart.Objects.Building;
import handin2.ModelPart.Objects.Coastline;
import handin2.ModelPart.Objects.Road;
import handin2.ModelPart.Objects.Water;
import handin2.ModelPart.Objects.Way;
import handin2.ModelPart.TernarySearchTree.TernaryTree;
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201

//import javafx.geometry.Point2D;

public class Model implements Serializable {
    /*We are creating lists that will contain objects of the different types in the .osm file*/
<<<<<<< HEAD
    public List<Line> lines = new ArrayList<Line>();
    public List<Way> ways = new ArrayList<Way>();
    public List<Road> roads = new ArrayList<Road>();
    public List<Building> buildings = new ArrayList<Building>();
    public List<Coastline> coastlines = new ArrayList<Coastline>();
    public List<Water> waters = new ArrayList<Water>();
    public List<Area> areas = new ArrayList<Area>();
    public  Graph roadGraph; 
    public List<Node> allNodes = new ArrayList<Node>();
     
=======
    public List<Line> lines = new ArrayList<>();
    public List<Way> ways = new ArrayList<>();
    public List<Road> roads = new ArrayList<>();
    public List<Building> buildings = new ArrayList<>();
    public List<Coastline> coastlines = new ArrayList<>();
    public List<Water> waters = new ArrayList<>();
    public List<Area> areas = new ArrayList<>();
    public List<Address> addresses = new ArrayList<>();
    public TernaryTree tree = new TernaryTree();
    
    
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
    public double minlat;
    public double maxlat;
    public double minlon;
    public double maxlon;


    public static Model load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException, XMLStreamException, FactoryConfigurationError {
        if (filename.endsWith(".obj")) {
            try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
                return (Model) in.readObject();
            }
        }

        return new Model(filename);
    }
    

    public Model(String filename) throws XMLStreamException, FactoryConfigurationError, IOException {
        /*This method is seeing which kind of file we are working with and what we need to parse */
        if (filename.endsWith(".osm.zip")) {
            parseZIP(filename);
        } else if (filename.endsWith(".osm")) {
            parseOSM(filename);
        } else {
            parseTXT(filename);
        }
        save(filename+".obj");
    }

    void save(String filename) throws FileNotFoundException, IOException {
        try (var out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    private void parseZIP(String filename) throws IOException, XMLStreamException, FactoryConfigurationError {
        var input = new ZipInputStream(new FileInputStream(filename));
        input.getNextEntry();
        parseOSM(input);
    }

    private void parseOSM(String filename) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
       /*It works with the parseZip */
        parseOSM(new FileInputStream(filename));
    }
    
    private void parseOSM(InputStream inputStream) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
        /*The streamReader is kind of an iterator that works like a pointer that will read the next 
        (reader object) and the createXMLStreamReader has to have a reader inside like (fileReader) but we use 
        InputStreamReader */

        var input = XMLInputFactory.newInstance().createXMLStreamReader(new InputStreamReader(inputStream));


        var id2node = new HashMap<Long, Node>(); //this map holds every Node(coordinate) and their id
        HashMap<Long, String> id2role = new HashMap<Long, String>(); //this map holds every way's id and tehir role in a relation

        var way = new ArrayList<Node>();
        String type = null;
        String buildingName = null;
        Long wayId = null;
        Long relationId = null;
<<<<<<< HEAD

      
=======
        Long nodeId = null;
        
        String city = null;
        String housenumber = null; 
        String postcode = null;
        String street = null;
        
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
        //create booleans, that will determine what types the different elements in the .osm file are
        var isRoad = false;
        var isCoast = false;
        var isBuilding = false;
        var isWater = false;
        var isArea = false;
        
        //create a String, that will determine the type of the area
        String areaType = null;
       
      

        //way array to the graph
  


        /*the while loop read every input of the file we are using */
        while (input.hasNext()) {
            /*Our tagkind is input next which means it goes through everything in the line from start to end */
            var tagKind = input.next();
            
            /*This means if the tagkind equals the first element */
            if (tagKind == XMLStreamConstants.START_ELEMENT) {
                /*name indicates the names of every input in the line, so we can see and compare */
                var name = input.getLocalName();

                if (name == "bounds") {
                     /*Bound is the whole area we are working with, thats why we have minlat and maxlat, 
                     minlon and maxlon   */
                    minlat = Double.parseDouble(input.getAttributeValue(null, "minlat"));
                    maxlat = Double.parseDouble(input.getAttributeValue(null, "maxlat"));
                    minlon = Double.parseDouble(input.getAttributeValue(null, "minlon"));
                    maxlon = Double.parseDouble(input.getAttributeValue(null, "maxlon"));
                } else if (name == "node") {
                     /*if the name input qeuals node, we want to extract the id, so we use getAttributeValue "id"
                      we also want the latitude and the longtitude of the node id, and put in a hashMap of 
                      <Long, Node>, where a Node object has a lat and a lon, and our id is of the type of a Long
                     */
                    nodeId = Long.parseLong(input.getAttributeValue(null, "id"));
                    var lat = Double.parseDouble(input.getAttributeValue(null, "lat"));
                    var lon = Double.parseDouble(input.getAttributeValue(null, "lon"));
                    id2node.put(nodeId, new Node(lat, lon, nodeId));

                } else if (name == "way") {
                    /*Everytime we see "way" in the file, we clear the List, 
                    such that the List of Nodes are specific to each way*/
                    
                    way.clear();

                    wayId = Long.parseLong(input.getAttributeValue(null, "id"));

                    /*We set the boolenas to false, or else they will always be true, which means that 
                    every way encountered will be added as example a building inside the buildings list.
                    We only want isBuilding to be true after checking the tag*/
                    isBuilding = false;
                    isWater = false;
                    isCoast = false;
                    isWater = false;
                    isArea = false;
                } else if (name == "relation"){
                    /*Everytime we see "relation" in the file, we clear the HashMap, 
                    such that the Map of id's and their role are specific to each relation*/
                    id2role.clear();

                    relationId = Long.parseLong(input.getAttributeValue(null, "id"));

                } else if (name == "member"){ //member is a tag under a relation-tag
                    var ref = Long.parseLong(input.getAttributeValue(null, "ref")); //ref is the id-reference to a way(list of Nodes)
                    var role = input.getAttributeValue(null, "role"); //role is wether the way act as an inner, outer or nothing

                    id2role.put(ref, role); //put every id to a way at key, and their role at value
                    

                } else if (name == "tag") {
                    var k = input.getAttributeValue(null, "k");
                    var v = input.getAttributeValue(null, "v");
                   
                    if(k.equals("addr:city")){
                        city = v;
                    }
                    if(k.equals("addr:housenumber")){
                        housenumber = v;
                    }
                    if(k.equals("addr:postcode")){
                        postcode = v;
                    }
                    if(k.equals("addr:street")){
                        street = v;
                    }



                    if(k.equals("name")){
                        buildingName = v;
                    }



                    //set the booleans to true, if we are dealing with a specific type of element in the .osm file
                    if(k.equals("building")){
                        isBuilding = true;
                        type = "building";
                    }
                    if(v.equals("water")){
                        isWater = true;
                        type = "water";
                    }
                    if (v.equals("coastline")){
                        isCoast = true;
                        type = "coastline";
                    }   
                    
                    if(k.equals("highway")){
                        isRoad = true;
                        type = "highway";
                    }
                    if(k.equals("landuse")|| k.equals("natural")){
                        isArea = true;
                        type = "landuse";

                        //Determine the type of the area
                        if (v.equals("forest")){
                            areaType = "forest";
                        } 
                        if (v.equals("farmland")){
                            areaType = "farmland";
                        }
                        if (v.equals("residential")){
                            areaType = "residential";
                        } 
                    }
                
                } else if (name == "nd") {
                    /*The same here we want to get the ref that is the same as node id, and we want the node
                     * so node = id2node.get(ref) which means our HashMap value and we have a key which is ref
                     * so we use the get(ref) which gives us the value which is the node and add it to our way List
                     */
                    var ref = Long.parseLong(input.getAttributeValue(null, "ref"));
                    var node = id2node.get(ref);
                    way.add(node);


                  


                    allNodes.add(node);
                }
                
                /*If our tagKind is in the end of the tag */
            } else if (tagKind == XMLStreamConstants.END_ELEMENT) {
                var name = input.getLocalName();
                /*We create objects of the different types of element in the list by looking at the booleans*/
                if (name == "node"){
                    Address address = new Address(nodeId, city, postcode, street, housenumber);
                    addresses.add(address);
                    tree.insert(address.getAddress());

                }
                
                if (name == "way") {
                    ways.add(new Way(way, wayId));

                    if(isBuilding) {
                        buildings.add(new Building(way, wayId, buildingName));
                        tree.insert(buildingName);
                    } else if (isArea){
                        areas.add(new Area(way, areaType, wayId));
                    } else if(isCoast){
                        coastlines.add(new Coastline(way, wayId));
                    } else if(isWater){
                        waters.add(new Water(way, wayId));
                    } else if (isRoad){
                        roads.add(new Road(way, wayId));


                                      //Her adder vi vores noder til vores array: 
                    //wayArray
                    //Man kunne lave et loop, hvor den for hvert tredje eller fjerse propper den ind som et edge
                    //efter det her skal  finde en måde hvor man tager den i par/bider og dernæste slette/ glemme den(inde dijkstra)
                    //det eneste der skal ske her er det skal tilføjes i array, med graphen og resten skal ske inde i dijkstra

                    } 
  
                } 
                if (name == "relation"){
                    /*if the endtag reaches a "relation" we will create objects of each reference to a way,
                    which are stored in the map id2role, based on the type of the relation. We find the list
                    of Nodes (way) to each way, by running through the list of all ways, and finding the
                    id that matches the id in the map id2role.*/

                    Relation relation = new Relation(relationId, id2role, type, areaType, buildingName);
                    
                    var map = relation.getMap().keySet();
                    List<Long> keysList = new ArrayList<>(map);
                    
                    for (var key : keysList){
                        for (var w :ways){
                            if (relation.getType().equals("building")){
                    
                                if(key.equals(w.getId())){
                                    buildings.add(new Building(w.getWay(), w.getId(), relation.getName()));
                                    
                                }
                            }
                            else if (relation.getType() == "water"){
                                if(key.equals(w.getId())){
                                    waters.add(new Water(w.getWay(), w.getId()));
                                }
                            }
                            else if (relation.getType() == "coastline"){
                                if(key.equals(w.getId())){
                                    coastlines.add(new Coastline(w.getWay(), w.getId()));
                                }
                            }
                            else if (relation.getType() == "highway"){
                                if(key.equals(w.getId())){
                                    roads.add(new Road(w.getWay(), w.getId()));

                                      //Her adder vi vores noder til vores array: 
                    //wayArray
                    //Man kunne lave et loop, hvor den for hvert tredje eller fjerse propper den ind som et edge
                    //efter det her skal  finde en måde hvor man tager den i par/bider og dernæste slette/ glemme den(inde dijkstra)
                    //det eneste der skal ske her er det skal tilføjes i array, med graphen og resten skal ske inde i dijkstra


                                }
                            }
                            else if (relation.getType() == "landuse"){
                                if(key.equals(w.getId())){
                                    areas.add(new Area(w.getWay(), relation.getAreaType(), w.getId()));
                                }
                            }
                        }  
                    }   
                }
              
            }
<<<<<<< HEAD
    
    
        } // end of while 
        roadGraph = new Graph(roads); 
        DijkstraSP dijkstra = new DijkstraSP(roadGraph, 0);
        System.out.println("Has path to?: "+ dijkstra.hasPathTo(roadGraph.getIntersections().length-1)); 
        System.out.println("The edges: " +dijkstra.getShortestPathEdges(roadGraph.getIntersections().length-1));
        System.out.println("The distance: " +dijkstra.distTo(roadGraph.getIntersections().length-1)); 
=======
        } // end of while

        System.out.println(buildings.get(0).getName() + buildings.get(1).getName());
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
    }

    private void parseTXT(String filename) throws FileNotFoundException {
        /*This works with a text file and reads every input in the line and adds the line object 
          where the line object splits it etc */
        File f = new File(filename);
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                lines.add(new Line(s.nextLine()));
            }
        }
    }

    /*public void add(Point2D p1, Point2D p2) {
        lines.add(new Line(p1, p2));
    }*/
}