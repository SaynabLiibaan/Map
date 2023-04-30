package handin2.ViewPart;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

=======
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
import handin2.ModelPart.Model;
import handin2.ModelPart.Node;
import handin2.ModelPart.Way;
import handin2.ModelPart.Dijkstra.DijkstraSP;
import handin2.ModelPart.Tree.KdTree;

import handin2.ModelPart.Tree.Rectangle;

import javafx.geometry.Point2D;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
<<<<<<< HEAD
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
=======
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.stage.Stage;




public class View {
<<<<<<< HEAD

    Queue<Node> nodes2Draw;

    public Canvas canvas;
    GraphicsContext gc;
    
    public Scene scene;
   
    public Pane pane;

    public Affine trans = new Affine();


    Model model;
   
    KdTree buildingTree;
    KdTree roadTree;
    Queue<Way> mapElementsInRect;
   
    public Label label;
    public Label zoomlabel;

    public Label labelBuildings;

    
=======
    public Canvas canvas;// = new Canvas(640, 480);
    public GraphicsContext gc;// = canvas.getGraphicsContext2D();
    double x1 = 100;
    double y1 = 100;
    double x2 = 200;
    double y2 = 800;

    Affine trans = new Affine(); //this package can do things like scale, rotate, translate...

    Model model;
    public MainScene mainScene;
    Scene scene;

    public Text factor;
    public TextField searchField;
    public GridPane grid;
    public Button closeButton;
    public Button dayButton;
    public Button nightButton;
    public Pane pane;
    public Color currentColor;
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201

    public View(Model model, Stage primaryStage) {
        this.model = model;
        primaryStage.setTitle("Draw Lines");
        pane = new Pane();
<<<<<<< HEAD

        scene = new Scene(pane, 800,600); //740, 580
    
        canvas = new Canvas(scene.getWidth(), scene.getHeight());
        pane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        StackPane stackpane = new StackPane(canvas);//, rectangle);
        
        label = new Label("x: y:");

        pane.getChildren().add(stackpane);
        pane.getChildren().add(label);
        
        
        
=======
        mainScene = new MainScene();

        scene = new Scene(pane, 800,600); 

        canvas = new Canvas(scene.getWidth(), scene.getHeight());
        pane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        //gc.setFill(Color.TRANSPARENT);
        gc.setFill(currentColor);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double f = canvas.getHeight() / (model.maxlat - model.minlat);
        String fa = String.valueOf(f);
        factor = new Text(fa);
        factor.setX(100);
        factor.setY(50);

        StackPane stackpane = new StackPane(canvas);

        pane.getChildren().add(stackpane);
        pane.getChildren().add(mainScene);

>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
        primaryStage.setScene(scene);
        primaryStage.show();

        
        buildingTree = new KdTree(model);
        for(Way building : model.buildings){
            buildingTree.insert(building);
        }
        roadTree = new KdTree(model);
        for(Way road : model.roads){
            roadTree.insert(road);
        }


 
        redraw();
<<<<<<< HEAD
        
        /*We want pan to do that the upper left matches with 0.0, and then we will zoom with 0.0 to make it fit.
         * It means when we start the application, what we will see will the map and we dont have to zoom in on it
         */
        pan(-0.56*model.minlon, model.maxlat); // //Takes the difference of the mouse position from pressed to after dragged
        //(-7.04088, 55.6804000)
        //smallest x-value, max y-value
        //-0.56 is a possible scaling factor for x-value
        
        zoom(0, 0, canvas.getHeight() / (model.maxlat - model.minlat)); //Take the difference, and a factor
        //(0,0, 580 / (55.6804000 - 55.6631000)) = 580 / 0,0173 = 33,526.0116
        //the zoom will start at point 0,0 (upper left), with a factor of the canvas height / delta latitude.
        //Since we scale with negative factor the affine() will flip everything
=======
        /*We want pan to do that the upper left matches with 0.0, and then we will zoom with 0.0 to make it fit  */
        pan(-0.56*model.minlon, model.maxlat);
        zoom(0, 0, canvas.getHeight() / (model.maxlat - model.minlat)); //we start zooming by the coordinate 0,0 and then the factor

>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
    }


    public void redraw() {
<<<<<<< HEAD

        /* Method for setting the background */
        setBackground();
=======
        gc.setTransform(new Affine()); //start from scratch when we move the mouse
        //gc.setFill(Color.LIGHTBLUE); 
        //we do next line such that we forget the previous drawn canvas
        
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); //this method refills the wholde canvas
        gc.setTransform(trans); //we need to write this line, such that the panning method works, because these two methods need to communicate
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201

