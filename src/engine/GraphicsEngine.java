package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GraphicsEngine extends JPanel{

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


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D panelG = (Graphics2D) g;

        //Redraw time!

        //Remake the graphics object if the panel gets resized
        //This is a terrible piece of code, but I don't know how to use JPanel graphics correctly nor do I want to learn
        if (width != this.getWidth() || height != this.getHeight()) {
            width = this.getWidth();
            height = this.getHeight();
            panelG = (Graphics2D) this.getGraphics();
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

        panelG.setColor(Color.white);
        panelG.fillRect(0 + (int) xOffset, 0 + (int) yOffset, (int) (areaWidth * scalar), (int) (areaHeight * scalar));

        updateGraphics((int) xOffset, (int) yOffset, scalar, panelG);


        //Black out the panel
        panelG.setColor(Color.BLACK);
        panelG.fillRect(0, 0 , this.getWidth(), (int) yOffset);
        panelG.fillRect(0, this.getHeight() - ((int) yOffset+2), this.getWidth(), (int) yOffset+2);
        panelG.fillRect(0, 0, (int) xOffset, this.getHeight());
        panelG.fillRect(this.getWidth() - ((int) xOffset+2), 0, (int) xOffset+2, this.getHeight());


    }

    public void updateGraphics(int xOffset, int yOffset, double scalar, Graphics2D panelG) {
        //Draw all of the sprites
        int layer = 0;
        int maxLayer = 0;

        while (layer <= maxLayer) {
            for (Spritoid spr : objects.values()) {

                if (spr.getLayer() > maxLayer) maxLayer = spr.getLayer();
                if (spr.getLayer() == layer) {
                    if (spr.getImage() != null) {
                        //Image drawing time
                        if (spr instanceof Transformable) {

                            //This image is transformed, draw it a such
                            Transformable trans = (Transformable) spr;

                            //Affine transformation hell
                            AffineTransform at = new AffineTransform();

                            //Translate to X position
                            //at.translate(spr.getX(), spr.getY());

                            //Rotate
                            at.rotate(Math.toRadians(trans.getAngle()), xOffset + ((spr.getWidth() * scalar) * trans.getOffsetX()) + (spr.getX() * scalar), yOffset + ((spr.getHeight() * scalar) * trans.getOffsetY()) + (spr.getY() * scalar));

                            //Transform, draw, and invert transform
                            panelG.transform(at);

                            panelG.drawImage(spr.getImage(), xOffset + (int) (spr.getX() * scalar), yOffset + (int) (spr.getY() * scalar), (int) (spr.getWidth() * scalar), (int) (spr.getHeight() * scalar), null);
                            try {
                                panelG.transform(at.createInverse());
                            } catch (NoninvertibleTransformException e) {
                                System.out.println("Cannot de-transform");
                            }

                            //If not transformable, draw it "the easy way"

                        } else {
                            panelG.drawImage(spr.getImage(), xOffset + (int) (spr.getX() * scalar), yOffset + (int) (spr.getY() * scalar), (int) ((spr.getWidth()+1) * scalar), (int) ((spr.getHeight()+1) * scalar), null);
                        }
                    }
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

    public void remove(String hash) {
        objects.remove(hash);
    }

    //Returns the object hashmap
    public HashMap<String, Spritoid> getObjects() {
        return objects;
    }

    //Remove all objects
    public void clear() {
        objects = new HashMap<String, Spritoid>();
    }

    //Returns all of the keys in the hashmap
    public String[] getKeys() {
        return objects.keySet().toArray(new String[0]);
    }
}
