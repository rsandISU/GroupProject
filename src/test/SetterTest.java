package test;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
import engine.SpriteTransformable;
import util.ResourceLoader;

public class SetterTest implements GameElement {

    private Canvas can;

    private SpriteTransformable spr;
    private Sprite background;
    private Sprite foreground;
    private SpriteTransformable funny;

    public SetterTest(Canvas c) {
        this.can = c;

        spr = new SpriteTransformable(ResourceLoader.getImage("test/funni.png"), 0, 0, 150, 300, 5);

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
        spr.setAngle(spr.getAngle() - 1);
        funny.setAngle(funny.getAngle() + 0.3);

        //spr.setX(can.getMouseX() - 75);
        //spr.setY(can.getMouseY() - 150);
    }
}
