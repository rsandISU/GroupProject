package engine;

import java.awt.image.BufferedImage;

public interface Spritoid {

    public BufferedImage getImage();
    public void setX(int x);
    public int getX();
    public void setY(int y);
    public int getY();
    public void setWidth(int width);
    public int getWidth();
    public void setHeight(int height);
    public int getHeight();
    public void setLayer(int layer);
    public int getLayer();
}
