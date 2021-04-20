
package tetris;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;

import engine.SpriteText;
import util.ResourceLoader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tetris implements GameElement, MouseMotionListener, KeyListener {
    Sprite left, right, bottom, pieceSpr, statsRect;
    SpriteText scoreSpr, lvlSpr;
    Canvas c;


    BufferedImage Empty = ResourceLoader.getImage("tetrisImages/empty.jpg");
    BufferedImage Square = ResourceLoader.getImage("tetrisImages/IPiece.png");
//    BufferedImage JPiece = ResourceLoader.getImage("tetrisImages/JPiece.png");
//    BufferedImage LPiece = ResourceLoader.getImage("tetrisImages/LPiece.png");
//    BufferedImage OPiece = ResourceLoader.getImage("tetrisImages/OPiece.png");
//    BufferedImage SPiece = ResourceLoader.getImage("tetrisImages/SPiece.png");
//    BufferedImage TPiece = ResourceLoader.getImage("tetrisImages/TPiece.png");
//    BufferedImage ZPiece = ResourceLoader.getImage("tetrisImages/ZPiece.png");



    //Tetris background
    BufferedImage LeftTB = ResourceLoader.getImage("tetrisImages/leftTetrisBackground.jpg");
    BufferedImage RightTB = ResourceLoader.getImage("tetrisImages/rightTetrisBackground.jpg");
    BufferedImage BottomTB = ResourceLoader.getImage("tetrisImages/bottomTetrisBackground.jpg");

    //In game stats
    BufferedImage Rect = ResourceLoader.getImage("tetrisImages/empty.jpg");


    //Tetris vars
    private int mx = 0;
    private int my = 0;
    private int moveDist = 50;
    private int drop = 0;
    private Point pieceOrigin;
    private int currentPiece;
    private int rotation;
    private ArrayList<Integer> nextPieces = new ArrayList<Integer>();
    private int well[][] = new int[21][18];
    private Sprite grid[][] = new Sprite[21][18];
    private long score;
    private Random gen = new Random();
    private int pieceNum;
    private boolean ableToMoveRight;
    private boolean ableToMoveLeft;
    private boolean ableToMoveDown;
    private boolean gameOver = false;
    private boolean isFinalPiece = false;
    private int lvlNum;



    public Tetris(Canvas c){
        this.c = c;

        //each block should have a side length of moveDist
        pieceSpr = new Sprite(Empty, 0, 0, moveDist, moveDist, 10);

        //background is 38.5 wide by 21.5 tall
        left = new Sprite(LeftTB, 0, 0, moveDist*10, moveDist*22,1);
        right = new Sprite(RightTB, moveDist*28, 0, moveDist*11, moveDist*22,2);
        bottom = new Sprite(BottomTB, moveDist*10, moveDist*21, moveDist*18, moveDist*4,4);

        //In game stats
        statsRect = new Sprite(Rect, moveDist*30, moveDist, moveDist*8, moveDist*5, 5);
        //Score
        scoreSpr = new SpriteText(moveDist*30+20, moveDist*2+20, moveDist*4, moveDist*4, 6);
        lvlSpr = new SpriteText(moveDist*30+20, moveDist+20, moveDist*4, moveDist*7, 6);


        //Generates the sprites that exist in grid[][]
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                grid[i][j] = new Sprite(Empty, moveDist*10 + j*moveDist, i * moveDist, moveDist, moveDist, 1);
            }
        }

    }


    //grid is 18 wide by 21 tall
    public void newPiece(){
        //fix any old pieces into position
        for (int i = 0; i < well.length; ++i) {
            for (int j = 0; j < well[i].length; ++j) {
                if(well[i][j] == 1){
                    well[i][j] = 2;
                }
            }
        }
        clearRows();

        pieceNum = gen.nextInt(7);      //generate a random piece

        if(pieceNum == 0){      //create I piece
            for(int i = 0; i < 4; ++i){
                well[i][8] = 1;
            }
        } else if(pieceNum == 1){       //create J piece
            for(int i = 0; i < 3; ++i){
                well[i][8] = 1;
            }
            well[2][7] = 1;
        } else if(pieceNum == 2){       //create L piece
            for(int i = 0; i < 3; ++i){
                well[i][8] = 1;
            }
            well[2][9] = 1;
        } else if(pieceNum == 3){       //create O piece
            well[0][8] = 1;
            well[1][8] = 1;
            well[0][9] = 1;
            well[1][9] = 1;
        } else if(pieceNum == 4){       //create S piece
            well[0][8] = 1;
            well[1][8] = 1;
            well[1][7] = 1;
            well[0][9] = 1;
        } else if(pieceNum == 5){       //create T piece
            well[0][8] = 1;
            well[1][8] = 1;
            well[1][7] = 1;
            well[1][9] = 1;
        } else if(pieceNum == 6){       //create Z piece
            well[0][8] = 1;
            well[1][8] = 1;
            well[0][7] = 1;
            well[1][9] = 1;
        }

        ableToMoveRight = true;
        ableToMoveLeft = true;
        ableToMoveDown = true;
        rotation = 0;
        //check if the new piece is hitting a fixed piece
        for (int i = well.length-2; i >= 0; --i) {
            for (int j = well[i].length - 1; j >= 0; --j) {
                if(well[i][j] == 1 && well[i+1][j] == 2){
                    ableToMoveDown = false;
                }
            }
        }
        if(!ableToMoveDown){
            gameOver = true;
        }


    }

    public void moveRight() {
        //if ableToMoveRight is false no need to run this code
        if(ableToMoveRight){
            //check along right wall
            for (int i = 0; i < well.length; ++i) {
                if (well[i][well[i].length - 1] == 1) {
                    ableToMoveRight = false;
                }
            }

            //check if current piece is hitting a fixed piece
            for(int i = 0; i < well.length; ++i){
                for(int j = 0; j < well[i].length - 1; ++j){
                    if(well[i][j] == 1 && well[i][j+1] == 2){
                        ableToMoveRight = false;
                    }
                }
            }
        }

        //if it can move right then do it
        if(ableToMoveRight){
            //if it moves right it can move left again
            ableToMoveLeft = true;
            //iterate through entire well and move each piece over 1
            for (int i = well.length-1; i >= 0; --i) {
                for (int j = well[i].length - 2; j >= 0; --j) {
                    if(well[i][j] == 1){
                        well[i][j + 1] = 1;
                        well[i][j] = 0;
                    }
                }
            }
        }
    }


    public void moveLeft(){
        //if able to move left is false no need to run this code
        if(ableToMoveLeft){
            //check along left wall
            for(int i = 0; i < well.length; ++i){
                if(well[i][0] == 1){
                    ableToMoveLeft = false;
                }
            }

            //check if current piece is hitting a fixed piece
            for(int i = 0; i < well.length; ++i){
                for(int j = 1; j < well[i].length; ++j){
                    if(well[i][j] == 1 && well[i][j-1] == 2){
                        ableToMoveLeft = false;
                    }
                }
            }
        }


        //if it can move left then do it
        if(ableToMoveLeft){
            //if it moves left then it can move right again
            ableToMoveRight = true;
            for(int i = 0; i < well.length; ++i){
                for(int j = 1; j < well[i].length; ++j){
                    if(well[i][j] == 1){
                        well[i][j] = 0;
                        well[i][j-1] = 1;
                    }
                }
            }
        }
    }


    public void dropAll(){
        drop = 0;
        //if it cant move down no need to run this code
        if(ableToMoveDown){
            //check if the piece is at the bottom
            for(int j = 0; j < well[well.length-1].length; ++j){
                if(well[well.length-1][j] == 1){
                    ableToMoveDown = false;
                }
            }

            //check if current falling piece is hitting a fixed piece
            for (int i = well.length-2; i >= 0; --i) {
                for (int j = well[i].length - 1; j >= 0; --j) {
                    if(well[i][j] == 1 && well[i+1][j] == 2){
                        ableToMoveDown = false;
                    }
                }
            }
        }


        //if piece can moe down then do it
        if(ableToMoveDown){
            for (int i = well.length-2; i >= 0; --i) {
                for (int j = well[i].length - 1; j >= 0; --j) {
                    if(well[i][j] == 1){
                        well[i][j] = 0;
                        well[i+1][j] = 1;
                    }
                }
            }
        } else {        //if the piece cant move down create a new piece
            newPiece();
        }
    }

    public void clearRows(){
        boolean isCompleteRow;
        int numCleared = 0;

        for (int i = 0; i < well.length; ++i) {
            isCompleteRow = true;

            for (int j = 0; j < well[i].length; ++j) {
                if(well[i][j] != 2){
                    isCompleteRow = false;      //not a complete row, move on to the next one
                }
            }
            if(isCompleteRow){
                ++numCleared;
                for (int j = 0; j < well[i].length; ++j) {
                    well[i][j] = 0;             //clear the row if it is a complete row
                }
                //iterate through all rows above the completed one and shift down
                for(int k = i; k > 0; --k){
                    for(int j = well[i].length - 1; j >= 0; --j){
                        well[k][j] = well[k-1][j];
                    }
                }
            }
        }


        if(numCleared == 1)
            score += 100;
        else if(numCleared == 2)
            score += 300;
        else if(numCleared == 3)
            score += 500;
        else if(numCleared == 4)
            score += 800;


    }

    public void rotate(){
        int row = 0;
        int col = 0;
        boolean isRotated = true;
        ableToMoveLeft = true;
        ableToMoveRight = true;

        for(int i = well.length - 1; i >= 0; --i){
            for(int j = well[i].length - 1; j >= 0; --j){
                if(well[i][j] == 1){    //find the top left piece
                    row = i;
                    col = j;
                }
            }
        }

        if(pieceNum == 0){              //I piece
            if(rotation == 0 || rotation == 2){
                //check bounds
                if(!(col-1 < 0) && !(col+2 > well[row].length-1)     //checks for walls
                        && well[row+1][col-1] != 2 && well[row+1][col] != 2 //checks for fixed piece
                        && well[row+1][col+1] != 2 && well[row+1][col+2] != 2){

                    well[row][col] = 0;        //clear the current piece
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+3][col] = 0;

                    well[row+1][col-1] = 1;     //create the rotated piece
                    well[row+1][col] = 1;
                    well[row+1][col+1] = 1;
                    well[row+1][col+2] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1 || rotation == 3){
                if(!(row+4 > well.length-1)         //checks bottom wall
                        && well[row][col+1] != 2 && well[row+1][col+1] != 2       //checks for fixed piece
                        && well[row+2][col+1] != 2 && well[row+3][col+1] != 2){
                    //0,0 0,1 0,2 0,3
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;
                    well[row][col+3] = 0;
                    //1,0 1,1 1,2 1,3
                    well[row][col+1] = 1;
                    well[row+1][col+1] = 1;
                    well[row+2][col+1] = 1;
                    well[row+3][col+1] = 1;
                } else {
                    isRotated = false;
                }
            }
        } else if(pieceNum == 1){       //J piece
            if(rotation == 0){
                if(!(col+1 > well[row].length-1)
                        && well[row][col-1] != 2 && well[row+1][col-1] != 2
                        && well[row+1][col] != 2 && well[row+1][col+1] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+2][col-1] = 0;

                    well[row][col-1] = 1;
                    well[row+1][col-1] = 1;
                    well[row+1][col] = 1;
                    well[row+1][col+1] = 1;
                } else {
                    isRotated = false;
                }


            } else if(rotation == 1){
                if(!(row+2 > well.length-1)     //check for walls
                        && well[row][col+1] != 2 && well[row][col+2] != 2       //check for fixed piece
                        && well[row+1][col+1] != 2 && well[row+2][col+1] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col+1] = 0;
                    well[row+1][col+2] = 0;

                    well[row][col+1] = 1;
                    well[row][col+2] = 1;
                    well[row+1][col+1] = 1;
                    well[row+2][col+1] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 2){
                if(!(col-1 < 0)     //check for walls
                        && well[row+1][col] != 2 && well[row+1][col-1] != 2     //check for fixed piece
                        && well[row+1][col+1] != 2 && well[row+2][col+1] != 2){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;

                    well[row+1][col] = 1;
                    well[row+1][col-1] = 1;
                    well[row+1][col+1] = 1;
                    well[row+2][col+1] = 1;
                } else {
                    isRotated = false;
                }
            } else if(rotation == 3){
                if(!(row-1 < 0)     //check for walls
                        && well[row+1][col] != 2 && well[row+1][col+1] != 2     //check for fixed piece
                        && well[row][col+1] != 2 && well[row-1][col+1] != 2){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;
                    well[row+1][col+2] = 0;

                    well[row+1][col] = 1;
                    well[row+1][col+1] = 1;
                    well[row][col+1] = 1;
                    well[row-1][col+1] = 1;
                } else {
                    isRotated = false;
                }
            }
        } else if(pieceNum == 2) {      //L piece
            if(rotation == 0){
                if(!(col-1 < 0)     //check for walls
                        && well[row+1][col-1] != 2 && well[row+1][col] != 2     //check for fixed piece
                        && well[row+2][col-1] != 2 && well[row+1][col+1] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+2][col+1] = 0;

                    well[row+1][col-1] = 1;
                    well[row+1][col] = 1;
                    well[row+2][col-1] = 1;
                    well[row+1][col+1] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1){
                if(!(row-1 < 0)     //check for walls
                        && well[row-1][col] != 2 && well[row-1][col+1] != 2     //check for fixed piece
                        && well[row][col+1] != 2 && well[row+1][col+1] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;

                    well[row-1][col] = 1;
                    well[row-1][col+1] = 1;
                    well[row][col+1] = 1;
                    well[row+1][col+1] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 2){
                if(!(col+2 > well[row].length-1)        //check for walls
                        && well[row+1][col] != 2 && well[row+1][col+1] != 2     //check for fixed piece
                        && well[row+1][col+2] != 2 && well[row][col+2] != 2){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col+1] = 0;
                    well[row+2][col+1] = 0;

                    well[row+1][col] = 1;
                    well[row+1][col+1] = 1;
                    well[row+1][col+2] = 1;
                    well[row][col+2] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 3){
                if(!(row+2 > well.length-1)     //check for walls
                        && well[row][col-1] != 2 && well[row+1][col-1] != 2     //check for fixed piece
                        && well[row+2][col-1] != 2 && well[row+2][col] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;
                    well[row+1][col-2] = 0;

                    well[row][col-1] = 1;
                    well[row+1][col-1] = 1;
                    well[row+2][col-1] = 1;
                    well[row+2][col] = 1;
                } else {
                    isRotated = false;
                }

            }
        } else if(pieceNum == 3){       //O piece
            //a square doesnt rotate because its a square
        } else if(pieceNum == 4){       //S piece
            if(rotation == 0 || rotation == 2){
                if(!(row+2 > well.length-1)
                        && well[row][col-1] != 2 && well[row+1][col-1] != 2
                        && well[row+1][col] != 2 && well[row+2][col] != 2){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;

                    well[row][col-1] = 1;
                    well[row+1][col-1] = 1;
                    well[row+1][col] = 1;
                    well[row+2][col] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1 || rotation == 3){
                if(!(col+2 > well[row].length-1)
                        && well[row+2][col] != 2 && well[row+2][col+1] != 2
                        && well[row+1][col+1] != 2 && well[row+1][col+2] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col+1] = 0;
                    well[row+2][col+1] = 0;

                    well[row+1][col] = 1;
                    well[row+1][col+1] = 1;
                    well[row][col+1] = 1;
                    well[row][col+2] = 1;
                } else {
                    isRotated = false;
                }


            }



        } else if(pieceNum == 5){       //T piece
            if(rotation == 0){
                if(!(row+2 > well.length-1
                        && well[row][col] != 2 && well[row+1][col] != 2
                        && well[row+2][col] != 2 && well[row+1][col+1] != 2)){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;
                    well[row+1][col+1] = 0;

                    well[row][col] = 1;
                    well[row+1][col] = 1;
                    well[row+2][col] = 1;
                    well[row+1][col+1] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1){
                if(!(col-1 < 0)
                        && well[row+2][col] != 2 && well[row+1][col] != 2
                        && well[row+1][col-1] != 2 && well[row+1][col+1] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row][col+1] = 0;

                    well[row+2][col] = 1;
                    well[row+1][col] = 1;
                    well[row+1][col-1] = 1;
                    well[row+1][col+1] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 2){
                if(!(row-1 < 0)
                        && well[row][col] != 2 && well[row][col+1] != 2
                        && well[row+1][col+1] != 2 && well[row-1][col+1] != 2){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;
                    well[row+1][col+1] = 0;

                    well[row][col] = 1;
                    well[row][col+1] = 1;
                    well[row+1][col+1] = 1;
                    well[row-1][col+1] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 3){
                if(!(col+1 > well[row].length-1)
                        && well[row][col] != 2 && well[row+1][col] != 2
                        && well[row+1][col-1] != 2 && well[row+1][col+1] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+1][col-1] = 0;

                    well[row][col] = 1;
                    well[row+1][col] = 1;
                    well[row+1][col-1] = 1;
                    well[row+1][col+1] = 1;
                } else {
                    isRotated = false;
                }

            }


        } else if(pieceNum == 6){       //Z piece
            if(rotation == 0 || rotation == 2){
                if(!(row+2 > well.length-1)
                        && well[row+1][col+1] != 2 && well[row+2][col+1] != 2
                        && well[row][col+2] != 2 && well[row+1][col+2] != 2){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col+1] = 0;
                    well[row+1][col+2] = 0;

                    well[row+1][col+1] = 1;
                    well[row+2][col+1] = 1;
                    well[row][col+2] = 1;
                    well[row+1][col+2] = 1;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1 || rotation == 3){
                if(!(col-2 < 0)
                        && well[row][col-2] != 2 && well[row][col-1] != 2
                        && well[row+1][col-1] != 2 && well[row+1][col] != 2){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;
                    well[row+2][col-1] = 0;

                    well[row][col-2] = 1;
                    well[row][col-1] = 1;
                    well[row+1][col-1] = 1;
                    well[row+1][col] = 1;
                } else {
                    isRotated = false;
                }

            }
        }

        if(isRotated){
            ++rotation;
        }
        if(rotation == 4){
            rotation = 0;
        }

    }





    public void endGame(){
        //print the last piece
        if(!isFinalPiece){
            //Copy the state of well[][] over to grid[][]
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (well[i][j] == 0) {
                        grid[i][j].setImage(Empty);
                    } else {
                        grid[i][j].setImage(Square);
                    }
                }
            }
            isFinalPiece = true;
        }


    }



    @Override
    public void start() {
        c.put(left, "LeftTB");
        c.put(right, "RightTB");
        c.put(bottom, "BottomTB");
        c.put(scoreSpr, "Score");
        c.put(lvlSpr, "Level");
        c.put(statsRect, "Grey Rect");



        //Adds all of the content of gird[][] onto the canvas
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                c.add(grid[i][j]);
            }
        }

        newPiece();
    }

    @Override
    public void stop() {

    }




    @Override
    public void update() {


        if(!gameOver){

            if(lvlNum != 6){
                lvlSpr.setText("Level: "+lvlNum, Color.CYAN, 2);        //update level
                scoreSpr.setText("Score: "+score, Color.CYAN, 2);       //update score
            } else {
                //if max level set color to red and level to say max
                lvlSpr.setText("Level: MAX", Color.RED, 2);
                scoreSpr.setText("Score: "+score, Color.RED, 2);
            }



            //Copy the state of well[][] over to grid[][]
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (well[i][j] == 0) {
                        grid[i][j].setImage(Empty);
                    } else {
                        grid[i][j].setImage(Square);
                    }
                }
            }

            //update the level number
            if(score < 1000)
                lvlNum = 1;
            else if(score < 2000)
                lvlNum = 2;
            else if(score < 3000)
                lvlNum = 3;
            else if(score < 4000)
                lvlNum = 4;
            else if(score < 5000)
                lvlNum = 5;
            else
                lvlNum = 6;


            if(drop > 60 - (lvlNum-1) * 10){        //at higher lvl drop piece faster
                dropAll();
            }
            drop++;

        } else {
            endGame();
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
            case KeyEvent.VK_SPACE:
                rotate();
                break;

            case KeyEvent.VK_LEFT:
                moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                moveRight();
                break;

            case KeyEvent.VK_DOWN:
                dropAll();
                score += 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


