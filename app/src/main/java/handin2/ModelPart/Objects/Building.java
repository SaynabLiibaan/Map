package handin2.ModelPart.Objects;

//import java.io.Serializable;
import java.util.ArrayList;
//import java.util.List;

import handin2.ModelPart.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Building extends Way{
    String name;

    public Building(ArrayList<Node> way, Long id, String name) {
        super(way, id);
        this.name = name;
    }

    public void draw(GraphicsContext gc, Color color) {
    super.draw(gc);
    gc.setFill(color);
    gc.fill();//Husk fill kaldet efter setFill.
    gc.setStroke(color); //Virker pt ikke, fordi alt bliver considered Road == roads farve vises.//UPDATE WORKS
    gc.stroke();
    }

    public String getName(){
        return name;
    }
}