package balloons;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    public void start(Stage primaryStage) {
        Group root = new Group();

        Board board = new Board(Board.createGrid(20,20,50,10));
        RouteFinder<GraphPoint> routeFinder = new RouteFinder<GraphPoint>(board.getBalloonGraph(),
        new EuclideanGraphPointScorer(),
        new EuclideanGraphPointScorer());

        List<GraphPoint> route = routeFinder.findRoute(board.getBalloonGraph().getNode("19-0"),board.getBalloonGraph().getNode("0-19"));

        Balloon balloon;
        Circle balloonDisplay;

        displayBoard(root, board,5);
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

        List<Circle> balloons = new ArrayList<Circle>();
        for(int i = 0; i < 100; i++) {
            balloons.add(new Circle(5,Color.BLUE));
            root.getChildren().add(balloons.get(i));
            // applyAndPlayBalloonTransition(balloons.get(i), routePolyLine, .1);
            // try {
            //     Thread.sleep(500);
            // } catch(InterruptedException e) { /* do nothing */ }
        }

        Thread balloonProducerThread = new Thread(new BalloonProducer(balloons, routePolyLine, .1, 500));
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

        private List<Circle> balloons;
        private Polyline rawRoute;
        private double speed;
        private int delay;

        public BalloonProducer(List<Circle> balloons, Polyline rawRoute, double speed, int delay) {
            this.balloons = balloons;
            this.rawRoute = rawRoute;
            this.speed = speed;
            this.delay = delay;
        }

        public List<Circle> getBalloonsList() { return this.balloons; }

        @Override
        public void run() {
            for(Circle b : balloons) {
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
    
    public static Circle applyAndPlayBalloonTransition(Circle balloon, Polyline rawRoute, double speed) { // just treat circle objects as balloons, that is a pretty poor design pattern but whatever
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