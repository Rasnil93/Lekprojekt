package Appen;


import Controller.Controller;
import Map.TileManager;
import entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class frame extends JPanel implements Runnable {
    //Detta är pixlar för saker så som gubben
    final int tile = 16;
    final int scale = 3;
    public final int tilesize = tile * scale;
    public final int maxscreencol = 10;
    public final int maxscreenrow = 10;
    public final int screenWidth = tilesize * maxscreencol;
    public final int screenHeight = tilesize * maxscreenrow;
    //vilken fps
    int FPS = 60;
    TileManager tileMan = new TileManager(this);
    Controller controller = new Controller();
    Thread gameThread;
    Player player = new Player(this,controller);


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
        player.update();

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D)g;
        tileMan.draw(g2);
        player.draw(g2);
        g2.dispose();



    }
}
