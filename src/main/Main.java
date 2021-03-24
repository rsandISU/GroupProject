package main;

import engine.Canvas;
import engine.SpriteText;
import test.EngineTest;
import test.MenuTest;
import test.SetterTest;
import tetris.Tetris;
import util.FontLoader;

import pacman.PacmanTest;

import menu.Menu;
import java.awt.Color;

public class Main {

    public static void main(String[] args) {

        FontLoader.getFont("font/c64ProMono.ttf");

        Canvas can = new Canvas("Test");

        SpriteText loader = new SpriteText(0);
        can.add(loader);

        loader.setText("LOADING: MENU", Color.BLACK, 3);

        can.addGameElement("MENU", new Menu(can));

        loader.setText("LOADING: DEBUG_MENU", Color.BLACK, 3);

        can.addGameElement("DEBUG_MENU", new MenuTest(can));

        loader.setText("LOADING: TEST", Color.BLACK, 3);

        can.addGameElement("TEST", new EngineTest(can));

        loader.setText("LOADING: ROTATE", Color.BLACK, 3);

        can.addGameElement("ROTATE", new SetterTest(can));

        loader.setText("LOADING: PACMANTEST", Color.BLACK, 3);

        can.addGameElement("PACMANTEST", new PacmanTest(can));

        loader.setText("LOADING: TETRIS", Color.BLACK, 3);

        can.addGameElement("TETRIS", new Tetris(can));

        can.setElement("DEBUG_MENU");

    }
}
