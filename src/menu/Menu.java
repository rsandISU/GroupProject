package menu;

import engine.Canvas;
import engine.GameElement;

public class Menu implements GameElement {

    Canvas can;
    MenuElementBuilder meb;
    MenuAnimationCore mac;

    public Menu(Canvas c) {
        can = c;
        meb = new MenuElementBuilder(c);
        mac = new MenuAnimationCore(c);
    }

    @Override
    public void start() {
        System.out.println("Building...");
        meb.buildMenu();
    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {
        mac.advance();
    }
}
