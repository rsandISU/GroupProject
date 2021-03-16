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
    Sprite backgroundspr;
    Sprite spr;
    Sprite block1;
    Canvas c;


    //Pacman vars
    int x = 0;
    int y = 0;
    int width = 55;
    int height = 55;
    Rectangle pacmanR = new Rectangle(x, y, width, height);
    int speed = 3;


    //Block1 vars
    int block1x = 300;
    int block1y = 540;
    int block1width = 200;
    int block1height = 200;
    Rectangle block1R = new Rectangle(block1x, block1y, block1width, block1height);



    //Put buffered images here
    BufferedImage neutral = ResourceLoader.getImage("pacman/circle.png");
    BufferedImage right = ResourceLoader.getImage("pacman/right.png");
    BufferedImage left = ResourceLoader.getImage("pacman/left.png");
    BufferedImage up = ResourceLoader.getImage("pacman/up.png");
    BufferedImage down = ResourceLoader.getImage("pacman/down.png");
    BufferedImage block = ResourceLoader.getImage("pacman/block.png");
    BufferedImage background = ResourceLoader.getImage("pacman/background.png");


    public PacmanTest(Canvas c) {
        this.c = c;

        //Background
        backgroundspr = new Sprite(background, 528, 0, 864, 1080, 0);

        //Pacman
        spr = new Sprite(right, x, y, width, height, 1);

        //Block Test
        block1 = new Sprite(block, block1x, block1y, block1width, block1height, 1);
    }

    @Override
    public void start() {
        c.put(backgroundspr, "background");
        c.put(spr, "FUNNY");
        c.put(block1, "FUNNY2");
        x = 0;
        y = 200;


    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

        pacmanR = new Rectangle(x, y, width, height);
        block1R = new Rectangle(block1x, block1y, block1width, block1height);

        //Pacman Movement
        if(c.getKeysDown().contains('s') && !blockCollideTestY(true)){
            y = y + speed;
            spr.setImage(down);
        } else if(c.getKeysDown().contains('w') && !blockCollideTestY(false)){
            y = y - speed;
            spr.setImage(up);
        } else if(c.getKeysDown().contains('d') && !blockCollideTestX(true)){
            x = x + speed;
            spr.setImage(right);
        } else if(c.getKeysDown().contains('a') && !blockCollideTestX(false)){
            x = x - speed;
            spr.setImage(left);
        } else if(!c.getKeysDown().contains('a')&&!c.getKeysDown().contains('d')&&!c.getKeysDown().contains('s')&&!c.getKeysDown().contains('w')){
            spr.setImage(neutral);

        }

        spr.setX((int) x);
        spr.setY((int) y);





    }

    //Returns true if pacman can't move without colliding in the desired x direction
    public boolean blockCollideTestX(boolean direction){

        Rectangle pacmanXT = new Rectangle(x+speed, y, width, height);
        Rectangle pacmanXF = new Rectangle(x-speed, y, width, height);

        //True direction is up and false is down
        if(direction){
            if(pacmanXT.intersects(block1R)){


                return true;
            }

        }else {

            if(pacmanXF.intersects(block1R)){


                return true;
            }
        }

        return false;
    }


    //Returns true if pacman can't move without colliding in the desired y direction
    public boolean blockCollideTestY(boolean direction){

        Rectangle pacmanYT = new Rectangle(x, y+speed, width, height);
        Rectangle pacmanYF = new Rectangle(x, y-speed, width, height);

        //True direction is up and false is down
        if(direction){
            if(pacmanYT.intersects(block1R)){


                return true;
            }

        }else {

            if(pacmanYF.intersects(block1R)){


                return true;
            }
        }

        return false;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

