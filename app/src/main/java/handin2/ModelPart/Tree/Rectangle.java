package handin2.ModelPart.Tree;

import javafx.geometry.Point2D;

public class Rectangle {
    private double xmin, ymin;
    private double xmax, ymax;
    
    public Rectangle(Point2D min, Point2D max){
        this(min.getX(),min.getY(),max.getX(),max.getY());
    }

    public Rectangle(double xmin, double ymin, double xmax, double ymax){
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;    
    } 

    public double xmin() {
        return xmin;
    }

    public double xmax() {
        return xmax;
    }

    public double ymin() {
        return ymin;
    }

    public double ymax() {
        return ymax;
    }

    public double width() {
        return xmax - xmin;
    }

    public double height() {
        return ymax - ymin;
    }

    public boolean intersects(Rectangle rect) {
        return this.xmax >= rect.xmin || this.ymax >= rect.ymin
                && rect.xmax >= this.xmin || rect.ymax >= this.ymin;
    }

    public boolean contains(double x, double y) {
        return (x >= xmin) && (x <= xmax) 
                && (y >= ymin) && (y <= ymax);
    }

    public double distanceTo(Point2D point) {
        return Math.sqrt(this.distanceSquaredTo(point));
    }

    public double distanceSquaredTo(Point2D point) {
        double dx = 0.0;
        double dy = 0.0;
        if (point.getX() < xmin) {
            dx = point.getX() - xmin;
        } else if (point.getX() > xmax) {
            dx = point.getX() - xmax;
        }
        if (point.getY() < ymin) {
            dy = point.getY() - ymin; 
        } else if (point.getY() > ymax) {
            dy = point.getY() - ymax;
        }
        return dx * dx + dy * dy; 
    }

    public String toString() {
        return "[" + xmin + ", " + xmax + "] x [" + ymin + ", " + ymax + "]";
    }
}
