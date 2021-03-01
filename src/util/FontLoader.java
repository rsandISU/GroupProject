package util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    private static FontLoader fl = new FontLoader();

    public FontLoader() {

    }

    public static Font getFont(String fileName) {
        Font font = null;
        InputStream is = fl.getClass().getResourceAsStream("/" + fileName);

        try {
            Font tFont = Font.createFont(Font.PLAIN, is);
            font = tFont.deriveFont(Font.PLAIN, 12);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(font);

        return font;
    }
}
