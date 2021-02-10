package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;

public class Canvas {

    //Canvas elements
    private JFrame frame;
    private JPanel panel;
    private GraphicsEngine engine;

    //Game element management
    private HashMap<String, GameElement> gameElements = new HashMap<String, GameElement>();
    private String currentGameElement = "";
    private String gameElementToSet = "";

    //IO Management
    private EngineKeyboardAdapter eka;
    private EngineMouseAdapter ema;

    //This will act as the main canvas onto which graphical elements will be added onto
    public Canvas(String name) {
        //Create initial JFrame
        frame = new JFrame(name);

        //Set up frame attributes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(960, 540));
        panel.setFocusable(true);

        frame.add(panel);
        frame.pack();

        //Make frame visible
        frame.setVisible(true);

        engine = new GraphicsEngine(panel);

        //Add action listeners
        eka = new EngineKeyboardAdapter();
        ema = new EngineMouseAdapter(this);
        panel.addKeyListener(eka);
        panel.addMouseListener(ema);
        panel.addMouseMotionListener(ema);

        startTimer();
    }

    //Starts up a timer at fires around 60 FPS
    public void startTimer() {
        ActionListener action = new ActionListener() {

            long lastTime = System.currentTimeMillis();
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //FPS Tracking, if needed
                /*if (lastTime + 1000 < System.currentTimeMillis()) {
                    System.out.println(count);
                    count = 0;
                    lastTime = 1000 + System.currentTimeMillis();
                }

                count++;*/
                engine.updatePanel();
                updateGameElements();
            }
        };

        Timer t = new Timer(10, action);
        t.setRepeats(true);
        t.start();
    }

    //Engine manipulation functions
    public String add(Spritoid spr) {
        return engine.add(spr);
    }

    public void put(Spritoid spr, String hash) {
        engine.put(spr, hash);
    }

    public Spritoid get(String hash) {
        return engine.get(hash);
    }

    public void clear() {
        engine.clear();
    }

    public void addGameElement(String name, GameElement e) {
        gameElements.put(name, e);

        if (e instanceof MouseListener) ema.addMouseListener((MouseListener) e);
        if (e instanceof MouseMotionListener) ema.addMouseMotionListener((MouseMotionListener) e);
    }

    public GameElement getGameElement(String hash) {
        return gameElements.get(hash);
    }

    //Updates a game element
    public void updateGameElements() {
        GameElement e = getGameElement(currentGameElement);

        boolean needStart = false;

        if (!gameElementToSet.equals(currentGameElement)) {

            GameElement elementToStop = getGameElement(currentGameElement);

            if (elementToStop != null) elementToStop.stop();

            currentGameElement = gameElementToSet;
            e = getGameElement(currentGameElement);

            needStart = true;
            engine.clear();
        }

        if (e != null) {
            if (needStart) e.start();
            e.update();
        }
    }

    //Sets the current game element
    public void setElement(String name) {
        gameElementToSet = name;
    }

    //Gets keys down
    public List<Character> getKeysDown() {
        return eka.keysDown;
    }

    //Returns game engine
    public GraphicsEngine getEngine() {
        return engine;
    }

}
