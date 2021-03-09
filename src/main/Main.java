package main;

import engine.Canvas;
import engine.SpriteText;
import test.EngineTest;
import test.MenuTest;
import test.SetterTest;
import tetris.Tetris;
import util.FontLoader;

import pacman.PacmanTest;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        FontLoader.getFont("font/c64ProMono.ttf");

        Canvas can = new Canvas("Test");

        SpriteText loader = new SpriteText(0);
        can.add(loader);

        loader.setText("LOADING: DEBUG_MENU", Color.BLACK, 3);

        can.addGameElement("DEBUG_MENU", new MenuTest(can));

        loader.setText("LOADING: TEST", Color.BLACK, 3);

        can.addGameElement("TEST", new EngineTest(can));

        loader.setText("LOADING: ROTATE", Color.BLACK, 3);

        can.addGameElement("ROTATE", new SetterTest(can));

        can.setElement("DEBUG_MENU");

<<<<<<< HEAD
=======

        can.addGameElement("PACMANTEST", new PacmanTest(can));

        can.setElement("PACMANTEST");

        can.addGameElement("TETRIS", new Tetris(can));

        can.setElement("TETRIS");

>>>>>>> 715c9694490106a662098af3b088f40a27b5c595


    }
}
