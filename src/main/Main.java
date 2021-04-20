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

        Canvas can = new Canvas("Java Game Arcade");

        SpriteText loader = new SpriteText(0);
        can.add(loader);

        loader.setText("LOADING: MENU", Color.BLACK, 3);

        Menu m = new Menu(can);
        can.addGameElement("MENU", m);

        loader.setText("LOADING: DEBUG_MENU", Color.BLACK, 3);

        can.addGameElement("DEBUG_MENU", new MenuTest(can));

        loader.setText("LOADING: TEST", Color.BLACK, 3);

        can.addGameElement("TEST", new EngineTest(can));

        loader.setText("LOADING: ROTATE", Color.BLACK, 3);

        can.addGameElement("ROTATE", new SetterTest(can));

        loader.setText("LOADING: PACMANTEST", Color.BLACK, 3);

        can.addGameElement("PACMANTEST", new PacmanTest(can, m));

        loader.setText("LOADING: TETRIS", Color.BLACK, 3);

        can.addGameElement("TETRIS", new Tetris(can));

        can.setElement("MENU");

    }
}
