package test;

import engine.*;
import engine.Canvas;
import util.ResourceLoader;
import util.TextBuilder;

import java.awt.*;

public class SetterTest implements GameElement {

    private Canvas can;

    private SpriteTextTransformable spr;
    private Sprite background;
    private Sprite foreground;
    private SpriteTransformable funny;

    public SetterTest(Canvas c) {
        this.can = c;

        spr = new SpriteTextTransformable(0, 0, 5);
        spr.setText("BRUH BRUH BRUH", Color.BLACK, 5);

        background = new Sprite(ResourceLoader.getImage("test/testBackground.png"), 0, 0, 2);
        foreground = new Sprite(ResourceLoader.getImage("test/testForeground.png"), 0, 0, 0);
        funny = new SpriteTransformable(ResourceLoader.getImage("test/funniActive.png"), 960 - 250, 50, 500, 1000, 1);

        funny.setOffsetY(1);
    }

    @Override
    public void start() {
        can.add(funny);
        can.add(background);
        can.add(foreground);
        can.add(spr);

        funny.setAngle(180);
    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {
        funny.setAngle(funny.getAngle() + 0.3);

        spr.setX(can.getMouseX() - (spr.getWidth() / 2));
        spr.setY(can.getMouseY() - (spr.getHeight()/2));
        spr.setAngle(spr.getAngle() - 1);
    }
}
