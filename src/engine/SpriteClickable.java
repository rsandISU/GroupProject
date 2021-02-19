package engine;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.EventListener;

public class SpriteClickable implements Spritoid{
    private int x;
    private int y;
    private int width;
    private int height;
    private int layer;
    private boolean visible;

    private BufferedImage activeImage;
    private BufferedImage inactiveImage;

    private boolean isActive;
    private ActionListener event;

    //Constructor zone
    public SpriteClickable(BufferedImage inactiveImage, BufferedImage activeImage, int x, int y, int width, int height, int layer, ActionListener event) {
        this.inactiveImage = inactiveImage;
        this.activeImage = activeImage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layer = layer;
        this.event = event;
        this.visible = true;
    }

    public SpriteClickable(BufferedImage inactiveImage, BufferedImage activeImage, int x, int y, int layer, ActionListener event) {
        this.inactiveImage = inactiveImage;
        this.activeImage = activeImage;
        this.x = x;
        this.y = y;
        this.width = inactiveImage.getWidth();
        this.height = inactiveImage.getHeight();
        this.layer = layer;
        this.event = event;
        this.visible = true;
    }

    public SpriteClickable(BufferedImage inactiveImage, BufferedImage activeImage, int layer, ActionListener event) {
        this.inactiveImage = inactiveImage;
        this.activeImage = activeImage;
        this.x = 0;
        this.y = 0;
        this.width = inactiveImage.getWidth();
        this.height = inactiveImage.getHeight();
        this.layer = layer;
        this.event = event;
        this.visible = true;
    }

    //Is called when clicked
    public void click() {
        if (event != null) {
            event.actionPerformed(new ActionEvent(this, 0, "Pressed"));
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setActiveImage(BufferedImage img) {
        activeImage = img;
    }

    public void setInactiveImage(BufferedImage img) {
        inactiveImage = img;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ActionListener getEvent() {
        return event;
    }

    public void setEvent(ActionListener event) {
        this.event = event;
    }

    @Override
    public BufferedImage getImage() {
        if (!visible) return null;
        if (isActive) return activeImage;
        return inactiveImage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
