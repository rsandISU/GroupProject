package main;

import engine.Canvas;
import engine.Sprite;
import test.EngineTest;
import util.ResourceLoader;

public class Main {

    public static void main(String[] args) {

        Canvas can = new Canvas("Test");

        can.addGameElement("TEST", new EngineTest(can));

        can.setElement("TEST");

    }
}
