package balloons;

import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Group group = new Group();

        /* create nodes */

        /* create connections */

        group.getChildren().add(new Circle(5));
        primaryStage.setScene(new Scene(group));
        primaryStage.show();
    }

    public static void main(String[] args) {
        App.launch(args);
    }
}

// 無

// well at least I can say that I wrote something on this document (I seriously need to get my time management in order)

// typing out text is pretty fun though, I would like to spend more time doing this, yet I keep procrastinating

// I'm working the entire time, but on nothing that is actually important.

// I'll do better tomorrow, right now I just need to sleep

// people are over right now

// I'm not with them

// it's whatever....