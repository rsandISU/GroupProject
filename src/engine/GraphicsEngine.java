package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GraphicsEngine {
    private JPanel panel;
    private BufferedImage area;
    private Graphics2D panelG;
    private Graphics2D areaG;

    //Panel size storage
    private int width;
    private int height;

    //Area size
    private final int areaWidth = 1920;
    private final int areaHeight = 1080;

    //Scale and offset information
    public double offsetX;
    public double offsetY;
    public double areaScalar;

    //Sprite Storage
    HashMap<String, Spritoid> objects = new HashMap<String, Spritoid>();

    public GraphicsEngine(JPanel panel) {
        this.panel = panel;
        panelG = (Graphics2D) panel.getGraphics();

        area = new BufferedImage(areaWidth, areaHeight, BufferedImage.TYPE_INT_ARGB);
        areaG = (Graphics2D) area.getGraphics();

        areaG.setColor(Color.WHITE);
        areaG.fillRect(0, 0, areaWidth, areaHeight);
    }

    private int cont;

    //Updates the graphics on the panel
    public void updatePanel() {
        //Update area graphics
        update();

        //Redraw time!

        //Remake the graphics object if the panel gets resized
        //This is a terrible piece of code, but I don't know how to use JPanel graphics correctly nor do I want to learn
        if (width != panel.getWidth() || height != panel.getHeight()) {
            width = panel.getWidth();
            height = panel.getHeight();
            panelG = (Graphics2D) panel.getGraphics();

            cont = 5;
        }

        if (cont > 0) {
            //Black out the panel
            panelG.setColor(Color.BLACK);
            panelG.fillRect(0, 0 , panel.getWidth(), panel.getHeight());

            cont--;
        }
        //graphicArea scaling fun!
        double scalar = 1.0;
        double xOffset = 0;
        double yOffset = 0;

        //Aspect ration correction
        double aspect = (width * 1.0) / (height * 1.0);
        double wantedAspect = (areaWidth * 1.0) / areaHeight;

        if (aspect > wantedAspect) {
            //If width is too wide, correct
            xOffset = width - (wantedAspect * height);
            xOffset *= 0.5;
            scalar = height / (areaHeight * 1.0);

        } else {
            //Else, correct for height being too high
            yOffset = height - ((1/wantedAspect) * width);
            yOffset *= 0.5;
            scalar = width / (areaWidth * 1.0);
        }

        offsetX = xOffset;
        offsetY = yOffset;
        areaScalar = scalar;

        //Draw graphicArea image
        panelG.drawImage(area, 0 + (int) xOffset, 0 + (int) yOffset, (int) (areaWidth * scalar), (int) (areaHeight * scalar), null);
    }

    //Draw all sprites on graphic area
    public void update() {
        //Wipe background
        areaG.setColor(Color.WHITE);
        areaG.fillRect(0, 0, areaWidth, areaHeight);

        //Draw all of the sprites
        int layer = 0;
        int maxLayer = 0;

        while (layer <= maxLayer) {
            for (Spritoid spr : objects.values()) {

                if (spr.getLayer() > maxLayer) maxLayer = spr.getLayer();
                if (spr.getLayer() == layer) {
                    areaG.drawImage(spr.getImage(), spr.getX(), spr.getY(), spr.getWidth(), spr.getHeight(), null);
                }
            }
            layer++;
        }
    }

    //Adds a sprite to the screen, and gives it a random hash
    public String add(Spritoid spr) {
        String hash = String.format("%0" + 8 + "X", (int) (Math.random() * 2000000000));

        while (objects.containsKey(hash)) hash = String.format("%0" + 8 + "X", (int) (Math.random() * 2000000000));

        objects.put(hash, spr);

        return hash;
    }

    //Puts a sprite on the screen
    public void put(Spritoid spr, String hash) {
        objects.put(hash, spr);
    }

    //Gets a sprite from the objects
    public Spritoid get(String hash) {
        return objects.get(hash);
    }

    //Remove all objects
    public void clear() {
        objects = new HashMap<String, Spritoid>();
    }
}
