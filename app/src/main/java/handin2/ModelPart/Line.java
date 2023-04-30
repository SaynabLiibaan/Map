package handin2.ModelPart;

import java.io.Serializable;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Line implements Serializable {
    /*Line has the purpose as to draw lines if we work with anythin else but an osm file*/
    double x1, y1, x2, y2;

    public Line(String line) {
        /*It takes the text file an split in to 4, so we can get the coordinates of the file 
         * So we have a start point and ending part
         */
        String[] coord = line.split(" ");
        x1 = Double.parseDouble(coord[1]);
        y1 = Double.parseDouble(coord[2]);
        x2 = Double.parseDouble(coord[3]);
        y2 = Double.parseDouble(coord[4]);
    }

    public Line(Point2D p1, Point2D p2) {
        /*This is if u want to draw somehting for ur self in the map (kinda irrelevant) */
        x1 = p1.getX();
        y1 = p1.getY();
        x2 = p2.getX();
        y2 = p2.getY();
        System.out.println(x1);
        System.out.println(y1);
    }

    public void draw(GraphicsContext gc) {
        /*This mehtod is for actually drawing the lines from the file in to the map, where x1,y1 is the start 
         and thats why we use moveTo after beginPath, and lineTo is the ending point where x2, y2 are the ending point
         */
        gc.beginPath();
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.stroke();
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }
    
    public double getY2() {
        return y2;
    }
}