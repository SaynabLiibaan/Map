package handin2.ModelPart.Objects;

import java.util.ArrayList;

import handin2.ModelPart.Node;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Area extends Way {
    String type;

    public Area(ArrayList<Node> way, String type, Long id) {
        super(way, id);
        this.type = type;
    }

    public void draw(GraphicsContext gc, Color forest, Color residential, Color farmland) {
        super.draw(gc);

        if (type == "forest"){
            gc.setFill(forest);
            gc.setStroke(forest); 
        }  else if (type == "residential"){
            gc.setFill(residential);
            gc.setStroke(residential);
        }   else if (type == "farmland") {
            gc.setFill(farmland);
            gc.setStroke(farmland);
        }   /*else if (type == "beach") {
            gc.setFill(Color.BISQUE);
            gc.setStroke(Color.BISQUE);
        }*/
        gc.fill();
        gc.stroke();
    }
}