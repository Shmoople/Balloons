package balloons;

import javafx.animation.AnimationTimer;
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

import java.util.ArrayList;
import java.util.List;

import balloons.entities.Balloon;
import balloons.playingfield.*;

/**
 * Main App class that contains displayable implementaiton of the project (essentially everything comes together here!)
 * @author Garett Bua
 * @author Wyatt Rose
 * @author Steven Short
 */
public class App extends Application {

    private static Balloon balloon;
    private static Circle balloonDisplay;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Board board = new Board(Board.createGrid(20,20,50,10));
        displayBoard(root, board,5);

        balloon = new Balloon(3,5);
        balloonDisplay = new Circle(5,Color.GREEN);
        root.getChildren().add(balloonDisplay);

        // new AnimationHandle().start(); // display game

        Polyline routePolyLine = new Polyline();

        routePolyLine.getPoints().addAll

        PathTransition balloonTransition = new PathTransition();
        balloonTransition.setNode(balloonDisplay);
        balloonTransition.setDuration(Duration.seconds(balloon.getSpeed()));
        balloonTransition.setPath(routePolyLine);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private class AnimationHandle extends AnimationTimer {
        @Override
        public void handle(long currentTime) {
            balloon.setxPosition(balloon.getxPosition()+balloon.getSpeed());
            balloon.setyPosition(balloon.getyPosition()+balloon.getSpeed());
            balloonDisplay.setTranslateX(balloon.getxPosition());
            balloonDisplay.setTranslateY(balloon.getyPosition());
        }
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
        Double[] rawRoute = new Double[route.size()*2];

        for(int i = 0; i < route.size(); i += 2) {
            rawRoute[i] = route.get(i).getxPosition();
            rawRoute[i+1] = route.get(i).getyPosition();
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