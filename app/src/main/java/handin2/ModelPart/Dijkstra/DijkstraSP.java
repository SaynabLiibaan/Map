package handin2.ModelPart.Dijkstra;

import java.util.ArrayList;
import java.util.List;

import handin2.ModelPart.Road;

public class DijkstraSP {
    private double[] distTo; //distTo[v] keeps an eye of the shortest path from s to v
    private DirectedEdge[] edgeTo;  //edgeTo[v] keeps an eye on last edge of the shortest path from s to v
    private PriorityQueue<Double> pq; // priority queue of vertices
    //private PriorityQueue<Integer> pq;

    
    //G is the edge weighted graph and s is the source aka starting point
    public DijkstraSP(Graph G, int s){
        
        //if e is less than 0, then we throw an Exception, so it does not ruin the graph
        for(DirectedEdge e : G.edges()){
            //System.out.println(e.road());
         if(e.road().getDistance() < 0){
           
            throw new IllegalArgumentException("unfortunately e " + e + " has a negativ weight");
         }
        }


        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];


        validateVertex(s);

        /*We are iterating over all the vertives and give the infinity to begin with and s is 0.0 which makes sense, since the starting
         * point always is 0.0 cause distance from s to s is 0.0
         */
        for (int v = 0; v < G.V(); v++){
            
            distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;

            //int v1 = v; 
          //  int v2 = v+1; 

             // relax vertices in order of distance from s
       
           pq = new PriorityQueue<Double>(G.V());
          // pq = new PriorityQueue<Integer>(Double.compare(distTo[v1], distTo[v2]));

           pq.insert(s, distTo[s]);

          System.out.println("Is the pq empty?: " +  pq.isEmpty());

        while (!pq.isEmpty()) {
            int j = pq.delMin();
            // System.out.println("All the v's: " + v);
            for (DirectedEdge e : G.adj(j))
                relax(e);
         

        }
             
           


                
        } 



    }

   
    /*This method is Edge relaxation. To relax an edge v->w means to test whether the best known way from s to w is to go 
    from s to v, then take the edge from v to w, and, if so, update our data structures. And find the shortest path and then update our 
    data/vertices/edge */

    private void relax(DirectedEdge e) {
        //So we have our v = e.from() and w = e.to() aka our two vertices
        int v = e.from(), w = e.to();
        
        if (distTo[w] > distTo[v] + e.road().getDistance()) {
            distTo[w] = distTo[v] + e.road().getDistance();
            edgeTo[w] = e;

          
             if (pq.contains(w)){
                //if the value is already in our priority queue, then we decrease it aka, 
                //After we locate its index, we’ll change its value, and start moving it up the tree if needed

              pq.decreaseKey(w, distTo[w]);

           } else {

            pq.insert(w, distTo[w]);

           }  
        }
    }

    //find en måde i en metode hvor jeg kan få fat alle directd edges og dernæst kalde drawPath()

    /*Returns the length of a shortest path from the source vertex s to vertex v */
    public double distTo(int node) {
        validateVertex(node);
        return distTo[node];
    }


 
     /*Returns true if there is a path from the source vertex s to vertex v */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        System.out.println("the vertex v is: " + distTo[v]);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

      public Iterable<Long> getShortestPathEdges(int node) {
        validateVertex(node);
    
        List<Long> pathEdges = new ArrayList<>();

        DirectedEdge edge = edgeTo[node];
     
        //System.out.println(theedge); 

        while (edge != null) {
            pathEdges.add(edge.road().getId());

            edge = edgeTo[edge.from()];
        }
    
        return pathEdges;
    } 

    /*To check if our vertex is valid aka if it is less than 0 and greater tha  */
    private void validateVertex(int node) {
        int V = distTo.length;
        if (node < 0 || node >= V)
            throw new IllegalArgumentException("vertex " + node + " is not between 0 and " + (V-1));
    }
    
}
