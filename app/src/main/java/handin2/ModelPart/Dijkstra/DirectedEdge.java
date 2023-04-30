package handin2.ModelPart.Dijkstra;

import java.io.Serializable;

import handin2.ModelPart.Road;

public class DirectedEdge implements Serializable{
    private  int v; // edge source aka first vertex to
    private  int w; // edge target aka ending vertex from
    private Road road; // edge weight 
   // private double speed; // to keep an eye when we use highways and when we use somehting else
  //  private String type;  // when we use roads/highways, bicycle paths, and paths (walking)
    
    
 
     public DirectedEdge(int v, int w, Road road) {
       this.v=v;
       this.w=w; 
       this.road = road; 
      
   
 }
 
     public Road road(){
        return road; 
     }
 
    
     public int from(){
       return v;  // you can write v = e.from(), to represent v in a graph
 
    }
 
     public int to(){
       return w;  // the same here u can write w = e.to(), to represent w in a graph
    }
     public String toString(){ 
       return String.format("%d->%d %.2f", v, w, road); 
    }
 
 
 /*
      public double length(){
 
        something to find out what the lenght between v to w is, idk yet how to code it
 
      }
 
 
 
      public double speedLimit(){
        Somehting that keeps an eye on the speedLimit
      }
 
 
      */
    
}
