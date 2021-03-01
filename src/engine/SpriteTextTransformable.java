package engine;

import java.awt.image.BufferedImage;

public class SpriteTextTransformable extends SpriteText implements Transformable {

    double angle = 0;
    double ox = 0.5;
    double oy = 0.5;

    public SpriteTextTransformable(int x, int y, int width, int height, int layer) {
        super(x, y, width, height, layer);
    }

    public SpriteTextTransformable(int x, int y, int layer) {
        super(x, y, layer);
    }

    public SpriteTextTransformable(int layer) {
        super(layer);
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public double getOffsetX() {
        return ox;
    }

    @Override
    public void setOffsetX(double ox) {
        this.ox = ox;
    }

    @Override
    public double getOffsetY() {
        return oy;
    }

    @Override
    public void setOffsetY(double oy) {
        this.oy = oy;
    }
}
