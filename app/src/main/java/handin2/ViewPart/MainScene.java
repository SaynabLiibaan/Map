package handin2.ViewPart;

import java.net.URL;

import handin2.ModelPart.TernarySearchTree.TernaryTree;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainScene extends AnchorPane{
    public Button lightBtn;
    public Button darkBtn;
    public Button sparkBtn;

    public GridPane grid;
    public TextField searchfield;
    public Button searchBtn;

    public TextField locationfield;
    public TextField destinationfield;

    public Button walkBtn;
    public Button bikeBtn;
    public Button carBtn;

    public Button prevClickedBtn;

    public MainScene() {
        BorderPane mainPane= new BorderPane();
        try{
            URL cssUrl = getClass().getResource("/styles.css");
            if (cssUrl == null) {
                throw new RuntimeException("Could not find styles.css");
            }
            String css = cssUrl.toExternalForm();
            getStylesheets().add(css);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Components for the top of BorderPane
        HBox topHBox = new HBox();
        topHBox.setPrefSize(800,28);
        topHBox.setMaxHeight(28);

        Pane leftPane = new Pane();
        leftPane.getStyleClass().add("transparent");
        leftPane.setPrefWidth(250);
        leftPane.setMaxHeight(28);
        topHBox.getChildren().add(leftPane);

        ImageView light = new ImageView("/images/sun.png");
        light.setFitWidth(20);
        light.setFitHeight(20);
        ImageView dark = new ImageView("/images/moon.png");
        dark.setFitWidth(20);
        dark.setFitHeight(20);
        ImageView spark = new ImageView("/images/shine.png");
        spark.setFitWidth(20);
        spark.setFitHeight(20);

        lightBtn = new Button(" ", light);
        darkBtn = new Button(" ", dark);
        sparkBtn = new Button(" ", spark);
        lightBtn.getStyleClass().add("icons");
        darkBtn.getStyleClass().add("icons");
        sparkBtn.getStyleClass().add("icons");
        lightBtn.setPrefSize(40, 40);
        darkBtn.setPrefSize(40, 40);
        sparkBtn.setPrefSize(40, 40);
        
        topHBox.getChildren().addAll(lightBtn, darkBtn, sparkBtn);
        HBox.setMargin(lightBtn, new Insets(25, 15, 0, 15));
        HBox.setMargin(darkBtn, new Insets(25, 15, 0, 15));
        HBox.setMargin(sparkBtn, new Insets(25, 15, 0, 15));
        mainPane.setTop(topHBox);

        //Components for the left of BordPane
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.getStyleClass().addAll("vbox", "primary-background");
        leftVBox.setPrefSize(125, 500);
        leftVBox.setMaxWidth(125);

        //Components to add to the leftVBox
        grid = new GridPane();
        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getStyleClass().add("search-box");
        searchBox.setPrefSize(178, 20);
        grid.add(searchBox, 0, 0);
        VBox.setMargin(grid, new Insets(30, 10, 0, 10));
        searchBox.setPrefSize(178, 20);

        searchfield = new TextField("Search here"); 
        searchfield.setAlignment(Pos.CENTER_LEFT);
        searchfield.getStyleClass().add("transparent");
        searchfield.setPrefSize(89, 23);

        ImageView arrow = new ImageView("/images/routearrow.png");
        arrow.setFitWidth(14);
        arrow.setFitHeight(14);
        searchBtn = new Button(" ", arrow);
        searchBtn.setPrefSize(11,19);
        searchBtn.getStyleClass().add("transparent");

        searchBox.getChildren().addAll(searchfield, searchBtn);

        //Create separator line for a clean look 
        Separator line = new Separator();
        line.setMaxWidth(100);
        line.setPrefHeight(60);

        //Vertical Box for the navigation components
        VBox navBox = new VBox();
        navBox.setAlignment(Pos.TOP_LEFT);
        navBox.setPrefSize(178, 364);

        //Components to add to navBox
        //Current location box
        HBox locBox = new HBox();
        locBox.setAlignment(Pos.CENTER);
        locBox.getStyleClass().add("search-box");
        locBox.setPrefSize(178, 20);
        //Textfield
        locationfield = new TextField("Location");
        locationfield.setAlignment(Pos.CENTER_LEFT);
        locationfield.getStyleClass().add("transparent");
        locationfield.setPrefSize(89, 23);
        //Image for button and add image to button
        ImageView location = new ImageView("/images/start.png");
        location.setFitWidth(14);
        location.setFitHeight(14);
        searchBtn = new Button(" ", location);
        searchBtn.setPrefSize(11,19);
        searchBtn.getStyleClass().add("transparent");
        
        //Destination box
        HBox desBox = new HBox();
        desBox.setAlignment(Pos.CENTER);
        desBox.getStyleClass().add("search-box");
        desBox.setPrefSize(178, 20);
        //textfield
        destinationfield = new TextField("Destination");
        destinationfield.setAlignment(Pos.CENTER_LEFT);
        destinationfield.getStyleClass().add("transparent");
        destinationfield.setPrefSize(89, 23);
        //Image for button
        ImageView destination = new ImageView("/images/slut.png");
        destination.setFitWidth(14);
        destination.setFitHeight(14);
        searchBtn = new Button(" ", destination);
        searchBtn.setPrefSize(11,19);
        searchBtn.getStyleClass().add("transparent");

        //Add textfield and button to locBox and desBox
        locBox.getChildren().addAll(locationfield, location);
        desBox.getChildren().addAll(destinationfield, destination);

        //Add locBox and desBox to navigation box
        navBox.getChildren().addAll(locBox, desBox);
        VBox.setMargin(locBox, new Insets(0, 10, 10, 10));
        VBox.setMargin(desBox, new Insets(10, 10, 10, 10));

        //Transportation box for buttons
        HBox transportBox = new HBox(5);
        transportBox.setAlignment(Pos.CENTER);
        //Button for walk
        ImageView walk = new ImageView("/images/walk.png");
        walk.setFitWidth(30);
        walk.setFitHeight(30);
        walkBtn = new Button(" ", walk);
        walkBtn.setPrefSize(11,19);
        walkBtn.getStyleClass().add("transparent");
        //Button for bike
        ImageView bike = new ImageView("/images/bicycle.png");
        bike.setFitWidth(30);
        bike.setFitHeight(30);
        bikeBtn = new Button(" ", bike);
        bikeBtn.setPrefSize(11,19);
        bikeBtn.getStyleClass().add("transparent");
        //Button for car
        ImageView car = new ImageView("/images/car.png");
        car.setFitWidth(30);
        car.setFitHeight(30);
        carBtn = new Button(" ", car);
        carBtn.setPrefSize(11,19);
        carBtn.getStyleClass().add("transparent");
        //Add
        transportBox.getChildren().addAll(walkBtn, bikeBtn, carBtn);
        navBox.getChildren().add(transportBox);
        VBox.setMargin(transportBox, new Insets(10, 0, 20, 0));

        //Set all components inside the left VBox
        leftVBox.getChildren().addAll(grid, line, navBox);
        //leftVBox.getChildren().add(searchBox);

        //Set the left box inside the main borderPane to the left.
        mainPane.setLeft(leftVBox);
        BorderPane.setMargin(leftVBox, new Insets(0,0,0,30));


        //Add mainPane to Mainscene
        this.getChildren().add(mainPane);

    }

    public void handleBtnClick(Button clickedBtn) {
        if (prevClickedBtn != null) {
            prevClickedBtn.getStyleClass().removeAll("clicked-icons");
            // Remove the clicked-icons style class from the previously clicked button
        }
        clickedBtn.getStyleClass().add("clicked-icons");
        // Add the clicked-icons style class to the current button
        prevClickedBtn = clickedBtn;
        // Update the prevClickedBtn variable
    }

    //this method creates a label that matches the searched string
    public VBox dropDown(String text, TernaryTree tree){
        VBox dropDown = new VBox();
        dropDown.setBackground(new Background(new BackgroundFill(Color.WHITE, null,null))); // colors the background of the dropdown
        for (int i = 0; i < 17; i++){
            Label label = new Label(tree.searchResults(text).get(i));
            dropDown.getChildren().add(label);

        }
        /* 
        for (String buildingName : tree.searchResults(text)){
            Label label = new Label(buildingName);
            dropDown.getChildren().add(label);
        }*/
       
        return dropDown;
    }

}