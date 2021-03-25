package menu;

import engine.Canvas;
import engine.Sprite;
import engine.SpriteClickable;
import engine.Spritoid;
import util.ResourceLoader;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MenuElementBuilder {
    Canvas can;
    private Menu menu;
    private HashMap<String, Spritoid> elements = new HashMap<String, Spritoid>();

    private Random r = new Random();

    public MenuElementBuilder(Canvas c, Menu menu) {
        can = c;
        this.menu = menu;
        initMenuElements();
    }

    private void initMenuElements() {
        BufferedImage ss = ResourceLoader.getImage("menu/starscape.png");
        elements.put("ss1", new Sprite(ss, 0, 0, 1930, 1080, 0));
        elements.put("ss2", new Sprite(ss, 1930, 0, 1930, 1080, 0));

        BufferedImage star = ResourceLoader.getImage("menu/blink0.png");
        for (int i = 0; i < 20; i++) {
            elements.put("star" + i, new Sprite(star, r.nextInt(1920*2), r.nextInt(1080), 10 + r.nextInt(5), 10 + r.nextInt(5), 1));
        }

        elements.put("tetrisLogo", new Sprite(ResourceLoader.getImage("menu/tetrisLogo.png"), (1920 - (1920/3)) / 2, 100, 1920/3, 200 , 2));

        SpriteClickable tetrisArrowRight = new SpriteClickable(ResourceLoader.getImage("menu/littleArrowRight.png"), ResourceLoader.getImage("menu/littleArrowRightActive.png"), 1650, (1080 / 2) - (110 / 2), 150, 110, 2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.mac.moveTo(1920 * 2);
            }
        });

        elements.put("tetrisArrowRight", tetrisArrowRight);

        SpriteClickable tetrisPlay = new SpriteClickable(ResourceLoader.getImage("menu/tetrisPlay.png"), ResourceLoader.getImage("menu/tetrisPlayActive.png"), 960, 575, 300, 100, 2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("TETRIS");
            }
        });

        elements.put("tetrisPlay", tetrisPlay);

        elements.put("pacmanLogo", new Sprite(ResourceLoader.getImage("menu/pacmanLogo.png"), (1920*2) + (1920 - (1920/3)) / 2, 100, 1920/3, 200 , 2));

        SpriteClickable pacmanArrowLeft = new SpriteClickable(ResourceLoader.getImage("menu/littleArrowLeft.png"), ResourceLoader.getImage("menu/littleArrowLeftActive.png"), (1920 * 2) + 120, (1080 / 2) - (110 / 2), 150, 110, 2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.mac.moveTo(0);
            }
        });

        elements.put("pacmanArrowLeft", pacmanArrowLeft);

        SpriteClickable pacmanPlay = new SpriteClickable(ResourceLoader.getImage("menu/pacmanPlay.png"), ResourceLoader.getImage("menu/pacmanPlayActive.png"), (1920 * 2) + 960, 575, 300, 100, 2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                can.setElement("PACMANTEST");
            }
        });

        elements.put("pacmanPlay", pacmanPlay);

    }

    public void buildMenu() {
        for (String hash : elements.keySet()) {
            can.put(elements.get(hash), hash);
        }
    }
}
