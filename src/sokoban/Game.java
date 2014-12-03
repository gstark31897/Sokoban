/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;

/**
 *
 * @author Octalus
 */
public class Game {
    static {
        Map.load("level_1.txt");
    }
    
    public static void update() {
        Map.update();
    }
    
    public static void render() {
        glPushMatrix();
        glScalef(8, 8, 8);
        Map.render();
        glPopMatrix();
    }
}
