package test;

import engine.*;
import engine.Canvas;
import util.ResourceLoader;
import util.SoundLoader;

import java.awt.*;
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

        engineText = new SpriteText(860, 230, 0, 0, 1);
        engineText.setText("ENGINE", Color.BLACK, 2.4);
        engineText.setX(860 + ((200 - engineText.getWidth()) / 2));

        rotationText = new SpriteText(860, 380, 0, 0, 1);
        rotationText.setText("ROTATE", Color.BLACK, 2.4);
        rotationText.setX(860 + ((200 - rotationText.getWidth()) / 2));

        tetrisText = new SpriteText(860, 530, 0, 0, 1);
        tetrisText.setText("TETRIS", Color.BLACK, 2.4);
        tetrisText.setX(860 + ((200 - tetrisText.getWidth()) / 2));

        pacmanText = new SpriteText(860, 680, 0, 0, 1);
        pacmanText.setText("PACMAN", Color.BLACK, 2.4);
        pacmanText.setX(860 + ((200 - pacmanText.getWidth()) / 2));


        s = SoundLoader.getSound("music/chip1.wav");
        s.setRepeats(true);
        s.setVolume(0.6F);
    }

    @Override
    public void start() {
        can.add(engineButton);
        can.add(rotationButton);
        can.add(tetrisButton);
        can.add(pacmanButton);

        can.add(engineText);
        can.add(rotationText);
        can.add(tetrisText);
        can.add(pacmanText);

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
