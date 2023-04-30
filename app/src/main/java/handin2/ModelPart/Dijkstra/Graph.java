package handin2.ModelPart.Dijkstra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import handin2.ModelPart.Node;
import handin2.ModelPart.Road;


public class Graph implements Serializable{
  private  int V;  // numbers of vertices
  private int E;  // numbers of edges
  private List<DirectedEdge>[] adj;  //adjacency lists
  private List<Road> roads; // array of Node objects
  private Node[] intersections;
  @SuppressWarnings("unchecked")



    public Graph(List <Road> roads) {
    /*The contructor takes the numbers of vertices so it can build datastructure (vertex index arrays) */
    this.E = 0;
    this.roads = roads;
    Map<Node, List<Road>> mapRoad = new HashMap<>(); 

    System.out.println("Iterating through roads! There are " + roads.size() + " roads.");

    for(Road road: roads){
      if(road.first() != null){ // Only add roads with if they have nodes. Some roads have no nodes for some reason
        if(mapRoad.containsKey(road.first())){
          List<Road>roadsIntersection = mapRoad.get(road.first());
  
          roadsIntersection.add(road); 
        } else{
          List<Road> roadIntersection = new ArrayList<>();
          roadIntersection.add(road); 
          mapRoad.put(road.first(), roadIntersection); 
        }
  
        if(mapRoad.containsKey(road.last())){
          List<Road>roadsIntersection = mapRoad.get(road.last());
  
          roadsIntersection.add(road); 
        } else{
          List<Road> roadIntersection = new ArrayList<>();
          roadIntersection.add(road); 
          mapRoad.put(road.last(), roadIntersection); 
        }
      }
    }

    System.out.println("We have added " + mapRoad.keySet() + " intersections.");


    //Intersection is an array of all the vertices which is the intersections
    intersections = mapRoad.keySet().toArray(new Node[0]); // new Node[0] lets toArray convert the array to an array of Node instead of an array of Objet[]
    
    System.out.println("intersections array size: " + intersections.length);
    
    V = intersections.length; // Set V (number of vertices) so other classes that use this number still work

    System.out.println("V: " + V);

   //This code is creating an array of lists where each list contains DirectedEdge objects
   // (an array of lists of DirectedEdge objects) with a length equal to the number of vertices V.
    adj = (List<DirectedEdge>[]) new List[intersections.length];
  
    /*It initializes each element of the array to a new empty ArrayList<DirectedEdge> by iterating through each vertex v from 0 to V-1 
    using a for loop. adj[v] will contain a list of all the edges directed from vertex v to other vertices in the graph.*/

    // Iterate through all intersections
       for (int i = 0; i < intersections.length; i++){

        adj[i] = new ArrayList <DirectedEdge>();
        
        // Get the intersection node we are currently looking at
        Node intersection = intersections[i];

        // Get the roads that touch this intersection
        List<Road> intersectionRoads = mapRoad.get(intersection);
        
        // Now we want to make each road into a directed edge
        for(Road road : intersectionRoads){
          //if the road objects first node is equal to our vertex (intersection)
          if(road.first() == intersection){
            Node from = road.first();
            Node to = road.last();

            addEdge(from, to, road);

            
          } else{
            Node from = road.last();
            Node to = road.first();

            addEdge(from, to, road);

          }
        }

    }

    

  }

  
  public int getIntersectionIndex(Node node) {
    for (int i = 0; i < intersections.length; i++) {
        if (intersections[i].equals(node)) {
            return i;
        }
    }
    throw new IllegalArgumentException("Node not found in graph");
}

     public void addEdge(Node from, Node to, Road road) {
   //addEdge takes a dirceted edge and adds it to the to the graph
      int v = getIntersectionIndex(from);
      int w = getIntersectionIndex(to);
      DirectedEdge e = new DirectedEdge(v, w, road);

      adj[e.from()].add(e);
    E++;
 }   

    public Iterable<DirectedEdge> adj(int v) {
    // it return an iterable of all the edges point from and given vertex
    return adj[v]; 
 }

    public int V() {
  // returns numbers of vertices
    return V;
 } 

    public int E() {
   // returns numbers of edges
    return E;
 }
 public Node [] getIntersections(){
  return intersections; 
 }


  public Iterable<DirectedEdge> edges() {
      //it iterates over alle edges in the graph an puts it in the new list and returns it
  List<DirectedEdge> list = new ArrayList<DirectedEdge>();
  for (int v = 0; v < V; v++) {
      for (DirectedEdge e : adj(v)) {
          list.add(e);
      }
  }
  return list;
}  

/*public double getDistance(Node node1, Node node2){

  double distance = Math.sqrt(Math.pow((node2.getX() - node1.getX()), 2)   + Math.pow((node2.getY() - node1.getY()), 2));

  //System.out.println(distance);

  return distance; 


}*/
    
}
