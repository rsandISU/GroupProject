package main;

import engine.Canvas;
import engine.Sprite;
import test.EngineTest;
import test.MenuTest;
import test.SetterTest;
import util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        Canvas can = new Canvas("Test");

        can.addGameElement("MENU", new MenuTest(can));
        can.addGameElement("TEST", new EngineTest(can));
        can.addGameElement("ROTATE", new SetterTest(can));

        can.setElement("MENU");


    }
}
