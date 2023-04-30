package handin2.ControllerPart;

import handin2.ModelPart.Model;
import handin2.ViewPart.MainScene;
import handin2.ViewPart.View;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

public class MainSceneController {
    double lastX;
    double lastY;
    MainScene front;
    Model model;
    View view;


    public MainSceneController(MainScene front, Model model, View view) {

        //Show which button is clicked and handle event
        front.lightBtn.setOnAction((ActionEvent e) -> {
            front.handleBtnClick(front.lightBtn);
            view.currentColor = Color.LIGHTBLUE;
            //view.gc.setFill(Color.LIGHTBLUE); 
            //view.gc.fillRect(0, 0, view.canvas.getWidth(), view.canvas.getHeight());
  
            
            for(var area : model.areas) {
                area.draw(view.gc, Color.DARKSEAGREEN, Color.LIGHTGRAY, Color.PALEGOLDENROD);
            }
            for(var road : model.roads) {
                road.draw(view.gc, Color.GREY);
            }
            for (var building : model.buildings) {
                building.draw(view.gc, Color.DARKGRAY);
            }
            for(var coast : model.coastlines) {
                coast.draw(view.gc, Color.LIGHTBLUE);
            }
            for(var water : model.waters) {
                water.draw(view.gc, Color.LIGHTBLUE);
            }
        });

        front.darkBtn.setOnAction((ActionEvent e) -> {
            front.handleBtnClick(front.darkBtn);
            view.currentColor = Color.MIDNIGHTBLUE;

            //view.gc.setFill(Color.MIDNIGHTBLUE); 
            //view.gc.fillRect(0, 0, view.canvas.getWidth(), view.canvas.getHeight());
            
            for(var area : model.areas) {
                area.draw(view.gc, Color.DARKGREEN, Color.DIMGREY, Color.DARKSLATEGREY);
            }
            for(var road : model.roads) {
                road.draw(view.gc, Color.GREY);
            }
            for (var building : model.buildings) {
                building.draw(view.gc, Color.BLACK);
            }
            for(var coast : model.coastlines) {
                coast.draw(view.gc, Color.MIDNIGHTBLUE);
            }
            for(var water : model.waters) {
                water.draw(view.gc, Color.MIDNIGHTBLUE);
            }
        });

        front.sparkBtn.setOnAction((ActionEvent e) -> {
            front.handleBtnClick(front.sparkBtn);
            view.currentColor = Color.LAVENDERBLUSH;
            for(var area : model.areas) {
                area.draw(view.gc, Color.LIGHTCORAL, Color.LIGHTGRAY, Color.LIGHTPINK);
            }
            for(var road : model.roads) {
                road.draw(view.gc, Color.GREY);
            }
            for (var building : model.buildings) {
                building.draw(view.gc, Color.PINK);
            }
            for(var coast : model.coastlines) {
                coast.draw(view.gc, Color.LAVENDERBLUSH);
            }
            for(var water : model.waters) {
                water.draw(view.gc, Color.LAVENDERBLUSH);
            }
        });

        //add actionlistener to textfield that creates a dropdown box matching the search
        front.searchfield.textProperty().addListener((observable, oldValue, newValue) -> {

            if(front.grid.getChildren().size()>1){ // if already contains a drop-down menu -> remove it 
                front.grid.getChildren().remove(1);
            }

            front.grid.add(front.dropDown(newValue, model.tree),0,1); // add the  drop-down menu to the second row in the grid pane with TST
        });

        
        
        
    }
}