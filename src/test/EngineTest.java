package test;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
import util.ResourceLoader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class EngineTest implements GameElement, MouseMotionListener {

    Sprite spr;
    Canvas c;

    double x = 0;
    double y = 0;

    double mx = 10;
    double my = 0;

    public EngineTest(Canvas c) {
        this.c = c;
        spr = new Sprite(ResourceLoader.getImage("/funni.png"), 0, 0, 200, 400, 0);
    }

    @Override
    public void start() {
        x = 0;
        y = 100;
        c.put(spr, "FUNNY");
    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

        x = x + mx;
        y = y + my;

        my = my + 0.5;

        //spr.setX((int) x);
        //spr.setY((int) y);

        if (y > 680) my = -20;
        if (x > 1720) mx = -Math.abs(mx);
        if (x < 0) mx = Math.abs(mx);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        spr.setX(e.getX());
        spr.setY(e.getY());
    }
}
