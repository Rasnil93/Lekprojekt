package Appen;


import Controller.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.*;

import java.awt.*;


public class frame extends JPanel implements Runnable {
    //Detta är pixlar för saker så som gubben
    final int tile = 16;
    final int scale = 3;
    final int tilesize = tile * scale;
    final int maxscreencol = 10;
    final int maxscreenrow = 10;
    final int screenWidth = tilesize * maxscreencol;
    final int screenHeight = tilesize * maxscreenrow;
    int FPS = 60;
    Controller controller = new Controller();
    Thread gameThread;

    //starter position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public frame() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controller);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInter = 1000000000/FPS;
        double nextDraw = System.nanoTime() + drawInter;

        while (gameThread != null) {


        update();

        repaint();

            try {
                double remaining = nextDraw - System.nanoTime();
                remaining = remaining/1000000;

                if(remaining < 0) {
                    remaining = 0;
                }
                Thread.sleep((long) remaining);

                nextDraw +=drawInter;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {

        if(controller.up == true) {
            playerY -= playerSpeed;
        }
        else if(controller.down == true) {
            playerY +=playerSpeed;
        }
        else if(controller.left == true) {
            playerX -= playerSpeed;
        }
        else if(controller.right == true){
            playerX +=playerSpeed;
        }


    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D)g;
        g2.setColor(Color.CYAN);
        g2.fillRect(playerX,playerY,tilesize,tilesize);
        g2.dispose();



    }
}
