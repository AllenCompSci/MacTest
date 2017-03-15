package test.collection;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

/**
 * Created by theshrewedshrew on 3/13/17.
 */
public class Board {
    boolean listJump[] = {false, false, false, false, false, false};
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
    int pegsLeft;
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
     *
     *
     */
    private int HEIGHT = 5;
    private int WIDTH = 5;
    private int W, H; // Current Position of Peg
    public Board(int initialH, int initialW){
        W = initialW; // X
        H = initialH; // Y
        empty[H][W] = true;
        pegsLeft = 14;
        setListJump();
    }
    public Board(boolean duplicate[][], int fromX, int fromY, int toX, int toY){
        pegsLeft = 15;
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++) {
                empty[i][j] = duplicate[i][j];
                if(empty[i][j])
                    pegsLeft--;
            }

        empty[fromX][fromY] = true;
        //System.out.println("FX : " + fromX + " FY : " + fromY);
        // This peg is going to move not disappear
        int dX = (toX-fromX)/2;
        int dY = (toY-fromY)/2;
        //System.out.println("tX : " + toX + "tY : " + toY);
        //System.out.println("dX : " + dX + "dY : " + dY);
        empty[fromX + dX][fromY + dY] = true;
        pegsLeft--; // This peg is going to disappear
        empty[toX][toY] = false; // Replaces the peg that jumps
        setListJump();


    }
    public Board(boolean duplicate[][]){
        pegsLeft = 15;
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++) {
                empty[i][j] = duplicate[i][j];
                if(empty[i][j])
                    pegsLeft--;
            }
        setListJump();
    }
    public boolean [][] getEmpty(){return empty;}
    public boolean [] getListJump(){return listJump;}
    public void setListJump(){
        for(int i = 0; i  < 6; i ++)
            listJump[i] = false;
    }
    public boolean canJump(int pegH, int pegW){
        if(empty[pegH][pegW])
            return false;
        return peg0(pegH, pegW) || peg1(pegH, pegW) || peg2(pegH, pegW) || peg3(pegH, pegW) || peg4(pegH, pegW) || peg5(pegH, pegW);
    }
    public boolean peg0(int pegH, int pegW){
        if(peg(pegH, pegW, -1, 0))
            return listJump[0] = true;
        return false;
    }
    public boolean peg1(int pegH, int pegW){
        if(peg(pegH, pegW, 0, 1))
            return listJump[1] = true;
        return false;
    }
    public boolean peg2(int pegH, int pegW){
        if( peg(pegH, pegW, 1,1))
            return listJump[2] = true;
        return false;
    }
    public boolean peg3(int pegH, int pegW){
        if(peg(pegH, pegW, 1,0))
            return listJump[3] = true;
        return false;
    }
    public boolean peg4(int pegH, int pegW){
        if( peg(pegH, pegW, 0, -1))
            return listJump[4] = true;
        return false;
    }
    public boolean peg5(int pegH, int pegW){
        if( peg(pegH, pegW, -1,-1))
            return listJump[5] = true;
        return false;
    }
    public boolean exists(int h,  int h2, int w , int w2){
        if((h + h2) >= 0 && (h + h2) < HEIGHT){
            if((w + w2) >= 0 && (w+w2) < WIDTH) {
                //System.out.println((h+h2) + ", " + (w+w2));
                return true;
            }
        }
        return false;
    }
    public boolean peg(int pegH, int pegW, int dH, int dW){
        if(exists(pegH, dH*2, pegW, dW*2))
            if(boardset[pegH+dH][pegW+dW] && !empty[pegH+dH][pegW+dW])
                return boardset[pegH+dH*2][pegW+dW*2] && empty[pegH+dH*2][pegW+dW*2];
        return false;
        }
    public int pegsLeft(){
        return pegsLeft;
    }
    @Override
    public String toString(){
        String PEGBOARD = "";
        for(int row = 0; row < HEIGHT; row++)
        {
            PEGBOARD += repeat(' ', 4 - row);
            for(int col = 0; col < WIDTH && (boardset[row][col]); col++){
                if(empty[row][col])
                    PEGBOARD += "* ";
                else
                    PEGBOARD += "o ";
            }
            PEGBOARD += repeat(' ', 4 - row);
            PEGBOARD += "\n";
        }
        return PEGBOARD;
    }

    private String repeat(char x, int n){
        if(n < 0){
            return "";
        }
        return x + repeat(x, n-1);
    }
}
