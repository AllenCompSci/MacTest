package test.collection;

import java.util.ArrayList;

/**
 * Created by theshrewedshrew on 3/13/17.
 */
public class Board {
    boolean boardset [][] = {
            {true, false, false, false, false},
            {true,  true, false, false, false},
            {true,  true,  true, false, false},
            {true,  true,  true,  true, false},
            {true,  true,  true,  true,  true}
    };
    boolean empty [][] = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    };
    /**
     *
     *      0    1    2    3    4
     * 0    V
     * 1    V    V
     * 2    V    V    V
     * 3    V    V    V    V
     * 4    V    V    V    V    V
     *
     *
     * CHECK SURROUNDING
     *
     *           5        0
     *       4      PEG       1
     *           3       2
     *
     *      ASSUME   boardset[H][W]
     *          HEX PATTERN
     *
     *               00
     *             10  11
     *           20  21  22
     *         30  31  32  33
     *       40  41  42  43  44
     *     CHECK THE PEG PATTERN
     *   0 - [H-1][W+0] -> [H-2][W+0]
     *   1 - [H+0][W+1] -> [H+0][W+2]
     *   2 - [H+1][W+1] -> [H+2][W+2]
     *   3 - [H+1][W+0] -> [H+2][W+0]
     *   4 - [H+0][W-1] -> [H+0][W-2]
     *   5 - [H-1][W-1] -> [H-2][W-2]
     *   This goes through the possible checks, so we only have to check these when they are within bounds to find holes.
     *
     */
    private int HEIGHT = 5;
    private int WIDTH = 5;
    private int W, H; // Current Position of Peg
    public Board(int initialH, int initialW){
        W = initialW; // X
        H = initialH; // Y
        empty[H][W] = true;

    }
    public boolean canJump(int pegH, int pegW){

    }
    public boolean peg0(int pegH, int pegW){
        if(pegH > 1)
            return boardset[pegH-2][pegW] && empty[pegH-2][pegW];
        return false;
    }
    public boolean peg1(int pegH, int pegW){
        if(pegW < 3){
            return boardset[pegH][pegH+2] && empty[pegH][pegW+2];
        }
        return false;
    }

}
