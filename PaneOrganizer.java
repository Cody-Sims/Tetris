package tetris;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

// Creates the main pane and allows other parts of the program to access it with a getter method
public class PaneOrganizer {
    private BorderPane _root;
    private Pane _gamePane;

    // Sets the size of the root pane and instantiates the Tetris class.
    PaneOrganizer(){
        _root = new BorderPane();
        _root.setPrefSize(360, 660);

        _gamePane = new Pane();
        _root.getChildren().add(_gamePane);
        new Tetris(_gamePane);
         setupQuitButton();
    }

    // Returns root
    public BorderPane getRoot() {
        return _root;
    }

    // Sets up a quit button and adds it to the main pain
    private void setupQuitButton(){
        Pane buttonPane = new Pane();
        Button button = new Button("Quit");
        button.setOnAction(new ButtonHandler());
        button.setFocusTraversable(false);
        buttonPane.getChildren().add(button);
        _root.setBottom(buttonPane );
    }

    // This is what happens when the button is clicked
    private class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            System.exit(3 );
        }
    }
}
