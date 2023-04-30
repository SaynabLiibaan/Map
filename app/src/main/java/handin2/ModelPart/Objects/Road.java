package handin2.ModelPart.Objects;

//import java.io.Serializable;
import java.util.ArrayList;

import handin2.ModelPart.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Road extends Way{

    private double weight = 1.0;
    

    public Road(ArrayList<Node> way, Long id) {
        super(way, id);
    }

   public void draw(GraphicsContext gc, Color color) {
    super.draw(gc);
    //gc.setFill(Color.LIGHTGRAY);
    //gc.fill();
    gc.setStroke(color);
    gc.stroke();
    }

    public void drawPath(GraphicsContext gc){
        super.draw(gc); 
        gc.setStroke(Color.GREEN);
        gc.stroke();
    }

    public double weight(){
        return weight;
    }

    public double getDistance(){

        double distance = Math.sqrt(Math.pow((last().getX() - first().getX()), 2)   + Math.pow((last().getY() - first().getY()), 2));
      
       // System.out.println( "The distance is: " + distance);
       return distance; 
      
      
      }

   
}