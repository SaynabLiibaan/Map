package handin2.ModelPart.Objects;

import java.io.Serializable;
import java.util.ArrayList;

import handin2.ModelPart.Node;
import javafx.scene.canvas.GraphicsContext;

public class Way implements Serializable {
    /*Our class road has the purpose to create and draw roads */
    double[] coords; // array of primitive values
    Long id;
    public int index; 
    ArrayList<Node> way;
    Node firstNode;
    Node lastNode;
    

   //maybe change double to float (said the teacher)

    public Way(ArrayList<Node> way, Long id) {
        this.id = id;
        //this.way = way;
        this.way = new ArrayList<>();
        if(way.size() > 0){
            firstNode = way.get(0);
            lastNode = way.get(way.size()-1);    
        }
        
        /*way.size() * 2 means that it contains twice as many entrys. Exact same order in the format
         so x1, y1, x2, y2 etc. Alternating entries*/
        coords = new double[way.size() * 2];

        for (int i = 0 ; i < way.size() ; ++i) {
            var node = way.get(i);//Way consists of nodes

            /*Even coordinates we have longtitudes */
            coords[2 * i] = 0.56 * node.lon; //0, 2, 4, 6, 8,
            //If this is node.lat, the map will be flipped 90 degrees
            /*Actually the reason why we multiply with 0.56 is cause the lon is twice as large, and we wanna
              sqiush it down, so the lat and lon is roughly the same */

            /*Odd coordinates we have latitude */
            coords[2 * i + 1] = -node.lat; //1, 3, 5, 7, 9,
            /* We are writing in screen coordinates which means up in the left side is 0.0. So y-coordinates goes 
            down and y-coordinates go up from the equator. Thats why we write - aka minus in front of
            */
        }
    }

    public void draw(GraphicsContext gc) {
        /* When we draw it is like in lines, but this time with our array, where the line begins at coords 0(x1)
         and coords 1(y1), and we iterate over the coordinates where coords i is x2 and coords i+2 is y2 */
        gc.beginPath();
        if(coords.length <= 0){ //Handles the possible exception below. !!Maybe use exception handling instead of if.
            return;
        }
        gc.moveTo(coords[0], coords[1]); //This will throw an exception if coords length is 0.
        //Is there a case where an empty coords[] is empty? 
        for (int i = 2 ; i < coords.length ; i += 2) {//Start on the third entry
            gc.lineTo(coords[i], coords[i+1]);
        }
        
    }

    public Long getId(){
        return id;
    }

    public ArrayList<Node> getWay() {
        return way;
    }

    public double[] getCoords() {
        return coords;
    }

    public Node first(){
        return firstNode;
    }

    public Node last(){
        return lastNode;
     }

     
     

    public double getX(){
        // Temporary check for 0 length ways:
        if(coords.length == 0)
            return 0;
        return coords[0];
    }
    
    public double getY(){
        // Temporary check for 0 length ways:
        if(coords.length == 0)
            return 0;
        return coords[1];
    }

    public Node centernode(){
        double latSum = 0.0;
        double lonSum = 0.0; 
        int n = way.size(); 

        for(Node node : way){
            latSum = latSum + node.lat; 
            lonSum = lonSum + node.lon; 
        }

        return new Node(latSum/n, lonSum/n, 0L); 
    }

    public void setIndex(int index, int arrayLength) {
        if (index < arrayLength) {
            this.index = index;
        } else {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    public int index() {
        return index;
    }

    
}