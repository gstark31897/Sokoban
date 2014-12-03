/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class Box {
    int x, y;
    int moveTime;
    int tx, ty;
    float xp, yp;
    int moveEllapsed;
    
    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        xp = x;
        yp = y;
        tx = x;
        ty = y;
        moveTime = 10;
        moveEllapsed = 0;
    }
    
    public void update() {
        if(moveEllapsed == 0) {
            x = (int)(xp+0.5f);
            y = (int)(yp+0.5f);
            xp = x;
            yp = y;
        }else{
            translate();
        }
    }
    
    public boolean moveUp() {
        if(!Map.isClear(x, y-1))
            return false;
        for(Box b: Map.boxes)
            if(b.y == y - 1 && b.x == x)
                return false;
        
        ty--;
        moveEllapsed = moveTime;
        return true;
    }
    
    public boolean moveDown() {
        if(!Map.isClear(x, y+1))
            return false;
        for(Box b: Map.boxes)
            if(b.y == y + 1 && b.x == x)
                return false;
        
        ty++;
        moveEllapsed = moveTime;
        return true;
    }
    
    public boolean moveLeft() {
        if(!Map.isClear(x-1, y))
            return false;
        for(Box b: Map.boxes)
            if(b.x == x - 1 && b.y == y)
                return false;
        
        tx--;
        moveEllapsed = moveTime;
        return true;
    }
    
    public boolean moveRight() {
        if(!Map.isClear(x+1, y))
            return false;
        for(Box b: Map.boxes)
            if(b.x == x + 1 && b.y == y)
                return false;
        
        tx++;
        moveEllapsed = moveTime;
        return true;
    }
    
    public void translate() {
        if(tx > xp) {
            xp+=1.0f/moveTime;
        }
        
        if(tx < xp) {
            xp-=1.0f/moveTime;
        }
        
        if(ty > yp) {
            yp+=1.0f/moveTime;
        }
        
        if(ty < yp) {
            yp-=1.0f/moveTime;
        }
        
        moveEllapsed--;
    }
    
    public void render() {
        glColor3f(1, 1, 1);
        glVertex3f(xp, yp, 0);
        glVertex3f(xp, yp+1, 0);
        glVertex3f(xp+1, yp+1, 0);
        glVertex3f(xp+1, yp, 0);
    }
}
