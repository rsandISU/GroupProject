package engine;

import java.awt.image.BufferedImage;

public class Sprite implements Spritoid{
    BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int layer;
    private boolean visible;

    //Constructor zone
    public Sprite(BufferedImage image, int x, int y, int width, int height, int layer) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layer = layer;
        this.visible = true;
    }

    public Sprite(BufferedImage image, int x, int y, int layer) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.layer = layer;
        this.visible = true;
    }

    public Sprite(BufferedImage image, int layer) {
        this.image = image;
        this.x = 0;
        this.y = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.layer = layer;
        this.visible = true;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public BufferedImage getImage() {
        if (visible) return image;
        return null;
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
