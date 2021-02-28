package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    //State
    private int FPS = 0;

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

            final boolean DO_FPS = false;
            final int EXP_FRAME_TENTH_SECOND= 7;

            long lastTime = System.currentTimeMillis();
            int count = 0;

            int choke = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //FPS Tracking, and choke calculation
                if (lastTime + 100 < System.currentTimeMillis()) {
                    //Choke calculation
                    int variance = EXP_FRAME_TENTH_SECOND - count;
                    if (variance > 0) choke += variance;

                    if (DO_FPS) System.out.println("FPS: " + count*10 + " CH: " + choke);
                    FPS = count*10;

                    count = 0;
                    lastTime = System.currentTimeMillis();
                }

                count++;
                engine.updatePanel();

                updateGameElements();
                updateButtons();

                //Make up choked frames
                if (choke > 0) {
                    updateGameElements();
                    updateButtons();

                    choke--;
                }
            }
        };

        Timer t = new Timer(10, action);
        t.setRepeats(true);
        t.start();
    }

    private void updateButtons() {
        for (Spritoid spr : getObjects().values()) {
            if (spr instanceof SpriteClickable) {
                SpriteClickable button = (SpriteClickable) spr;

                button.setActive(ema.mouseX >= button.getX() && ema.mouseX <= button.getX() + (button.getWidth()) && ema.mouseY >= button.getY() && ema.mouseY <= (button.getY() + button.getHeight()));

            }
        }
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

    public void remove(String hash) {
        engine.remove(hash);
    }

    public void remove(Spritoid spr) {
        String targetHash = null;

        for (String hash : engine.getKeys()) {
            if (get(hash) == spr) {
                targetHash = hash;
            }
        }

        remove(targetHash);
    }

    public void clear() {
        engine.clear();
    }

    //Game element addition and getters
    public void addGameElement(String name, GameElement e) {
        gameElements.put(name, e);

        //If the game element is interfaced with any of these types, add them to their respective adapters
        if (e instanceof MouseListener) ema.addMouseListener((MouseListener) e);
        if (e instanceof MouseMotionListener) ema.addMouseMotionListener((MouseMotionListener) e);
        if (e instanceof KeyListener) eka.addKeyListener((KeyListener) e);
    }

    public GameElement getGameElement(String hash) {
        return gameElements.get(hash);
    }

    //Updates a game element
    private void updateGameElements() {
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

    //Returns the GraphicsEngine objects
    public HashMap<String, Spritoid> getObjects() {
        return engine.getObjects();
    }

    public int getMouseX() {
        return ema.mouseX;
    }

    public int getMouseY() {
        return ema.mouseY;
    }

    //Returns frames per second
    public int getFPS() {
        return FPS;
    }


}
