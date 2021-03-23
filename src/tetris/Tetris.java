
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
    Sprite piece, gridSpr, rectSpr;
    Canvas c;


    BufferedImage Grid = ResourceLoader.getImage("tetrisImages/blackGrid.jpg");
    BufferedImage Rect = ResourceLoader.getImage("tetrisImages/blackRect.jpg");
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
        piece = new Sprite(LPiece, 0, 0, moveDist*3, moveDist*2, 1);

        //grid is 29 tall by 52 wide
        gridSpr = new Sprite(Grid, 0, 0, moveDist*52, moveDist*29, 0);


    }




    @Override
    public void start() {
        x = 0;
        y = 100;
        c.put(piece, "LPiece");
        c.put(gridSpr, "Grid");

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


