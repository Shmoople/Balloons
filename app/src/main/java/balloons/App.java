package balloons;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import balloons.entities.*;

import balloons.entities.Balloon;
import balloons.playingfield.*;
import balloons.astar.*;

/**
 * Main App class that contains displayable implementaiton of the project (essentially everything comes together here!)
 * @author Garett Bua
 * @author Wyatt Rose
 * @author Steven Short
 */
public class App extends Application {


    @Override
    public void start(Stage primaryStage) { // this gets run
        Group root = new Group();

        Board board = new Board(Board.createGrid(20,20,50,10));
        RouteFinder<GraphPoint> routeFinder = new RouteFinder<GraphPoint>(board.getBalloonGraph(),
        new EuclideanGraphPointScorer(),
        new EuclideanGraphPointScorer());

        List<GraphPoint> route = routeFinder.findRoute(board.getBalloonGraph().getNode("19-0"),board.getBalloonGraph().getNode("0-19"));

        displayBoard(root, board,3);


        /* SETUP BACKGROUND */
        InputStream coolStream = null; // get ready for a nullpointer exception...
        try {
            coolStream = new FileInputStream("./src/main/java/balloons/resources/images/Finished_Background_for_bloons.png");
        } catch (FileNotFoundException e) {
            // oh no...
            System.out.println("Could not find the specified file with this file path!");
            e.printStackTrace();
        }

        Image imageImage = new Image(coolStream);
        ImageView background = new ImageView();
        background.setPreserveRatio(true);
        background.setImage(imageImage);
        root.getChildren().add(background);


        /* SETUP BALLOONS */
        
        Polyline routePolyLine = graphPointRouteToPolyLine(route);

        BalloonProducer bp = new BalloonProducer(root, balloons.entities.Round.S1R5, routePolyLine, 500);
        Thread balloonProducerThread = new Thread(bp);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
        /* START SENDING BALLOONS */
        balloonProducerThread.start();

        // transition for the animation should be done with a single transition object... right?
        // transition objects can only have one node that they're bound to, so it wouldn't make a whole lot of sense

        // if that wasn't the case...

        // ok so we'll just make method that binds a transition object to a balloon and then plays it (effectively changing the movement...)
        // cool

        /* Should find a way to pass the balloon object to the thread (could just define a new runnable implementation and pass through constructor) */
        // Thread t = new Thread(()->{
 
           
        //     while(true) {
        //         System.out.println(App.balloonDisplay.getTranslateX());

        //         try {
        //             Thread.sleep(100);
        //         } catch(InterruptedException e) { /* do nothing */}

        //     }

        // });


        // t.start();
    }

    private class BalloonProducer implements Runnable {

        private List<ImageView> balloons;
        private Balloon[] balloonObjects;
        private Polyline rawRoute;
        private int delay;

        public BalloonProducer(Group root, Balloon[] balloonObjects, Polyline rawRoute, int delay) {

            this.rawRoute = rawRoute;
            this.delay = delay;
            this.balloonObjects = balloonObjects;
            this.balloons = new ArrayList<ImageView>();

            /* THIS CODE RIGHT HERE IS SUPER UNSAFE BUT WHATEVER LETS JUST HOPE THE RELATIVE PATH WORKS! */

            for(Balloon b : balloonObjects) {

                InputStream stream = null; // get ready for a nullpointer exception...
                try {
                    switch(b.getHitPoints()) {
                        case 1: // red
                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Red Balloon.png");
                            break;
                        case 2: // orange

                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Orange Balloon.png");
                            break;
                        case 3: // yellow
                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Yellow Balloon.png");
                            break;
                        case 4: // green
                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Green Balloon.png");
                            break;
                        case 5: // blue
                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Blue Balloon.png");
                            break;
                        case 6: // purple
                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Purple Balloon.png");
                            break;
                        default: // red
                            stream = new FileInputStream("./src/main/java/balloons/resources/images/Red Balloon.png");
                            break;
                    }
                } catch (FileNotFoundException e) {
                    // oh no...
                    System.out.println("Could not find the specified file with this file path!");
                    e.printStackTrace();
                }


                Image image = new Image(stream);
                ImageView imageView = new ImageView();

                imageView.setImage(image);
                imageView.setPreserveRatio(true);
                balloons.add(imageView);
                root.getChildren().add(imageView);
            }

        }

        public List<ImageView> getBalloonsList() { return this.balloons; }

        @Override
        public void run() {
            System.out.println("Start of run!");
            for(int i = 0; i < balloonObjects.length; i++) {

                System.out.println("Inside for loop");

                applyAndPlayBalloonTransition(balloons.get(i), rawRoute, balloonObjects[i].getSpeed());

                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {
                    /* Do nothing! */
                }
            }

            System.out.println("End of run!");


        }
    }

    /* Not using this for the time being, it should just be easier to use transitions instead */
    // private class AnimationHandle extends AnimationTimer {
    //     @Override
    //     public void handle(long currentTime) {
    //         balloon.setxPosition(balloon.getxPosition()+balloon.getSpeed());
    //         balloon.setyPosition(balloon.getyPosition()+balloon.getSpeed());
    //         balloonDisplay.setTranslateX(balloon.getxPosition());
    //         balloonDisplay.setTranslateY(balloon.getyPosition());
    //     }
    // }
    
    public static ImageView applyAndPlayBalloonTransition(ImageView balloon, Polyline rawRoute, double speed) { // just treat circle objects as balloons, that is a pretty poor design pattern but whatever

        PathTransition balloonTransition = new PathTransition();
        balloonTransition.setNode(balloon);
        balloonTransition.setDuration(Duration.seconds(speed*(rawRoute.getPoints().size()/2))); // タイミング
        balloonTransition.setPath(rawRoute);
        balloonTransition.setInterpolator(Interpolator.LINEAR);
        balloonTransition.play();
        return balloon; // return passed Circle for method chaining
    }

    public static void displayBoard(Group root, Board board, int size) {

        // this does way to many iterations (since lines would have already been drawn from each node anyways)
        // whatever... shouldn't impact performance that much and is just used for debugging anyways
        for(GraphPoint n : board.getBalloonGraph().getNodes()) {
            for(GraphPoint g : board.getBalloonGraph().getConnections(n)) {
                Line displayObject = new Line(n.getxPosition(),n.getyPosition(),g.getxPosition(),g.getyPosition());
                displayObject.setStroke(Color.BLACK);
                displayObject.setStrokeWidth(3);
                root.getChildren().add(displayObject);
            }
        }

        for(GraphPoint n : board.getBalloonGraph().getNodes()) {
            Circle displayObject = new Circle(n.getxPosition(),n.getyPosition(),size); // displayObject is the Circle thing
            displayObject.setFill(Color.RED);
            root.getChildren().add(displayObject);
        }
    }

    public static void displayRouteTraversal(Group root, List<GraphPoint> route, int size) {

        Balloon b = new Balloon(3,1);
        Rectangle displayObject = new Rectangle(size,size,Color.GREEN);
    }

    public Polyline graphPointRouteToPolyLine(List<GraphPoint> route) {
        ArrayList<Double> rawRoute = new ArrayList<Double>();

        for(GraphPoint routeNode : route) {
            rawRoute.add(routeNode.getxPosition());
            rawRoute.add(routeNode.getyPosition());
        }

        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(rawRoute);

        return polyline;
    }

    /**
     * Main method for this program if javafx entry-point forwarding is required
     * @param args arguments passed to launch() static method
     */
    public static void main(String[] args) { App.launch(args); }
}

// 無