package engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class EngineMouseAdapter implements MouseListener, MouseMotionListener {

    private Canvas c;
    private ArrayList<MouseListener> addedMouseListeners = new ArrayList<MouseListener>();
    private ArrayList<MouseMotionListener> addedMouseMotionListeners = new ArrayList<MouseMotionListener>();

    public void addMouseListener(MouseListener l) {
        addedMouseListeners.add(l);
    }

    public void addMouseMotionListener(MouseMotionListener l) {
        addedMouseMotionListeners.add(l);
    }

    public EngineMouseAdapter(Canvas can) {
        c = can;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (MouseListener l : addedMouseListeners) {
            l.mouseClicked(augmentMouseEvent(e));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MouseListener l : addedMouseListeners) {
            l.mousePressed(augmentMouseEvent(e));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MouseListener l : addedMouseListeners) {
            l.mouseReleased(augmentMouseEvent(e));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (MouseListener l : addedMouseListeners) {
            l.mouseEntered(augmentMouseEvent(e));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (MouseListener l : addedMouseListeners) {
            l.mouseExited(augmentMouseEvent(e));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (MouseMotionListener l : addedMouseMotionListeners) {
            l.mouseDragged(augmentMouseEvent(e));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MouseMotionListener l : addedMouseMotionListeners) {
            l.mouseMoved(augmentMouseEvent(e));
        }
    }

    //These functions transform the mouse position onto
    private int transformX(int x) {
        int tx = 0;

        tx = x - (int) c.getEngine().offsetX;
        tx = (int) (tx / c.getEngine().areaScalar);

        return tx;
    }

    //These functions transform the mouse position onto
    private int transformY(int y) {
        int ty = 0;

        ty = y - (int) c.getEngine().offsetY;
        ty = (int) (ty / c.getEngine().areaScalar);

        return ty;
    }

    //Creates a new MouseEvent with x/y transformed
    private MouseEvent augmentMouseEvent(MouseEvent e) {
        MouseEvent out;
        out = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), transformX(e.getX()), transformY(e.getY()), e.getClickCount(), e.isPopupTrigger(), e.getButton());

        return out;
    }
}
