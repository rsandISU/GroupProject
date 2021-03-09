
package tetris;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;

import util.ResourceLoader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class tetris implements GameElement, MouseMotionListener {
    Sprite spr;
    Canvas c;

    BufferedImage LPiece = ResourceLoader.getImage("tetrisImages/LPiece.png");

    double x = 0;
    double y = 0;

    double mx = 10;
    double my = 0;



    public tetris(Canvas c){
        this.c = c;

        spr = new Sprite(LPiece, 0, 0, 100, 100, 0);


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

        x = x + mx;
        y = y + my;

        my = my + 0.5;

        mx = mx * 0.995;
        my = my * 0.99;

        spr.setX((int) x);
        spr.setY((int) y);

        if (y > 680) {
            my = -Math.abs(my);
            if (my > -8) my = my * 0.8;
            if (my > -2) my = 0;
        }

        if (Math.abs(mx) < 1) mx = 0;

        if (x > 1720) mx = -Math.abs(mx);
        if (x < 0) mx = Math.abs(mx);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}


