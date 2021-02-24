package test;

import engine.Canvas;
import engine.GameElement;
import engine.SpriteTransformable;
import util.ResourceLoader;

public class SetterTest implements GameElement {

    private Canvas can;

    private SpriteTransformable spr;

    public SetterTest(Canvas c) {
        this.can = c;

        spr = new SpriteTransformable(ResourceLoader.getImage("funni.png"), 0, 0, 150, 300, 0);
        spr.setOffsetX(0.5);
        spr.setOffsetY(0.5);
    }

    @Override
    public void start() {
        can.add(spr);
    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {
        spr.setAngle(spr.getAngle() + 1);

        spr.setX(can.getMouseX() - 75);
        spr.setY(can.getMouseY() - 150);
    }
}
