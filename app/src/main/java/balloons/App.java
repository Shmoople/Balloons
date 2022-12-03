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