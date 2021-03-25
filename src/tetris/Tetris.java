
package tetris;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;

import util.ResourceLoader;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Tetris implements GameElement, MouseMotionListener, KeyListener {
    Sprite piece, gridSpr, left, right, bottom;
    Canvas c;


    BufferedImage Grid = ResourceLoader.getImage("tetrisImages/blackGrid.jpg");
    BufferedImage LPiece = ResourceLoader.getImage("tetrisImages/LPiece.png");

    //Tetris background
    BufferedImage LeftTB = ResourceLoader.getImage("tetrisImages/leftTetrisBackground.jpg");
    BufferedImage RightTB = ResourceLoader.getImage("tetrisImages/rightTetrisBackground.jpg");
    BufferedImage BottomTB = ResourceLoader.getImage("tetrisImages/bottomTetrisBackground.jpg");


    //Pacman vars
    int x = 0;
    int y = 0;
    int moveDist = 50;
    int drop = 0;





    public Tetris(Canvas c){
        this.c = c;

        //each block should have a side length of moveDist
        piece = new Sprite(LPiece, 0, 0, moveDist*3, moveDist*2, 10);

        //grid is 29 tall by 52 wide
        gridSpr = new Sprite(Grid, 0, 0, moveDist*52, moveDist*29, 0);

        //background is 38.5 wide by 21.5 tall
        left = new Sprite(LeftTB, 0, 0, moveDist*10, moveDist*22,1);
        right = new Sprite(RightTB, moveDist*28, 0, moveDist*11, moveDist*22,2);
        bottom = new Sprite(BottomTB, moveDist*10, moveDist*21, moveDist*18, moveDist*4,4);


    }




    @Override
    public void start() {
        x = 0;
        y = 100;
        c.put(piece, "LPiece");
        c.put(gridSpr, "Grid");
        c.put(left, "LeftTB");
        c.put(right, "RightTB");
        c.put(bottom, "BottomTB");

    }

    @Override
    public void stop() {

    }




    @Override
    public void update() {

        drop += 1;

        piece.setX(x);
        piece.setY(y);

        //Tetris piece falling mechanism
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


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {

            case KeyEvent.VK_LEFT: x = x - moveDist;;
                break;

            case KeyEvent.VK_RIGHT: x = x + moveDist;
                break;

            case KeyEvent.VK_DOWN: y = y + moveDist;
                drop = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


