/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Octalus
 */
public class Game {
    static {
        Map.load("level_5.txt");
    }
    
    public static void update() {
        Map.update();
    }
    
    public static void render() {
        glPushMatrix();
        glScalef(32, 32, 32);
        glTranslatef(-Map.player.xp+Display.getWidth()/64-0.5f, -Map.player.yp+Display.getHeight()/64-0.5f, 0);
        Map.render();
        glPopMatrix();
    }
}
