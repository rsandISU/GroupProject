package test;

import engine.Canvas;
import engine.GameElement;
import engine.SpriteClickable;
import util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTest implements GameElement {

    private Canvas can;

    private SpriteClickable engineButton;
    private SpriteClickable rotationButton;

    public MenuTest(Canvas c) {
        this.can = c;

        engineButton = new SpriteClickable(ResourceLoader.getImage("test/buttonEngineTest.png"), ResourceLoader.getImage("test/buttonEngineTestActive.png"), 860, 300, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("TEST");
            }
        });

        rotationButton = new SpriteClickable(ResourceLoader.getImage("test/buttonRotationTest.png"), ResourceLoader.getImage("test/buttonRotationTestActive.png"), 860, 450, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("ROTATE");
            }
        });
    }

    @Override
    public void start() {
        can.add(engineButton);
        can.add(rotationButton);
    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

    }
}
