package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/*
 * This class is responsible for all of the game's logic. It creates a timeline for which the game abides by. It is
 * responsible for the creation and deletion of the tetris blocks. It is also responsible for key movement and creating
 * action events that occur in the timeline.
 */
public class Tetris {
    private Pane _root;
    private Board _board;
    private Block _block;
    private Timeline _timeline;
    private Timeline _timeline2;
    private Text _text;
    private boolean _paused; // Keeps track of whether or not the game is paused

    // Instantiates variables and sets up movement and the timeline
    Tetris(Pane root) {
        _root = root;
        _board = new Board(_root);
        _block = new Block(_root, _board);

        setupMovement();
        setupTimeline();
    }

    /*
     * Sets up the timeline with two keyframes. One is responsible for updating the board. The other is responsible for
     * generation of blocks and making the blocks fall automatically
     */
    private void setupTimeline() {
        KeyFrame kf1 = new KeyFrame(Duration.seconds(.1), new BoardHandler());
        KeyFrame kf2 = new KeyFrame(Duration.seconds(.5), new BlockHandler());

        _timeline = new Timeline(kf1); // Updates Board
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();

        _timeline2 = new Timeline(kf2); // Moves block down automatically
        _timeline2.setCycleCount(Animation.INDEFINITE);
        _timeline2.play();
    }

    // It adds the event handler to the block pane and set its focus traversable to true
    private void setupMovement() {
        _block.getPane().addEventHandler(KeyEvent.KEY_PRESSED, new keyHandler());
        _block.getPane().setFocusTraversable(true);
    }


    /*
     * This class handles updating the board. It checks the rows to see if a line needs to be cleared.
     * If the game is over it creates a game over screen.
     */
    private class BoardHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (_board.isGameOver()) {
                _timeline.stop();
                _timeline2.stop();
                _root.getChildren().remove(_block.getPane());

                _text = new Text("Game Over");

                _text.setX(125);
                _text.setY(300);
                _text.setFill(Color.WHITE);
                _text.setFont(Font.font("ComicSans", 30));

                _root.getChildren().add(_text);
            }
            // Creates a random new block if collision has been detected. It then adds key input to the block
            if (_block.getCollisionDetected() && !_board.isGameOver()) {
                _root.getChildren().remove(_block.getPane());
                _block = new Block(_root, _board);
                setupMovement();
            }

            _board.checkRow();
        }
    }

    // This constantly moves the blocks down.
    private class BlockHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            _block.down();
        }
    }

    // This removes the paused text from the pane and plays the timeline
    private void unpause(){
        _root.getChildren().remove(_text);
        _timeline.play();
        _timeline2.play();
        _paused = false;
    }

    // This pauses the game by stopping the timelines and creates adds "Paused" to the pane.
    private void pause(){
        _text = new Text("Paused");
        _text.setX(125);
        _text.setY(300);
        _text.setFill(Color.WHITE);
        _text.setFont(Font.font("ComicSans", 30));
        _root.getChildren().add(_text);

        _timeline.stop();
        _timeline2.stop();
        _paused = true;
    }

    // Responsible for key input and pausing and unpausing the game
    private class keyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            if (!_board.isGameOver() && !_paused) {
                switch (e.getCode()) {
                    case DOWN:
                        _block.down();
                        break;

                    case RIGHT:
                        _block.right();
                        break;

                    case LEFT:
                        _block.left();
                        break;

                    case UP:
                        _block.rotate();
                        break;
                    case SPACE:
                        _block.instantDown();
                        break;

                    default:
                        break;
                }
            }


            // Unpauses game
            if (!_board.isGameOver() && _paused && e.getCode() == KeyCode.P) {
                unpause();
            }
            // pauses game
            else if (!_board.isGameOver() && !_paused && e.getCode() == KeyCode.P) {
                pause();
            }

            e.consume();
        }

    }

}


