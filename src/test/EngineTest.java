package test;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
import engine.SpriteClickable;
import util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EngineTest implements GameElement, MouseMotionListener {

    SpriteClickable spr;
    Canvas c;

    double x = 0;
    double y = 0;

    double mx = 10;
    double my = 0;

    public EngineTest(Canvas c) {
        this.c = c;
        spr = new SpriteClickable(ResourceLoader.getImage("funni.png"), ResourceLoader.getImage("funniActive.png"), 0, 0, 200, 400, 0, null);

        spr.setEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mx = c.getMouseX() - (x + 100);
                my = c.getMouseY() - (y + 200);

                mx = mx * 0.2;
                my = my * 0.2;
            }
        });
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
