
package tetris;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;

import util.ResourceLoader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Tetris implements GameElement, MouseMotionListener, KeyListener {
    Sprite left, right, bottom, pieceSpr;
    Canvas c;


    BufferedImage Empty = ResourceLoader.getImage("tetrisImages/empty.jpg");
    BufferedImage IPiece = ResourceLoader.getImage("tetrisImages/IPiece.png");
    BufferedImage JPiece = ResourceLoader.getImage("tetrisImages/JPiece.png");
    BufferedImage LPiece = ResourceLoader.getImage("tetrisImages/LPiece.png");
    BufferedImage OPiece = ResourceLoader.getImage("tetrisImages/OPiece.png");
    BufferedImage SPiece = ResourceLoader.getImage("tetrisImages/SPiece.png");
    BufferedImage TPiece = ResourceLoader.getImage("tetrisImages/TPiece.png");
    BufferedImage ZPiece = ResourceLoader.getImage("tetrisImages/ZPiece.png");



    //Tetris background
    BufferedImage LeftTB = ResourceLoader.getImage("tetrisImages/leftTetrisBackground.jpg");
    BufferedImage RightTB = ResourceLoader.getImage("tetrisImages/rightTetrisBackground.jpg");
    BufferedImage BottomTB = ResourceLoader.getImage("tetrisImages/bottomTetrisBackground.jpg");


    //Tetris vars
    int mx = 0;
    int my = 0;
    int moveDist = 50;
    int drop = 0;
    private Point pieceOrigin;
    private int currentPiece;
    private int rotation;
    private ArrayList<Integer> nextPieces = new ArrayList<Integer>();
    int well[][] = new int[21][18];
    Sprite grid[][] = new Sprite[21][18];
    private long score;


    public Tetris(Canvas c){
        this.c = c;

        //each block should have a side length of moveDist
        pieceSpr = new Sprite(Empty, 0, 0, moveDist, moveDist, 10);


        //background is 38.5 wide by 21.5 tall
        left = new Sprite(LeftTB, 0, 0, moveDist*10, moveDist*22,1);
        right = new Sprite(RightTB, moveDist*28, 0, moveDist*11, moveDist*22,2);
        bottom = new Sprite(BottomTB, moveDist*10, moveDist*21, moveDist*18, moveDist*4,4);



        //Generates the sprites that exist in grid[][]
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Sprite(Empty, moveDist*10 + j*moveDist, i * moveDist, moveDist, moveDist, 1);
            }
        }

        init();
    }



    //grid size is 18 wide by 21 tall
    // Creates a border around the well and initializes the dropping piece
    private void init() {

        for (int i = 0; i < well.length; i++) {
            for (int j = 0; j < well[my].length; j++) {
                well[i][j] = 0;
            }
        }
        newPiece();
    }



    // Put a new, random piece into the dropping position
    public void newPiece() {
        pieceOrigin = new Point(5, 2);
        rotation = 0;
        if (nextPieces.isEmpty()) {
            Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6);
            Collections.shuffle(nextPieces);
        }
        currentPiece = nextPieces.get(0);
        nextPieces.remove(0);
    }
/*
    // Collision test for the dropping piece
    private boolean collidesAt(int x, int y, int rotation) {
        for (Point p : Piece[currentPiece][rotation]) {
            if (well[p.x + x][p.y + y] != 0) {
                return true;
            }
        }
        return false;
    }
*/


    // Rotate the piece clockwise or counterclockwise
    public void rotate(int i) {
        int newRotation = (rotation + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
//        if (!collidesAt(pieceOrigin.x, pieceOrigin.y, newRotation)) {
//            rotation = newRotation;
//        }
        //repaint();
    }

//**************************************************************************************





    @Override
    public void start() {
        int x = 0;
        int y = 100;
        c.put(left, "LeftTB");
        c.put(right, "RightTB");
        c.put(bottom, "BottomTB");

        //Adds all of the content of gird[][] onto the canvas
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                c.add(grid[i][j]);

            }
        }







    }

    @Override
    public void stop() {

    }




    @Override
    public void update() {


        //Copy the state of well[][] over to grid[][]
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (well[i][j] == 0) {
                    grid[i][j].setImage(Empty);
                } else {
                    grid[i][j].setImage(IPiece);
                }
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {

            case KeyEvent.VK_LEFT: mx = mx - moveDist;;
                break;

            case KeyEvent.VK_RIGHT: mx = mx + moveDist;
                break;

            case KeyEvent.VK_DOWN: my = my + moveDist;
                drop = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


