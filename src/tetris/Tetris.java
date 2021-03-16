
package tetris;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;

import util.ResourceLoader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Tetris implements GameElement, MouseMotionListener {
    Sprite spr;
    Canvas c;

    BufferedImage LPiece = ResourceLoader.getImage("tetrisImages/LPiece.png");

    //Pacman vars
    int x = 0;
    int y = 0;
    int width = 200;
    int height = 200;
    //new Rectangle r1 = (10, 20, width, height);
    int moveDist = 50;
    int drop = 0;
    int move = 0;
    boolean moveRight, moveLeft, moveDown;

//    double x = 0;
//    double y = 0;

    double mx = 10;
    double my = 0;



    public Tetris(Canvas c){
        this.c = c;

        //each block should have a side length of moveDist
        spr = new Sprite(LPiece, 200, 0, moveDist*3, moveDist*2, 0);


    }




    @Override
    public void start() {
        x = 0;
        y = 100;
        c.put(spr, "LPiece");

    }

    @Override
    public void stop() {

    }




    @Override
    public void update() {


        drop += 1;
        move += 1;


        //Pacman Movement

        //give user time to lift up the key after they pressed it down (drop > 8)
        //set drop to zero to reset drop timer
        if(c.getKeysDown().contains('s') && drop > 8){
            moveDown = true;
            drop = 0;
        }


        else if(c.getKeysDown().contains('d') && move > 4){
            moveRight = true;
        }

        else if(c.getKeysDown().contains('a') && move > 4){
            moveLeft = true;
        }



        spr.setX(x);
        spr.setY(y);

        if(move>10){
            move=0;
            if(moveRight){
                moveRight = false;
                x = x + moveDist;
            }

            if(moveLeft){
                moveLeft = false;
                x = x - moveDist;
            }

            if(moveDown){
                moveDown = false;
                y = y + moveDist;
            }

        }

        if(drop>50){
            drop = 0;
            y = y + 50;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}


