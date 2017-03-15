package test.collection;

import java.util.ArrayList;

/**
 * Created by taylor hudson on 3/15/2017.
 */
public class Game {

    ArrayList<Paths> solution;
    /**
     *               00
     *             10  11
     *           20  21  22  from peg 00 to peg 22 in numerical order makes up all the combonations of pegs that could be missing. so from 0 - 5 or i = 0; i  < 6
     *         30  31  32  33
     *       40  41  42  43  44
     *     CHECK THE PEG PATTERN
     */
    static int pegX [] = {0,1,1,2,2,2,3,3,3,3,4,4,4,4,4};
    static int pegY [] = {0,0,1,0,1,2,0,1,2,3,0,1,2,3,4};
    static int jumpX [] = {-2,0,2,2,0,-2};
    static int jumpY [] = {0,2,2,0,-2,-2};
    public Game(){
        solution = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            if(i != 2)
                solution.add(new Paths(new Board(pegX[i], pegY[i])));
        runSol();
        System.out.println(solution);
    }

    public void runSol(){
        boolean update = false;
        do {
            int kMEAN = solution.size();

            for (int i = 0; i < kMEAN; i++) {

                if (solution.get(i).canMove()) {
                    // System.out.println("FROM : " + solution.get(i).getFrom());
                    //  System.out.println("TO : " + solution.get(i).getTo());

                    for (int j = 0; j < solution.get(i).numMoves(); j++) {
                        int index = solution.size();
                        for (int k = 0; k < solution.get(i).getSolution().size(); k++) {
                            if (k == 0)
                                solution.add(new Paths(solution.get(i).getSolution().get(k)));
                            else
                                solution.get(index).add(new Board(solution.get(i).getSolution().get(k).getEmpty()));
                        }
                        Board temp = solution.get(i).getSolution().get(solution.get(i).getSolution().size() - 1);
                        solution.get(index).add(new Board(temp.getEmpty(), solution.get(i).getFrom().get(j).x, solution.get(i).getFrom().get(j).y, solution.get(i).getTo().get(j).x, solution.get(i).getTo().get(j).y));

                    }
                    solution.remove(i);
                    i--;
                    kMEAN--;
                } else if (solution.get(i).pegsLeft == 1) {

                } else {
                    solution.remove(i);
                    i--;
                    kMEAN--;
                }

            }
            for(int i = 0; i < solution.size(); i++){
                for(int j = i+1; j < solution.size(); j++){
                    if(solution.get(i).getSolution().get(solution.get(i).getSolution().size()-1).compareBoard(solution.get(j).getSolution().get(solution.get(j).getSolution().size()-1).getEmpty()))
                        solution.remove(j);
                }
            }


            update = false;
            for(int i = 0; i < solution.size(); i ++){
                if(solution.get(i).getSolution().get(solution.get(i).getSolution().size()-1).pegsLeft > 1){
                    update = true;
                }
            }
        }while (update);
        System.out.println(solution);
        System.out.println(solution.size());
    }


}
