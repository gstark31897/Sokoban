/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static sokoban.Map.boxes;

/**
 *
 * @author Octalus
 */
public class Player {
    int x, y;
    int moveTime;
    int tx, ty;
    float xp, yp;
    int moveEllapsed;
    
    public Player(int x, int y) {
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
            input();
        }else{
            translate();
        }
    }
    
    public void input() {
        if(Keyboard.isKeyDown(Keyboard.KEY_UP) && Map.isClear(x, y-1)) {
            ty--;
            moveEllapsed = moveTime;
            for(Box b: boxes) {
                if(b.y == y-1 && b.x == x) {
                    if(!b.moveUp()) {
                        ty++;
                    }
                }
            }
            return;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && Map.isClear(x, y+1)) {
            ty++;
            moveEllapsed = moveTime;
            for(Box b: boxes) {
                if(b.y == y+1 && b.x == x) {
                    if(!b.moveDown()) {
                        ty--;
                    }
                }
            }
            return;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Map.isClear(x+1, y)) {
            tx++;
            moveEllapsed = moveTime;
            for(Box b: boxes) {
                if(b.y == y && b.x == x+1) {
                    if(!b.moveRight()) {
                        tx--;
                    }
                }
            }
            return;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Map.isClear(x-1, y)) {
            tx--;
            moveEllapsed = moveTime;
            for(Box b: boxes) {
                if(b.y == y && b.x == x-1) {
                    if(!b.moveLeft()) {
                        tx++;
                    }
                }
            }
            return;
        }
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
        glColor3f(0, 1, 0);
        glVertex3f(xp, yp, 0);
        glVertex3f(xp, yp+1, 0);
        glVertex3f(xp+1, yp+1, 0);
        glVertex3f(xp+1, yp, 0);
    }
}
