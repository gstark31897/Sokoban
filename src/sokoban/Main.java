/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Octalus
 */
public class Main {
    static int width = 800, height = 600;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(width, height));
        Display.create();
        
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, width, height, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glClearColor(0, 0, 0, 0);
        
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            Game.update();
            Game.render();
            
            Display.update();
            Display.sync(60);
        }
    }
    
}
