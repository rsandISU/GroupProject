package menu;

import engine.Canvas;
import engine.Sprite;
import engine.Spritoid;
import util.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuElementBuilder {
    Canvas can;
    HashMap<String, Spritoid> elements = new HashMap<String, Spritoid>();

    public MenuElementBuilder(Canvas c) {
        can = c;
        initMenuElements();
    }

    private void initMenuElements() {
        BufferedImage ss = ResourceLoader.getImage("menu/starscape.png");
        elements.put("ss1", new Sprite(ss, 0, 0, 1935, 1080, 0));
        elements.put("ss2", new Sprite(ss, 1930, 0, 1935, 1080, 0));
    }

    public void buildMenu() {
        for (String hash : elements.keySet()) {
            can.put(elements.get(hash), hash);
        }
    }
}
