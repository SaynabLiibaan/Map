package handin2.ModelPart;


import java.io.Serializable;

public class Node implements Serializable{
    /*Node it representing a node (point), where longtitudes and lattitudes helps us identify absolute, 
    or exact, locations on the Earth's surface */
<<<<<<< HEAD
    public double lat, lon;
=======
    public double lat;
    public double lon;
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
    Long id;
    public int index; 

    
    public Node(double lat, double lon, Long id) {
        /*Latitudes equals y-axis */
        this.lat = lat;

        /*Longtitudes equals x-axis */
        this.lon = lon; 

        this.id = id;
    }

    public Node(double lat, double lon){
        this.lat = lat; 
        this.lon = lon; 

    }

    public double distanceSquaredTo(Node node) {
        double dx = lon - node.lon;
        double dy = lat - node.lat;
        return dx * dx + dy * dy;
    }

    public double getX(){
        return lon;
    }

    public double getY(){
        return lat;
    }

    public Long getId(){
        return id;
    }

    public String toString(){
        return Double.toString(lat) + " " + Double.toString(lon) + " " + Long.toString(id);
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