package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static tetris.Constants.*;


/*
 * Creates a random block that is able to move left, right, down, and rotate. This block is added to its own block pane
 * that is added to the main root pane.
 */
public class Block {
    Pane _root;
    Pane _blockPane;
    Board _board;
    Square[] _blockArray;
    Color _color;

    private int[][] _coords;
    int _xOffset;

    int _randomNumber;
    boolean _collisionDetected = false;

    // Instantiates variables declared above and sets up a random block.
    public Block(Pane root,Board board)
    {
        _root = root;
        _board = board;
        _blockPane = new Pane();
        _blockArray = new Square[4];
        _xOffset = (int) (Math.random() * 6) + 1; // needs to be between 5 and 10
        _randomNumber = (int)(Math.random() * 7);

        random();
        setupBlock();
    }

    // This method randomly picks a block by using a random number and changing the block's coordinates
    private void random(){
        switch ( (_randomNumber)) {
            case 0:
                _coords = S_PIECE_COORDS;
                _color = Color.BLUE;
                break;
            case 1:
                _coords = Z_PIECE_COORDS;
                _color = Color.RED;
                break;

            case 2:
                _coords = T_PIECE_COORDS;
                _color = Color.WHITE;
                break;
            case 3:
                _coords = L_PIECE_COORDS;
                _color = Color.ORANGE;
                break;
            case 4:
                _coords = I_PIECE_COORDS;
                _color = Color.PURPLE;
                break;
            case 5:
                _coords = SQUARE_PIECE_COORDS;
                _color = Color.PINK;
                break;
            case 6:
                _coords = J_PIECE_COORDS;
                _color = Color.YELLOW;
                break;
        }
    }

    /*
     * This method sets up 4 Squares in an arrangement that is specified by the _coords array. It adds each square
     * to a block pane and sets it to a color defined by the _ color variable.
     */
    public void setupBlock() {
        for(int i = 0; i < 4; i++) {
            Square square = new Square(_coords[i][0] + _xOffset * 30, _coords[i][1] + 30); //Creates a square
            square.setColor(_color);

            _blockArray[i] = square; // Puts square in block
            _blockPane.getChildren().add(square.getNode()); // Adds square to block pane
        }

        _root.getChildren().add(_blockPane); //adds block pane to main pane
    }

    // This method moves the block down by 30 pixels if collision is not detected
    public void down() {
        if (!_collisionDetected){ //Checks for collision
            _collisionDetected = _board.checkCollision(_blockPane, _blockArray,0,1);

            for(int i = 0; i < 4; i++) {
                _blockArray[i].getNode().setY(_blockArray[i].getNode().getY() + 30); //moves block down 30 pixel
            }
        }

    }

    // This moves the block down until collision is detected
    public void instantDown(){
        while(!_collisionDetected){
            down();
        }
    }

    // This moves the block to the left by 30 pixels if collision is not detected.
    public void left(){
        boolean collisionDetected = false;
        int[][] tempCoords = new int[4][2];

        for(int i = 0; i < 4; i++){
            int newX = _blockArray[i].getX() - 30;
            int newY =  _blockArray[i].getY();

            tempCoords[i][0] = newX;
            tempCoords[i][1] = newY;

            if(_board.checkCollision(newX, newY)){
                collisionDetected = true;
            }
        }

        if (!collisionDetected){
            for(int j = 0; j < 4; j++){
                _blockArray[j].getNode().setX(tempCoords[j][0]);
                _blockArray[j].getNode().setY(tempCoords[j][1]);
            }
        }
    }

    // This moves the block to the right by 30 pixels if collision is not detected.
    public void right(){
        boolean collisionDetected = false;
        int[][] tempCoords = new int[4][2];

        for(int i = 0; i < 4; i++){
            int newX = _blockArray[i].getX() + 30;
            int newY =  _blockArray[i].getY();

            tempCoords[i][0] = newX;
            tempCoords[i][1] = newY;

            if(_board.checkCollision(newX, newY)){
                collisionDetected = true;
            }
        }

        if (!collisionDetected){
            for(int j = 0; j < 4; j++){
                _blockArray[j].getNode().setX(tempCoords[j][0]);
                _blockArray[j].getNode().setY(tempCoords[j][1]);
            }
        }
    }

    /*
     * This rotates the random block around a fixed point defined in the first coordinate in the block's array.
     * It temporarily stores the coordinates of the blocks position after rotation. Then, it checks to see if the
     * rotation would result in a collision
     */

    public void rotate(){
        if(_randomNumber != 5){ // this is the number for a square. So if it isn't a square it can rotate
            int centerOfRotationX = _blockArray[0].getX();
            int centerOfRotationY = _blockArray[0].getY();
            boolean collisionDetected = false;
            int[][] tempCoords = new int[4][2];

            for(int i = 0; i < 4; i++){
                int oldX = _blockArray[i].getX();
                int oldY = _blockArray[i].getY();

                int newX = centerOfRotationX - centerOfRotationY + oldY;
                int newY = centerOfRotationY + centerOfRotationX - oldX;

                tempCoords[i][0] = newX;
                tempCoords[i][1] = newY;

                if(_board.checkCollision(newX, newY)){
                    collisionDetected = true;
                }
            }
            if (!collisionDetected){ // If this does not result in a collision the block can rotate
                for(int j = 0; j < 4; j++){
                    _blockArray[j].getNode().setX(tempCoords[j][0]);
                    _blockArray[j].getNode().setY(tempCoords[j][1]);
                }
            }
        }
        }



    // Returns a boolean value on whether or not collision has been detected
    public boolean getCollisionDetected(){
        return _collisionDetected;
    }

    // Returns block pane
    public Pane getPane(){
        return _blockPane;
    }

}
