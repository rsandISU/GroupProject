package test;

import engine.*;
import util.ResourceLoader;
import util.SoundLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuTest implements GameElement {

    private Canvas can;

    private SpriteClickable engineButton;
    private SpriteClickable rotationButton;
    private SpriteClickable tetrisButton;
    private SpriteClickable pacmanButton;

    private SpriteText engineText;
    private SpriteText rotationText;
    private SpriteText tetrisText;
    private SpriteText pacmanText;


    private Soundable s;

    public MenuTest(Canvas c) {
        this.can = c;

        engineButton = new SpriteClickable(ResourceLoader.getImage("template/buttonBlank.png"), ResourceLoader.getImage("template/buttonBlankActive.png"), 860, 200, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("TEST");
            }
        });

        rotationButton = new SpriteClickable(ResourceLoader.getImage("template/buttonBlank.png"), ResourceLoader.getImage("template/buttonBlankActive.png"), 860, 350, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("ROTATE");
            }
        });

        tetrisButton = new SpriteClickable(ResourceLoader.getImage("template/buttonBlank.png"), ResourceLoader.getImage("template/buttonBlankActive.png"), 860, 500, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("TETRIS");
            }
        });

        pacmanButton = new SpriteClickable(ResourceLoader.getImage("template/buttonBlank.png"), ResourceLoader.getImage("template/buttonBlankActive.png"), 860, 650, 200, 100, 0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("PACMANTEST");
            }
        });

        engineText = new SpriteText(860, 200, 0, 0, 1);

        s = SoundLoader.getSound("music/chip1.wav");
        s.setRepeats(true);
    }

    @Override
    public void start() {
        can.add(engineButton);
        can.add(rotationButton);
        can.add(tetrisButton);
        can.add(pacmanButton);

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
