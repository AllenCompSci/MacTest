package test.collection;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by taylor hudson on 3/15/2017.
 */
public class Paths {
    ArrayList<Board> solution;
    ArrayList<Point> from;
    ArrayList<Point> to;
    int pegsLeft = 15;
    public Paths(Board t){
        from = new ArrayList<>();
        to = new ArrayList<>();
        solution = new ArrayList<>();
        solution.add(t);
        pegsLeft = t.pegsLeft();
    }
    public void add(Board t){
        solution.add(t);
        pegsLeft = t.pegsLeft();
    }
    public ArrayList<Board> getSolution(){
        return solution;
    }
    public boolean canMove(){
        boolean move = false;
        int index = solution.size() - 1;
        if(pegsLeft == 1)
            return false;
        for(int i = 0; i < 15; i ++) {
            solution.get(index).setListJump();
            boolean test = solution.get(index).canJump(Game.pegX[i], Game.pegY[i]);
            if(test){
                move = true;
                boolean [] list = solution.get(index).getListJump();
                /**
                 *   0 - [H-1][W+0] -> [H-2][W+0]
                 *   1 - [H+0][W+1] -> [H+0][W+2]
                 *   2 - [H+1][W+1] -> [H+2][W+2]
                 *   3 - [H+1][W+0] -> [H+2][W+0]
                 *   4 - [H+0][W-1] -> [H+0][W-2]
                 *   5 - [H-1][W-1] -> [H-2][W-2]
                 */
                for(int j = 0; j < 6; j++){
                    if(list[j]){
                        from.add(new Point(Game.pegX[i], Game.pegY[i]));
                        to.add(new Point(Game.pegX[i] + Game.jumpX[j], Game.pegY[i] + Game.jumpY[j]));
                    }
                }
            }
        }
        return move;
    }
    public String toString() {
        String str = "";
        for(Board e : solution)
            str += "\n" + e.toString() + "\n";
        return str;
    }
    public int getPegsLeft(){
        return pegsLeft;
    }
    public int numMoves(){
        return from.size();
    }
    public ArrayList<Point> getFrom(){
        return from;
    }
    public ArrayList<Point> getTo(){
        return to;
    }
}
