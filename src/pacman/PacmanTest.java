package pacman;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
import engine.SpriteClickable;
import util.ResourceLoader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class PacmanTest implements GameElement, MouseMotionListener {

    Sprite spr;
    Sprite block1;
    Canvas c;


    //Pacman vars
    int x = 0;
    int y = 0;
    int width = 200;
    int height = 200;
    new Rectangle r1 = (10, 20, width, height);
    int speed = 3;


    //Block1 vars
    int block1x = 500;
    int block1y = 200;
    int block1width = 200;
    int block1height = 200;

    //Put buffered images here
    BufferedImage neutral = ResourceLoader.getImage("pacman/circle.png");
    BufferedImage right = ResourceLoader.getImage("pacman/right.png");
    BufferedImage left = ResourceLoader.getImage("pacman/left.png");
    BufferedImage up = ResourceLoader.getImage("pacman/up.png");
    BufferedImage down = ResourceLoader.getImage("pacman/down.png");
    BufferedImage block = ResourceLoader.getImage("pacman/block.png");


    public PacmanTest(Canvas c) {
        this.c = c;
        //Pacman
        spr = new Sprite(neutral, 0, 200, 200, 200, 0);

        //Block Test
        block1 = new Sprite(block, block1x, block1y, block1width, block1height, 0);
    }

    @Override
    public void start() {
        x = 0;
        y = 100;
        c.put(spr, "FUNNY");
        c.put(block1, "FUNNY2");

    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {


        //Pacman Movement
        if(c.getKeysDown().contains('s')){
            y = y + speed;
            spr.setImage(down);
        }

        if(c.getKeysDown().contains('w')){
            y = y - speed;
            spr.setImage(up);
        }

        if(c.getKeysDown().contains('d')){
            x = x + speed;
            spr.setImage(right);
        }

        if(c.getKeysDown().contains('a')){
            x = x - speed;
            spr.setImage(left);
        }

        if(!c.getKeysDown().contains('a')&&!c.getKeysDown().contains('d')&&!c.getKeysDown().contains('s')&&!c.getKeysDown().contains('w')){
            spr.setImage(neutral);

        }

        spr.setX((int) x);
        spr.setY((int) y);


        //Block detection
        if()


    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