        /*Because the stroke is way to big we have to take the square root of the determinant of our transform
        and invert it by dividing it with 1*/
        gc.setLineWidth(1/Math.sqrt(trans.determinant()));


        Rectangle screenRect = new Rectangle(mousetoModel(canvas.getWidth()-750,canvas.getHeight()-550), mousetoModel(canvas.getWidth()-50, canvas.getHeight()-50));
        //Color.rgb(255, 192, 203, 0.5)
        gc.setFill(Color.rgb(255, 192, 203, 0.5));
        gc.fillRect(screenRect.xmin(),screenRect.ymin(),screenRect.width(),screenRect.height());
        
        // Now we retrieve all buildings:
        Queue<Way> retrievedBuildings = buildingTree.range(screenRect);
        for (Way building : retrievedBuildings) {
            building.draw(gc);    
        }

        Queue<Way> retrievedRoads = roadTree.range(screenRect);
        for(Way road : retrievedRoads) {
            road.draw(gc);
        }
        /*for(var line : model.lines) {
            line.draw(gc);
            
        }
        //we draw all the objects stored in the lists on the model class
          
        for(var area : model.areas) {
<<<<<<< HEAD
                area.draw(gc);
        }
        for(var road : model.roads) {
            road.draw(gc);
        }*/
        /*for (var building : model.buildings) {
            building.draw(gc);
            
        }*/
        /*for(var coast : model.coastlines) {
            coast.draw(gc);
            
        }
        for(var water : model.waters) {
            water.draw(gc);
        }*/
    }

    public void setBackground(){
        gc.setTransform(new Affine());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setTransform(trans);
=======
            area.draw(gc, Color.DARKSEAGREEN, Color.LIGHTGRAY, Color.PALEGOLDENROD);
        }
        for(var road : model.roads) {
            road.draw(gc, Color.GREY);
        }
        for (var building : model.buildings) {
            building.draw(gc, Color.DARKGRAY);
        }
        for(var coast : model.coastlines) {
            coast.draw(gc, Color.LIGHTBLUE);
        }
        for(var water : model.waters) {
            water.draw(gc, Color.LIGHTBLUE);
        }
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
    }
   

<<<<<<< HEAD
    //Takes the difference of the mouse position from pressed to after dragged
    public void pan(double dx, double dy) {
        // Base function
        trans.prependTranslation(dx, dy);
=======
    //this method contains panning, that means moving around, and we need to know how much we move along x and y
    public void pan(double dx, double dy) {
        //this operation translates the currently drawn picture by the amount we specify in dx and dy
        trans.prependTranslation(dx, dy); 
        //then we redraw
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
        redraw();
    } // pan(..)


    /*zooming means zooming in and out, where we need a zooming factor, which says how much we are zooming in
     *or out, and x and y coordinate such that we scale down in the right region of the map
    */
    public void zoom(double dx, double dy, double factor) {
        pan(-dx, -dy);
        trans.prependScale(factor, factor); 
        pan(dx, dy);
<<<<<<< HEAD
        redraw();
        

    }

    /* Transforms the mouse coordinates on the screen into a Point2D object
     * lastX is from e.getX of the mouse
     * lastY is from e.getY of the mouse
     */
=======
        //then we redraw
        redraw(); 
    }
    
>>>>>>> 00cdf1ee17689fc5aeb3e68113cba917425dd201
    public Point2D mousetoModel(double lastX, double lastY) {
        try {
            return trans.inverseTransform(lastX, lastY);
            
        } catch (NonInvertibleTransformException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    public GraphicsContext getGC(){
        return gc;
    }


   

    public double convert2lat(double valueY, double maxlat, double minlat, double height){
        double lat = maxlat - (valueY / height) * (maxlat - minlat);
        return lat;
    }
    public double convert2lon(double valueX, double maxlon, double minlon, double width){
        double lon = maxlon - (valueX / width) * (maxlon - minlon);
        return lon;
    }

    public double convert2x(double longitude, double maxlon, double minlon, double width){
        double x = (longitude - minlon) * width / (maxlon - minlon);
        return x;
    }
    public double convert2y(double latitude, double maxlat, double minlat, double height){
        double y = (maxlat - latitude) * height / (maxlat - minlat);
        return y;
    }

}