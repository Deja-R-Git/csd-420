//Juedeja Richard_Module7_9-20-25
//Create an external CSS style sheet that defines a class for white fill
//and black stroke color and an ID for red and green color.
//Write a JavaFX program that displays four circles and uses the style
//class and ID. Write test code that ensures your code functions correctly

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//making CircleProgram a subclass of Application
public class CircleProgram extends Application {

    @Override
    public void start(Stage primary_stage) {
//create a pane window
        Pane root = new Pane();
//create four circles
        Circle c1 = new Circle(100,170,50);
        Circle c2 = new Circle(230,170,50);
        Circle c3 = new Circle(360,170,50);
        Circle c4 = new Circle(490,170,50);
//create a rectangle and set fill,stroke,color,height and width
        Rectangle border = new Rectangle(50,50,100,250);
//could not get rectangle transparency to work in the CSS file so I did some styling here
//Im not sure what the problem was there butt if you could explain how to do it in the CSS that would be helpful
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(5);//
        //border.getStyleClass().add("border");
//two plain circles so
        c1.getStyleClass().add("plaincircle");
//set ID for red and green circle specifically
        c2.setId("redcircle");
        c3.setId("greencircle");
        c4.getStyleClass().addAll("plaincircle","circleborder");
//gets the list of stuff made and adds it to the scene
        root.getChildren().addAll(border,c1,c2,c3,c4);
//sets the size of the scene
        Scene scene = new Scene(root, 1000,500);
//gets external css file
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
        primary_stage.setTitle("Circle");
        primary_stage.setScene(scene);
        primary_stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

//I did get a few warnings as well like these:
//WARNING: CSS Error parsing file:/C:/Users/Lordy/IdeaProjects/Circle/out/production/CircleProgram/mystyle.css: Expected LBRACE at [-1,-1]
//WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
//WARNING: sun.misc.Unsafe::allocateMemory has been called by com.sun.marlin.OffHeapArray (file:/C:/Program%20Files/javafx-sdk-24.0.2/lib/javafx.graphics.jar)
//WARNING: Please consider reporting this to the maintainers of class com.sun.marlin.OffHeapArray
//WARNING: sun.misc.Unsafe::allocateMemory will be removed in a future release