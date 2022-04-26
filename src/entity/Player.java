package entity;

import Appen.frame;
import Controller.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    frame gp;
    Controller controller;


    public Player(frame gp, Controller controller) {
        this.gp = gp;
        this.controller = controller;
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage(){
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/assets/player.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/assets/player.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/assets/player.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/assets/player.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDefaultValues() {
      x = 50;
      y = 50;
      speed = 4;
      direction = "up";
    }
    public void update() {
        if(controller.up == true) {
            direction ="up";
            y -= speed;
        }
        else if(controller.down == true) {
            direction = "down";
            y +=speed;
        }
        else if(controller.left == true) {
            direction = "left";
            x += speed;
        }
        else if(controller.right == true){
            direction = "right";
            x -=speed;
        }



    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "up":
            image = up;
            break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image =right;
                break;
        }
        g2.drawImage(image,x,y,gp.tilesize,gp.tilesize,null);

    }
}
