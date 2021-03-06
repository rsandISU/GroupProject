package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EngineKeyboardAdapter implements KeyListener {
    //Make synchro list to ensure bad shit does not happen
    public List<Character> keysDown = Collections.synchronizedList(new ArrayList<Character>());

    private ArrayList<KeyListener> addedKeyListeners = new ArrayList<KeyListener>();

    public void addKeyListener(KeyListener l) {
        addedKeyListeners.add(l);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (KeyListener l : addedKeyListeners) {
            l.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyListener l : addedKeyListeners) {
            l.keyPressed(e);
        }

        //If a key has been pressed, add it to the array of keys down
        Character keyChar = e.getKeyChar();

        if (!keysDown.contains(keyChar)) keysDown.add(keyChar);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (KeyListener l : addedKeyListeners) {
            l.keyReleased(e);
        }

        //If a key has been let up, remove it from the array of keys down
        Character keyChar = e.getKeyChar();

        if (keysDown.contains(keyChar)) keysDown.remove(keyChar);
    }
}
