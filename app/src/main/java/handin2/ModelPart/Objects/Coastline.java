package handin2.ModelPart.Objects;

//import java.io.Serializable;
import java.util.ArrayList;
//import java.util.List;

import handin2.ModelPart.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Coastline extends Way{
    public Coastline(ArrayList<Node> way, Long id) {
        super(way, id);
    }

   public void draw(GraphicsContext gc, Color color) {
    super.draw(gc);
    //gc.setFill(Color.DEEPSKYBLUE);
    //gc.fill();//Husk fill kaldet efter setFill.
    gc.setStroke(color); //Virker pt ikke, fordi alt bliver considered Road == roads farve vises.//UPDATE WORKS
    gc.stroke();
    }  
}