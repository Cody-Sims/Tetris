package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static tetris.Constants.*;


/*
 * This class is responsible for creating the board and all of its functions. Blocks no longer being used are added to
 * the board. It is also responsible for collision detection. If it detects a block above the starting point. Then
 * it sets the _gameOver variable to true
 */
public class Board {
    private Pane _root;
    private Pane _boardPane;
    private int _column;
    private int _row;
    private Square[][] _board;
    private boolean _gameOver = false;


    // Instantiates the variables and sets up the board when called
    Board(Pane root) {
        _root = root;
        _boardPane = new Pane();
        _column = BOARD_WIDTH;
        _row = BOARD_HEIGHT;
        _board = new Square[_column][_row];


        setupBoard();
    }

    // This sets up the border of the board. The outside blocks are gray and the inside of the board is black
    private void setupBoard() {
        // Sets up board border
        for (int i = 0; i < _column; i++) {
            for (int j = 0; j < _row; j++) {
                Square boardPiece = new Square(i * 30, j * 30);
                _boardPane.getChildren().add(boardPiece.getNode());
                _board[i][j] = boardPiece;
            }
        }

        // Sets up within the border of the board
        for (int i = 1; i < _column - 1; i++) {
            for (int j = 1; j < _row - 1; j++) {
                Square boardPiece = new Square( i * 30, j * 30);
                boardPiece.setColor(Color.BLACK);
                _boardPane.getChildren().add(boardPiece.getNode());
                _board[i][j] = boardPiece;
            }
        }

        _root.getChildren().add(_boardPane);
    }

    /*
     * This adds a piece to the board by getting the x and y position of the piece. It uses the x and y location to
     * decide where in the board array it would be. Then, it sets that part of the board array to the piece's color
     */
    public void addPiece(Square[] block) {
        for (int i = 0; i < 4; i++) {
            int x = block[i].getX() / 30;
            int y = block[i].getY() / 30;

            _board[x][y].setColor(block[i].getColor());
        }
    }

    // Checks each row to see if one is full. If it is full it deletes it.
    public void checkRow(){

        //Checks in Row major for lines to be cleared
        for (int i = 1; i < _row - 1; i++) {
            int row = i; // Row index
            int blackSquares = 0;

            for (int j = 1; j < _column - 1; j++) {
                Color boardColor = _board[j][i].getColor();
                if (boardColor == Color.BLACK) {
                    blackSquares += 1;
                }
            }
            if (blackSquares == 0){ // If there is no black squares the row is full
                deleteRow(row); // deletes the row at the index above
                moveRowsDown(row);
            }
        }
    }

    // Deletes an entire row by setting the board squares to black
    private void deleteRow(int row){
        for (int i = 1; i < _column - 1; i++) {
            {
                _board[i][row].setColor(Color.BLACK);
            }
        }
    }

    // Takes in a row as an argument and moves every row above that down by one
    private void moveRowsDown(int row) {
        for (int i = 1; i < _column - 1; i++) {
            for (int j = row ; j > 2 ; j--) {
                _board[i][j].setColor(_board[i][j - 1].getColor());
            }
        }
    }


    /*
     * Checks for collision against an entire block. It is also responsible for detecting the games borders and
     * preventing the block from going out of bounds. Lastly, if the piece is above the top border, gameover is set to
     * true
     */
    public boolean checkCollision(Pane blockPane, Square[] block, int xOffset, int yOffset) {

        for (int i = 0; i < 4; i++) {
            int xPos = block[i].getX() / 30 + xOffset;
            int yPos = block[i].getY() / 30 + yOffset;
            Color boardColor = _board[xPos][yPos].getColor();

            if (boardColor == Color.GRAY) {
                return false;
            }

            else if (yPos <= 3 && boardColor != Color.BLACK) { //Checks blocks against board.
                _root.getChildren().remove(blockPane);
                addPiece(block);
                _gameOver = true;
            }

            else if (boardColor != Color.BLACK || yPos > 20) {
                _root.getChildren().remove(blockPane);
                addPiece(block);

                return true;
            }



        }
        return false;
    }

    /*
     * Checks for collision against a single square by seeing if the square in the board array at that position is
     * a color other than black which is the default board color
     */

    public boolean checkCollision(int x, int y){
        if(_board[x/30][y/30].getColor() != Color.BLACK) {
            return true;
        }
        return false;
    }

    // returns true or false depending on the state of the game
    public boolean isGameOver(){
        return _gameOver;
    }

    // returns the board's pane
    public Pane getPane(){
        return _boardPane;
    }
}


