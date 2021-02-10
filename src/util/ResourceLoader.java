package util;

import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ResourceLoader {
	private static ResourceLoader rl = new ResourceLoader();
	
	public static BufferedImage getImage(String fileName) {
		Image i = (ToolkitImage) Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(fileName));

		return toBufferedImage(i);
	}

	//Waits for an image to load, then transfers it to a buffered image
	public static BufferedImage toBufferedImage(Image img) {
		try {
			MediaTracker mt = new MediaTracker(new JPanel());
			mt.addImage(img, 0);
			mt.waitForAll();

			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bi.createGraphics();
			g2d.drawImage(img, 0, 0, null);
			g2d.dispose();

			return bi;
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
