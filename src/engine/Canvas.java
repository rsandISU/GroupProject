package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canvas {

    private JFrame frame;
    private JPanel panel;
    private GraphicsEngine engine;

    //This will act as the main canvas onto which graphical elements will be added onto
    public Canvas(String name) {
        //Create initial JFrame
        frame = new JFrame(name);

        //Set up frame attributes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(960, 540));

        frame.add(panel);
        frame.pack();

        //Make frame visible
        frame.setVisible(true);

        engine = new GraphicsEngine(panel);

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
            }
        };

        Timer t = new Timer(7, action);
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
}
