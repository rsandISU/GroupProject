package main;

import engine.Canvas;
import engine.Sprite;
import test.EngineTest;
import util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        Canvas can = new Canvas("Test");

        can.addGameElement("TEST", new EngineTest(can));

        can.setElement("TEST");

    }
}
