package handin2.ModelPart.Tree;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Queue;

import handin2.ModelPart.Model;
import handin2.ModelPart.Node;
import handin2.ModelPart.Way;



public class KdTree {

    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public TNode root; //Median af data/nodes
    private int size; // Antal nodes i kd-træet.

    public ArrayList<Node> nodes;

    private class TNode implements Serializable{ //Tree-node klasse inden i kd-tree. 
        //public Node coord;
        public Rectangle rect;
        public TNode left;
        public TNode right;

        private Way mapElement;

        private boolean position; // horizontal or vertical
        

        TNode(Way mapElement, Rectangle rect){
            //this.coord = coord;
            this.mapElement = mapElement;
            this.rect = rect;
            left = null;
            right = null;
        }
        public double getX(){
            return mapElement.getX();
        }
        public double getY(){
            return mapElement.getY();
        }
        public void showRect(){
            System.out.println("xmin: " + this.rect.xmin() + " ymin: " + this.rect.ymin() +
                                " xmax: " + this.rect.xmax() + " ymax: " + this.rect.ymax());
        }
    } // end of TNode class.
    
    public KdTree(Model model){//Tree constructor
        size = 0;
        xMax = 0.56 * model.maxlon;
        xMin = 0.56 * model.minlon;
        yMax = -model.maxlat;
        yMin = -model.minlat;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }


    /* PUBLIC insert only needs a node */
    public void insert(Way mapElement){
        root = insert(root, mapElement,xMin,yMin,xMax,yMax,0);
    }

    /* PRIVATE insert, is what happens when the public is used. 
     * Everything that is returned in this method, is given to the public insert.
    */
    private TNode insert(TNode tnode, Way mapElement, double xmin, double ymin,
                        double xmax, double ymax, int depth){
        if(tnode == null){
            size++;
            return new TNode(mapElement, new Rectangle(xmin,ymin,xmax,ymax));
        }
        if(tnode.mapElement.equals(mapElement)){
            return tnode;
        }
        
        double compare = compareMapElements(mapElement, tnode.mapElement, depth);

        if(compare < 0){ //If coord is less than tnode.coord, then node coord should be to the left of tnode.
            if(depth % 2 == 0) { //If depth is even
                tnode.left = insert(tnode.left, mapElement, xmin, ymin,
                                     tnode.mapElement.getX(),ymax, depth +1);
                //Create a rectangle that is made with the coordinates of the tnode.
                
            } else {    //If isHorizontal
                tnode.left = insert(tnode.left, mapElement, xmin, ymax,
                                     xmax, tnode.mapElement.getY(), depth + 1);
                
            }
        } else if (compare > 0){ //If coord is bigger than 
            if( depth % 2 == 0 ) { // if depth is even
                tnode.right = insert(tnode.right, mapElement, tnode.mapElement.getX(), ymin,
                                     xmax, ymax, depth +1);
                
            } else {
                tnode.right = insert(tnode.right, mapElement, xmin, tnode.mapElement.getY(),
                                     xmax, ymax, depth +1);
                
            }
        }

        return tnode;
    }


    private int compareMapElements(Way a, Way b, int depth) {
        if (depth % 2 == 0) { //Compares x-coordinates of even depth
            Double ax = a.getX();
            Double bx = b.getX();
            int result = ax.compareTo(bx); //result > 0 if ax greater,
            
            if(result == 0){//If ax and yx are equal
                Double ay = a.getY();
                Double by = b.getY();
                return ay.compareTo(by); // compare y-values
            } else {
                return result; //Return result if x-values are different.
            }
        } else { //Compares y-coordinates of uneven depth
            Double ay = a.getY();
            Double by = b.getY();
            int result = ay.compareTo(by); //result > 0 if ay greater.

            if(result == 0) {//if ay and by are equal
                Double ax = a.getX();
                Double bx = b.getX();
                return ax.compareTo(bx); //compares x-values
            } else {
                return result; // return int result of y-values
            }
        }

    }

    /*private Node contains(TNode tnode, Node coord, int depth) {
        while(tnode != null) {
            int compare = compareMapElements(coord, tnode.coord, depth);
            
            if(compare < 0) { //If coord is less than tnode.coord
                return contains(tnode.left, coord, depth + 1); //Check left side
            } else if (compare > 0) { //If coord is bigger than tnode.coord
                return contains(tnode.right, coord, depth + 1); // check right side
            } else {
                return tnode.coord; // return the tnode if new node coord is equal = no new.
            }
        }

        return null; // if none above holds, return null.
    }*/

    /* PUBLIC range method */
    public Queue<Way> range(Rectangle rect) {
        if (rect == null) {
            throw new IllegalArgumentException("rect is null");
        }
        
        Queue<Way> queue = new LinkedList<Way>();
        range(root, rect, queue);
        return queue;
    }

    /* PRIVATE range method */
    private void range(TNode tnode, Rectangle rect, Queue<Way> queue){
        if (tnode != null && rect.intersects(tnode.rect)) { //if tnode's rect is apart of query Rect
            if( rect.contains(tnode.mapElement.getX(), tnode.mapElement.getY())) {
                queue.add(tnode.mapElement);
            }

            range(tnode.left, rect, queue);
            range(tnode.right, rect, queue);
            
       }
    }

    /* PUBLIC nearest method */
    /*public Node nearest(Node coord) {
        if(coord == null){
            throw new IllegalArgumentException("nearest coord is null");
        }
        if(isEmpty()) {
            return null;
        } else {
            Node result = null;
            result = nearest(root, coord, result);
        return result;
        }
    }

    private Way nearest(TNode tnode, Node queryCoord, Node closeCoord){
        if(tnode != null) {
            if (closeCoord == null) {
                closeCoord = tnode.mapElement;
            }
            if(tnode.mapElement.distanceSquaredTo(queryCoord) < closeCoord.distanceSquaredTo(queryCoord)) {
                closeCoord = tnode.mapElement;
            
            if (tnode.right != null && tnode.right.rect.contains(new Point2D.Double(queryCoord.getX(), queryCoord.getY()))){
                closeCoord = nearest(tnode.right, queryCoord, closeCoord);
                closeCoord = nearest(tnode.left, queryCoord, closeCoord);
            } else {
                closeCoord = nearest(tnode.left, queryCoord, closeCoord);
                closeCoord = nearest(tnode.right, queryCoord, closeCoord);
            }
            
            }
    
        }
        return closeCoord;
    }*/
}


