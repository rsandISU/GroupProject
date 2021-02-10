package main;

import engine.Canvas;
import engine.Sprite;
import util.ResourceLoader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Canvas Test");

        Canvas can = new Canvas("Test");

        Sprite spr = new Sprite(ResourceLoader.getImage("/funni.png"), 500, 0, 200, 400, 0);
        can.add(spr);
    }
}
