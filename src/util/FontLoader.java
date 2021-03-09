package util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    private static FontLoader fl = new FontLoader();

    //Loads up fonts
    public static Font getFont(String fileName) {
        Font font = null;
        InputStream is = fl.getClass().getResourceAsStream("/" + fileName);

        try {
            Font tFont = Font.createFont(Font.PLAIN, is);
            font = tFont.deriveFont(Font.PLAIN, 12);
        } catch (Exception e) {
            System.out.println("FONT LOAD FAILURE: " + fileName);

            return null;
        }

        System.out.println("LOADED: " + fileName);

        return font;
    }
}
