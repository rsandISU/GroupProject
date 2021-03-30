package pacman;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
import engine.SpriteClickable;
import util.ResourceLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class PacmanTest implements GameElement, MouseMotionListener {
    Sprite backgroundspr;
    Sprite boardspr;
    Sprite spr;
    Sprite block;
    Canvas c;


    //Pacman vars
    int x = 935;
    int y = 590;
    int width = 45;
    int height = 45;
    Rectangle pacmanR = new Rectangle(x, y, width, height);
    int speed = 3;


    //Block vars
    //int blockx = 300;
    //int blocky = 540;
    //int blockwidth = 200;
    //int blockheight = 200;
    //Rectangle blockR = new Rectangle(blockx, blocky, blockwidth, blockheight);


    //Block1
    Rectangle block1R = new Rectangle(628, 196, 82, 52);

    //Block2
    Rectangle block2R = new Rectangle(772, 196, 114, 52);

    //Block3
    Rectangle block3R = new Rectangle(1040, 196, 114, 52);

    //Block4
    Rectangle block4R = new Rectangle(1216, 196, 82, 52);

    //Block5
    Rectangle block5R = new Rectangle(628, 308, 80, 23);

    //Block6
    Rectangle block6R = new Rectangle(1216, 308, 80, 23);

    //Block7
    Rectangle block7R = new Rectangle(771, 563, 27, 107);

    //Block8
    Rectangle block8R = new Rectangle(1126, 563, 26, 107);

    //Block9
    Rectangle block9R = new Rectangle(771, 732, 117, 20);

    //Block10
    Rectangle block10R = new Rectangle(1038, 732, 114, 20);



    //Put buffered images here
    BufferedImage neutral = ResourceLoader.getImage("pacman/circle.png");
    BufferedImage right = ResourceLoader.getImage("pacman/right.png");
    BufferedImage left = ResourceLoader.getImage("pacman/left.png");
    BufferedImage up = ResourceLoader.getImage("pacman/up.png");
    BufferedImage down = ResourceLoader.getImage("pacman/down.png");
    BufferedImage blockpic = ResourceLoader.getImage("pacman/block.png");
    BufferedImage background = ResourceLoader.getImage("pacman/background.png");
    BufferedImage board = ResourceLoader.getImage("pacman/board.png");


    public PacmanTest(Canvas c) {
        this.c = c;

        //Background
        backgroundspr = new Sprite(background, 528, 0, 864, 1080, 0);

        //Board
        boardspr = new Sprite(board, 540, 116, 840, 887, 1);

        //Pacman
        spr = new Sprite(neutral, x, y, width, height, 2);

        //Block Test
        //block1 = new Sprite(blockpic, blockx, blocky, blockwidth, blockheight, 2);
    }

    @Override
    public void start() {
        c.put(backgroundspr, "background");
        c.put(boardspr, "board");
        c.put(spr, "FUNNY");
        //c.put(block, "FUNNY2");



    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

        pacmanR = new Rectangle(x, y, width, height);
        //blockR = new Rectangle(blockx, blocky, blockwidth, blockheight);

        //Pacman Movement
        if(c.getKeysDown().contains('s') && !blockCollideTestY(true)){
            y = y + speed;
            //spr.setImage(down);
        } else if(c.getKeysDown().contains('w') && !blockCollideTestY(false)){
            y = y - speed;
            //spr.setImage(up);
        } else if(c.getKeysDown().contains('d') && !blockCollideTestX(true)){
            x = x + speed;
            //spr.setImage(right);
        } else if(c.getKeysDown().contains('a') && !blockCollideTestX(false)){
            x = x - speed;
            //spr.setImage(left);
        } //else if(!c.getKeysDown().contains('a')&&!c.getKeysDown().contains('d')&&!c.getKeysDown().contains('s')&&!c.getKeysDown().contains('w')){
            //spr.setImage(neutral);

        //}

        spr.setX((int) x);
        spr.setY((int) y);

        //For debugging and setting coordinate positions
        //Point p = MouseInfo.getPointerInfo().getLocation();
        //p.translate(459, 263);
        //System.out.println(p.getLocation());
        //System.out.println(x + " " + y);


    }

    //Returns true if pacman can't move without colliding in the desired x direction
    public boolean blockCollideTestX(boolean direction){

        Rectangle pacmanXT = new Rectangle(x+speed, y, width, height);
        Rectangle pacmanXF = new Rectangle(x-speed, y, width, height);
        //Rectangle pacmanXT1 = new Rectangle(x+1, y, width, height);
        //Rectangle pacmanXF1 = new Rectangle(x-1, y, width, height);

        //True direction is up and false is down
        return Collision(direction, pacmanXT, pacmanXF);
    }

    //Returns true if pacman can't move without colliding in the desired y direction
    public boolean blockCollideTestY(boolean direction){

        Rectangle pacmanYT = new Rectangle(x, y+speed, width, height);
        Rectangle pacmanYF = new Rectangle(x, y-speed, width, height);

        //True direction is up and false is down
        return Collision(direction, pacmanYT, pacmanYF);
    }

    private boolean Collision(boolean direction, Rectangle pacmanXT, Rectangle pacmanXF) {
        if(direction){
            if (Rectangle(pacmanXT)) return true;

        }else {

            if (Rectangle(pacmanXF)) return true;
        }

        return false;
    }

    private boolean Rectangle(Rectangle pacmanXT) {
        if(pacmanXT.intersects(block1R)||pacmanXT.intersects(block2R)||pacmanXT.intersects(block3R)||pacmanXT.intersects(block4R)||pacmanXT.intersects(block5R)||pacmanXT.intersects(block6R)||pacmanXT.intersects(block7R)||pacmanXT.intersects(block8R)||pacmanXT.intersects(block9R)||pacmanXT.intersects(block10R)){


            return true;
        }
        return false;
    }





    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        //Coordinates for testing
        //int x=e.getX();
        //int y=e.getY();
        //System.out.println(x+","+y);//these co-ords are relative to the component
    }




}

