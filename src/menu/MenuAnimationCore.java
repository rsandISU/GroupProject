package menu;

import engine.Canvas;
import engine.Spritoid;

public class MenuAnimationCore {

    Canvas can;

    public MenuAnimationCore(Canvas c) {
        can = c;
    }

    public void advance() {
        String[] scrollable = {"ss1", "ss2"};

        for (String name : scrollable) {
            Spritoid spr = can.get(name);

            spr.setX(spr.getX() - 1);

            if (spr.getX() < -1930) {
                spr.setX(1930);
            }
        }
    }
}
