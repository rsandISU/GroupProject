package menu;

import engine.Canvas;
import engine.Sprite;
import engine.SpriteText;
import util.ResourceLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoreTable {

    private Canvas can;
    private Menu menu;

    private int startX;

    private Sprite border;
    private SpriteText title;

    private ArrayList<SpriteText> highScoreTexts = new ArrayList<>();
    private ArrayList<ScoreEntry> highScoreEntries = new ArrayList<>();

    public HighScoreTable(int startX, Canvas can, Menu m) {
        this.can = can;
        this.menu = m;

        this.startX = startX;

        border = new Sprite(ResourceLoader.getImage("menu/highScoreBorder.png"), startX + 430, 350, 500, 600, 3);

        title = new SpriteText(startX + 480, 400, 400, 20, 3);
        title.setText("-- High Scores --", Color.LIGHT_GRAY, 2);

        for (int i = 0; i < 10; i++) {
            SpriteText line = new SpriteText(startX + 480, 470 + (i * 40), 400,20, 3);
            line.setText("??? : 00000000", Color.LIGHT_GRAY, 2);

            highScoreTexts.add(line);
        }
    }

    public void build(String name) {
        can.put(border, name + "Border");
        can.put(title, name + "Title");

        for (int i = 0; i < highScoreTexts.size(); i++) {
            can.put(highScoreTexts.get(i), name + "Line" + i);
        }

        update();
    }

    public void add(ScoreEntry score) {
        highScoreEntries.add(score);
    }

    public void update() {
        Collections.sort(highScoreEntries);

        int lastIndex = highScoreEntries.size() - 1;

        for (int i = 0; i < highScoreTexts.size(); i++) {
            if (lastIndex < 0) break;

            ScoreEntry entry = highScoreEntries.get(lastIndex);

            highScoreTexts.get(i).setText((entry.getInitials() + "   ").toUpperCase().substring(0, 3) + " : " + String.format("%08d", entry.getScore()), Color.LIGHT_GRAY, 2);

            lastIndex--;
        }
    }
}
