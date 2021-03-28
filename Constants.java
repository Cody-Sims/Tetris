package tetris;

import javafx.scene.paint.Color;

public class Constants {
     // Board Dimensions
    public static final int BOARD_WIDTH = 12; //squares
    public static final int BOARD_HEIGHT = 22;

    // width of each square
    public static final int SQUARE_WIDTH = 30;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = { {0, 2*SQUARE_WIDTH}, {0, 0}, {0, SQUARE_WIDTH}, {0, 3*SQUARE_WIDTH} };
    public static final int[][] T_PIECE_COORDS = { {0, SQUARE_WIDTH}, {SQUARE_WIDTH, SQUARE_WIDTH}, {0, 0},  {0, 2*SQUARE_WIDTH}};
    public static final int[][] SQUARE_PIECE_COORDS = {{SQUARE_WIDTH,SQUARE_WIDTH}, {0,0},{0,SQUARE_WIDTH},{SQUARE_WIDTH, 0}};
    public static final int[][] Z_PIECE_COORDS = {{SQUARE_WIDTH,SQUARE_WIDTH},{SQUARE_WIDTH,0},{2*SQUARE_WIDTH, 0},{0,SQUARE_WIDTH}};
    public static final int[][] S_PIECE_COORDS = {{SQUARE_WIDTH,0}, {SQUARE_WIDTH,SQUARE_WIDTH},{2*SQUARE_WIDTH, SQUARE_WIDTH},{0,0}};
    public static final int[][] L_PIECE_COORDS = {{0, SQUARE_WIDTH}, {0,0},{SQUARE_WIDTH, 0},  {0, 2*SQUARE_WIDTH}};
    public static final int[][] J_PIECE_COORDS = {{SQUARE_WIDTH, SQUARE_WIDTH}, {SQUARE_WIDTH,0},{0, 0}, {SQUARE_WIDTH, 2*SQUARE_WIDTH}};

}
