package handin2.ControllerPart;

import handin2.ModelPart.Model;
import handin2.ModelPart.Node;

import handin2.ModelPart.Tree.Rectangle;
import handin2.ViewPart.View;
<<<<<<< HEAD
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.transform.Affine;
=======
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201

public class Controller {
    double lastX;
    double lastY;
    double width;
    double height;
    private int count;


    public Controller(Model model, View view) {
<<<<<<< HEAD
        //view.rectangle.setMouseTransparent(true);
        // Behavior: on mouse pressed
        
        view.canvas.setOnMousePressed(e -> {
            lastX = e.getX();
            lastY = e.getY();



            if(e.getClickCount() == 2){
                System.out.println("!!!!!  TEESSSST    !!!");
                count = count + 1;//count tælles op med 1 når der dobbelklikkes.
                if(count%2 == 1){
                  
                    System.out.println("Dijkstra");
                } else {//lige klik
                    System.out.println("Ikke dijkstra");
                }
            }
            
        });

        view.pane.setOnMouseMoved(e -> {
            String labelText = "x=" + e.getX() + "; y=" + e.getY();
            view.label.setText(labelText);
        });

        // Behavior : on mouse dragged (=> everytime you move on the map)
        view.pane.setOnMouseDragged(e -> {
            //view.rectangle.setMouseTransparent(true);
            if (e.isPrimaryButtonDown()) {
                // Get the differential coordinates
                double dx = e.getX() - lastX;
                double dy = e.getY() - lastY;

                //view.redraw();
                // Make the view move long the mouse's move
                view.pan(dx, dy);
            }

            lastX = e.getX();
            lastY = e.getY();
            
           
        });

        // Behavior: on scroll
        view.pane.setOnScroll(e -> {

            double factor = e.getDeltaY();//Number of pixels you can scroll vertically
            view.zoom(e.getX(), e.getY(), Math.pow(1.01, factor));//1.01^factor

        });


    }
    
=======
        //when the mouse is pressed, we create variables, that remember where the mouse is at the moment
        view.mainScene.setOnMousePressed(e -> {
            lastX = e.getX(); //e.getX gets us where the mouse is currently (at x)
            lastY = e.getY(); //e.getY gets us where the mouse is currently (at y)
        });

        // we say, that if the mouse is dragged, we need to move what's drawn on the canvas using the pan-method in view
        view.pane.setOnMouseDragged(e -> {
            if (e.isPrimaryButtonDown()) {
                double dx = e.getX() - lastX; //set dx to how much the mouse is dragged (at x)
                double dy = e.getY() - lastY; //set dy to how much the mouse is dragged (at y)
                view.pan(dx, dy); //then we pan how much we want to move at x and y
            } 

            lastX = e.getX(); //then we set the current variable of where the mouse is, to the new place the mouse is (at x)
            lastY = e.getY(); //then we set the current variable of where the mouse is, to the new place the mouse is (at y)
        });

        //we want to zoom based on how much we scroll
        view.pane.setOnScroll(e -> {
            double factor = e.getDeltaY(); //we read a factor, which returns the vertical scrolling amount
            /*so we zoom at the current coordinates, and because the mouse is very sensitive, 
            we use the pow. operation, which makes the function grow slower*/
            String f = String.valueOf(Math.pow(1.01, factor));
            view.factor.setText(f);
            view.zoom(e.getX(), e.getY(), Math.pow(1.01, factor)); 
        });   
    }
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
}
