package menu;

import engine.Canvas;
import engine.Sprite;
import engine.Spritoid;
import util.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class MenuAnimationCore {

    private Canvas can;

    private int[] stars = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private BufferedImage blink0 = ResourceLoader.getImage("menu/blink0.png");
    private BufferedImage blink1 = ResourceLoader.getImage("menu/blink1.png");
    private BufferedImage blink2 = ResourceLoader.getImage("menu/blink2.png");
    private BufferedImage blink3 = ResourceLoader.getImage("menu/blink3.png");
    private BufferedImage blink4 = ResourceLoader.getImage("menu/blink4.png");

    private Random r = new Random();

    private HashMap<String, Integer> initPosMapX = new HashMap<>();
    private HashMap<String, Integer> initPosMapY = new HashMap<>();

    private double clock = 0;
    private double scrollX = 0;
    private double targetX = 0;

    private Menu menu;

    public MenuAnimationCore(Canvas c, Menu menu) {
        can = c;
        this.menu = menu;
    }

    public void moveTo(int targetX) {
        this.targetX = targetX;
    }

    public void advance() {
        clock = clock + 0.1;

        scrollX = scrollX + ((targetX - scrollX) * 0.08);

        String[] scrollers = {"ss1", "ss2", "star0", "star1", "star2", "star3", "star4", "star5", "star6", "star7", "star8", "star9", "star10", "star11", "star12", "star13", "star14", "star15", "star16", "star17", "star18", "star19"};

        for (String name : scrollers) {
            Spritoid spr = can.get(name);

            spr.setX(spr.getX() - 1);

            if (spr.getX() < -1930) {
                spr.setX(1930);
            }
        }

        for (int i = 0; i < stars.length; i++)  {
            if (stars[i] > 0) {
                stars[i]++;

                if (stars[i] > 9) stars[i] = 0;
            }  else {
                if (r.nextInt(200) == 0) stars[i] = 1;
            }

            Sprite spr = (Sprite) can.get("star"+i);

            if (stars[i] == 0) spr.setImage(blink0);
            if (stars[i] == 2) spr.setImage(blink1);
            if (stars[i] == 4) spr.setImage(blink2);
            if (stars[i] == 6) spr.setImage(blink3);
            if (stars[i] == 8) spr.setImage(blink4);
        }

        String[] bouncers = {"tetrisLogo", "pacmanLogo"};

        for (String name : bouncers) {
            Spritoid spr = can.get(name);

            int initPos = 0;

            if (initPosMapY.containsKey(name)) {
                initPos = initPosMapY.get(name);
            } else {
                initPos = spr.getY();
                initPosMapY.put(name, initPos);
            }

            spr.setY(initPos + (int) (Math.sin(clock/3) * 15));
        }

        String[] shifters = {"tetrisLogo", "tetrisArrowRight", "pacmanLogo", "pacmanArrowLeft", "tetrisBorder", "tetrisTitle", "tetrisLine0", "tetrisLine1", "tetrisLine2", "tetrisLine3", "tetrisLine4", "tetrisLine5", "tetrisLine6", "tetrisLine7", "tetrisLine8", "tetrisLine9", "tetrisPlay", "pacmanBorder", "pacmanTitle", "pacmanLine0", "pacmanLine1", "pacmanLine2", "pacmanLine3", "pacmanLine4", "pacmanLine5", "pacmanLine6", "pacmanLine7", "pacmanLine8", "pacmanLine9", "pacmanPlay"};

        for (String name : shifters) {
            Spritoid spr = can.get(name);

            int initPos = 0;

            if (initPosMapX.containsKey(name)) {
                initPos = initPosMapX.get(name);
            } else {
                initPos = spr.getX();
                initPosMapX.put(name, initPos);
            }

            spr.setX(initPos - (int) scrollX);
        }
    }
}
