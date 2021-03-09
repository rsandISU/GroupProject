package test;

import engine.Canvas;
import engine.GameElement;
import engine.Soundable;
import engine.SpriteClickable;
import util.ResourceLoader;
import util.SoundLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTest implements GameElement {

    private Canvas can;

    private SpriteClickable engineButton;
    private SpriteClickable rotationButton;

    private Soundable s;

    public MenuTest(Canvas c) {
        this.can = c;

        engineButton = new SpriteClickable(ResourceLoader.getImage("template/buttonBlank.png"), ResourceLoader.getImage("template/buttonBlankActive.png"), 860, 300, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("TEST");
            }
        });

        rotationButton = new SpriteClickable(ResourceLoader.getImage("template/buttonBlank.png"), ResourceLoader.getImage("template/buttonBlankActive.png"), 860, 450, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("ROTATE");
            }
        });

        s = SoundLoader.getSound("music/chip1.wav");
        s.setRepeats(true);
    }

    @Override
    public void start() {
        can.add(engineButton);
        can.add(rotationButton);

        s.play();
    }

    @Override
    public void stop() {
        s.haltClip();
    }

    @Override
    public void update() {

    }
}
