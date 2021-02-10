package engine;

import java.awt.image.BufferedImage;

public class Sprite implements Spritoid{
    BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int layer;

    //Constructor zone
    public Sprite(BufferedImage image, int x, int y, int width, int height, int layer) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layer = layer;
    }

    public Sprite(BufferedImage image, int x, int y, int layer) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.layer = layer;
    }

    public Sprite(BufferedImage image, int layer) {
        this.image = image;
        this.x = 0;
        this.y = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.layer = layer;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
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

    public void setHeight(int height) {
        this.height = height;
    }
}
