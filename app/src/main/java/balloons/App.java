package balloons;

import javafx.animation.AnimationTimer;
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

        Balloon balloon;
        Circle balloonDisplay;

        displayBoard(root, board,3);
        balloon = new Balloon(1,5);
        balloonDisplay = new Circle(5,Color.GREEN);
        root.getChildren().add(balloonDisplay);

        // new AnimationHandle().start(); // display game


        Polyline routePolyLine = graphPointRouteToPolyLine(route);
        // PathTransition balloonTransition = new PathTransition();


        // balloonTransition.setNode(balloonDisplay);
        // balloonTransition.setPath(routePolyLine);
        // balloonTransition.setInterpolator(Interpolator.LINEAR);
        

        /*
         * Ok, so in order to get this to function like an actual game, there should be some stuff that needs to get done first
         * 
         * Balloons position shouldn't actually matter, we can just treat the node as an actual balloon.
         */
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        // balloonTransition.play();

        List<ImageView> balloons = new ArrayList<ImageView>();

        /* THIS CODE RIGHT HERE IS SUPER UNSAFE BUT WHATEVER LETS JUST HOPE THE RELATIVE PATH WORKS! */
        InputStream stream = null; // get ready for a nullpointer exception...
        try {
            stream = new FileInputStream("./src/main/java/balloons/resources/images/Blue Balloon.png");
        } catch (FileNotFoundException e) {
            // oh no...
            System.out.println("Could not find the specified file with this file path!");
            e.printStackTrace();
        }


        Image image = new Image(stream);

        for(int i = 0; i < 100; i++) {
            /*
             *  InputStream stream = new FileInputStream("D:\images\elephant.jpg");
                Image image = new Image(stream);

                //Creating the image view
                ImageView imageView = new ImageView();

                //Setting image to the image view
                imageView.setImage(image);
            */

            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            // imageView.setFitHeight(100);
            // imageView.setFitWidth(100);

            balloons.add(imageView); // imageview shouldn't be that different than using a circle object (just need to load the image with a file loader class)
            root.getChildren().add(balloons.get(i));
            // applyAndPlayBalloonTransition(balloons.get(i), routePolyLine, .1);
            // try {
            //     Thread.sleep(500);
            // } catch(InterruptedException e) { /* do nothing */ }
        }
        BalloonProducer bp = new BalloonProducer(balloons, routePolyLine, 1, 500);
        Thread balloonProducerThread = new Thread(bp);
        balloonProducerThread.start();

        double xpositionoffirstballoon = bp.getBalloonsList().get(0).getTranslateX();

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
        private Polyline rawRoute;
        private double speed;
        private int delay;

        public BalloonProducer(List<ImageView> balloons, Polyline rawRoute, double speed, int delay) {
            this.balloons = balloons;
            this.rawRoute = rawRoute;
            this.speed = speed;
            this.delay = delay;
        }

        public List<ImageView> getBalloonsList() { return this.balloons; }

        @Override
        public void run() {
            for(ImageView b : balloons) {
                applyAndPlayBalloonTransition(b, rawRoute, speed);

                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {
                    /* Do nothing! */
                }
            }
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