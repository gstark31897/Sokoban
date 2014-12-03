/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class Map {
    static int[][] map;
    static int goals;
    static Player player;
    static ArrayList<Box> boxes;
    static String nextMap;
    static String currentMap;
    public static void load(String mapPath) {
        try {
            Scanner scan = new Scanner(new File("src/res/" + mapPath));
            currentMap = mapPath;
            nextMap = scan.nextLine();
            System.out.println(nextMap);
            player = new Player(scan.nextInt(), scan.nextInt());
            System.out.println(player.x + "," + player.y);
            boxes = new ArrayList<Box>();
            int b = scan.nextInt();
            while(b != -1) {
                boxes.add(new Box(b, scan.nextInt()));
                System.out.println(b + "," + boxes.get(boxes.size()-1).y);
                b = scan.nextInt();
            }
            
            map = new int[scan.nextInt()][scan.nextInt()];
            
            for(int y = 0; y < map.length; y++) {
                for(int x = 0; x < map[y].length; x++) {
                    int i = scan.nextInt();
                    map[y][x] = i;
                }
            }
            
            scan.close();
            
            goals = getGoals();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update() {
        player.update();
        
        int g = 0;
        for(Box b: boxes) {
            b.update();
            if(isGoal(b.x, b.y))
                g++;
        }
        
        if(g == goals)
            load(nextMap);
        
        if(Keyboard.isKeyDown(Keyboard.KEY_R))
            load(currentMap);
    }
    
    public static boolean isClear(int x, int y) {
        return map[y][x] != 2;
    }
    
    public static boolean isGoal(int x, int y) {
        return map[y][x] == 3;
    }
    
    public static int getGoals() {
        int g = 0;
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                if(map[y][x] == 3)
                    g++;
            }
        }
        
        return g;
    }
    
    public static void render() {
        glBegin(GL_QUADS);
        
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                switch(map[y][x]) {
                    case 1:
                        glColor3f(1, 0, 0);
                        glVertex3f(x, y, 0);
                        glVertex3f(x, y+1, 0);
                        glVertex3f(x+1, y+1, 0);
                        glVertex3f(x+1, y, 0);
                        break;
                        
                    case 2:
                        glColor3f(1, 1, 0);
                        glVertex3f(x, y, 0);
                        glVertex3f(x, y+1, 0);
                        glVertex3f(x+1, y+1, 0);
                        glVertex3f(x+1, y, 0);
                        break;
                        
                    case 3:
                        glColor3f(0, 1, 1);
                        glVertex3f(x, y, 0);
                        glVertex3f(x, y+1, 0);
                        glVertex3f(x+1, y+1, 0);
                        glVertex3f(x+1, y, 0);
                        break;
                }
            }
        }
        
        player.render();
        for(Box b: boxes)
            b.render();
        
        glEnd();
    }
}
