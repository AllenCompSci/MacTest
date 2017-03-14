package test.collection;

import java.awt.*;

/**
 * Created by theshrewedshrew on 2/27/17.
 */
public class Node{
    Point position;

    public Node(int x, int y){
        position.setLocation(x,y);
    }
    public Node(float x, float y){
        position.setLocation((int)(x/32), (int)(y/32));
    }
    public int getX(){
        return (int)position.getX();
    }
    public int getY(){
        return (int)position.getY();
    }
    @Override
    public boolean equals(Node node){
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
