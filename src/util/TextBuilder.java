package util;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextBuilder {
    public static Font c64Mono = FontLoader.getFont("font/c64ProMono.ttf");

    public static BufferedImage buildText(String message, Color color) {

        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle2D bounds = c64Mono.getStringBounds(message, frc);
        int w = (int) bounds.getWidth();
        int h = (int) bounds.getHeight();

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = image.createGraphics();

        g.setBackground(new Color(0, true));
        g.clearRect(0, 0, w, h);
        g.setColor(color);
        g.setFont(c64Mono);

        g.drawString(message, (float) bounds.getX(), (float) -bounds.getY());

        g.dispose();

        return image;
    }
}
