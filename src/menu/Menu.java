package menu;

import engine.Canvas;
import engine.GameElement;
import engine.Soundable;
import util.SoundLoader;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Menu implements GameElement {

    private Canvas can;
    public MenuElementBuilder meb;
    public MenuAnimationCore mac;

    public HighScoreTable tetrisHighScore;
    public HighScoreTable pacmanHighScore;

    private ArrayList<Soundable> playlist = new ArrayList<>();
    private Soundable currentlyPlaying;

    private Random r = new Random();

    public Menu(Canvas c) {
        can = c;
        mac = new MenuAnimationCore(c, this);
        meb = new MenuElementBuilder(c, this);

        tetrisHighScore = new HighScoreTable(0, can, this);
        pacmanHighScore = new HighScoreTable(1920 * 2, can, this);

        currentlyPlaying = null;

        Soundable.setGlobalVolume(0.8F);

        playlist.add(SoundLoader.getSound("music/chip1.wav"));
        playlist.add(SoundLoader.getSound("music/chip2.wav"));
        playlist.add(SoundLoader.getSound("music/chip3.wav"));
        playlist.add(SoundLoader.getSound("music/chip4.wav"));
        playlist.add(SoundLoader.getSound("music/chip5.wav"));

    }

    @Override
    public void start() {
        System.out.println("Building...");
        meb.buildMenu();

        tetrisHighScore.build("tetris");
        pacmanHighScore.build("pacman");
    }

    @Override
    public void stop() {
        if (currentlyPlaying != null) {
            currentlyPlaying.haltClip();
            currentlyPlaying = null;
        }
    }

    @Override
    public void update() {
        mac.advance();

        boolean newSong = false;

        if (currentlyPlaying == null) {
                newSong = true;
                System.out.println("currentlyPlaying is null");
        } else {
            if (!currentlyPlaying.isRunning()) {
                System.out.println("currentlyPlaying is stopped");
                newSong = true;
            }
        }

        if (newSong) {
            System.out.println("Playing new song...");


            currentlyPlaying = playlist.get(r.nextInt(playlist.size()));
            currentlyPlaying.play();
        }
    }

    public void addTetrisScore(int score) {
        String name = JOptionPane.showInputDialog("Enter Your Initials");
        tetrisHighScore.add(new ScoreEntry(score, name));
    }

    public void addPacmanScore(int score) {
        String name = JOptionPane.showInputDialog("Enter Your Initials");
        pacmanHighScore.add(new ScoreEntry(score, name));
    }
}
