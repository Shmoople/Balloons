package balloons;

import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        Group group = new Group();
        primaryStage.setScene(new Scene(group));
        primaryStage.show();
    }

    /**
     * Main method for this program if javafx entry-point forwarding is required
     * @param args arguments passed to launch() static method
     */
    public static void main(String[] args) { App.launch(args); }
}

// 無