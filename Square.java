package tetris;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static tetris.Constants.SQUARE_WIDTH;

// Creates a Square at a given position and allows that square to be returned
// I didn't give it its own pane because I felt it should be added to the block or board's pane since it is a component
public class Square {

    private Rectangle _boardPiece;
    private int _xPos;
    private int _yPos;
    private Color _color;

    // Creates a square at the given X and Y coordinates
    public Square(int i, int j){
        _xPos = i;
        _yPos = j;
        setupSquare();
    }

    // It sets up a gray square of size 30 by 30 with a black outline
    private void setupSquare(){
        _boardPiece = new Rectangle(SQUARE_WIDTH,SQUARE_WIDTH, Color.GRAY);
        _boardPiece.setX(_xPos);
        _boardPiece.setY(_yPos);
        _boardPiece.setStroke(Color.BLACK); // Visually separates each block
    }

    // returns the x position
    public int getX() {
        return (int) _boardPiece.getX();
    }

    // returns the y position
    public int getY(){
        return (int) _boardPiece.getY();
    }

    // sets the color of the square
    public void setColor(Color color){
        _boardPiece.setFill(color);
        _color = color;
    }

    // gets the color of the square
    public Color getColor(){
        return _color;
    }

    // returns the square's node
    public Rectangle getNode(){
        return _boardPiece;
    }
}
