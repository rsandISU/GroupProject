package main;

import engine.Canvas;
import engine.Sprite;
import engine.SpriteText;
import test.EngineTest;
import test.MenuTest;
import test.SetterTest;
import util.FontLoader;
import util.ResourceLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        FontLoader.getFont("font/c64ProMono.ttf");

        Canvas can = new Canvas("Test");

        SpriteText loader = new SpriteText(0);
        can.add(loader);

        loader.setText("LOADING: MENU", Color.BLACK, 3);

        can.addGameElement("MENU", new MenuTest(can));

        loader.setText("LOADING: TEST", Color.BLACK, 3);

        can.addGameElement("TEST", new EngineTest(can));

        loader.setText("LOADING: ROTATE", Color.BLACK, 3);

        can.addGameElement("ROTATE", new SetterTest(can));

        can.setElement("MENU");


    }
}
