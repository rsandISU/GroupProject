
package tetris;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;

import engine.SpriteText;
import menu.Menu;
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
    SpriteText scoreSpr, lvlSpr, howToExit, howToPlayAgain, rotateControls, moveControls;
    Canvas c;
    Menu m;


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

    //In game stats background
    BufferedImage Rect = ResourceLoader.getImage("tetrisImages/empty.jpg");


    //Tetris vars
    private int moveDist = 50;
    private int drop;
    private Point pieceOrigin;
    private int currentPiece;
    private int rotation;
    private ArrayList<Integer> nextPieces = new ArrayList<Integer>();
    private int well[][] = new int[21][18];
    private Sprite grid[][] = new Sprite[21][18];
    private int score;
    private Random gen = new Random();
    private int pieceNum;
    private int prevPieceNum;
    private boolean ableToMoveRight;
    private boolean ableToMoveLeft;
    private boolean ableToMoveDown;
    private boolean isGameOver;
    private boolean isPaused;
    private int lvlNum;



    public Tetris(Canvas c, Menu m){
        this.c = c;
        this.m = m;

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

        //controlls
        rotateControls = new SpriteText(moveDist*30+20, moveDist*4, moveDist*4, moveDist*4, 6);
        moveControls = new SpriteText(moveDist*30+20, moveDist*5, moveDist*4, moveDist*4, 6);

        //when game is over rules to go back
        howToExit = new SpriteText(moveDist*14, moveDist*9, moveDist*4, moveDist*4, 6);

        //when game is over rules to play again
        howToPlayAgain = new SpriteText(moveDist*11+25, moveDist*11, moveDist*4, moveDist*4, 6);

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
                if(well[i][j] != 1 && well[i][j] != 0){
                    well[i][j] = 1;
                }
            }
        }
        clearRows();

        prevPieceNum = pieceNum;
        pieceNum = gen.nextInt(7) + 2;      //generate a random piece

        //pieceNum = 8;
        if(pieceNum == 2){      //create I piece
            for(int i = 0; i < 4; ++i){
                well[i][8] = pieceNum;
            }
        } else if(pieceNum == 3){       //create J piece
            for(int i = 0; i < 3; ++i){
                well[i][8] = pieceNum;
            }
            well[2][7] = pieceNum;
        } else if(pieceNum == 4){       //create L piece
            for(int i = 0; i < 3; ++i){
                well[i][8] = pieceNum;
            }
            well[2][9] = pieceNum;
        } else if(pieceNum == 5){       //create O piece
            well[0][8] = pieceNum;
            well[1][8] = pieceNum;
            well[0][9] = pieceNum;
            well[1][9] = pieceNum;
        } else if(pieceNum == 6){       //create S piece
            well[0][8] = pieceNum;
            well[1][8] = pieceNum;
            well[1][7] = pieceNum;
            well[0][9] = pieceNum;
        } else if(pieceNum == 7){       //create T piece
            well[0][8] = pieceNum;
            well[1][8] = pieceNum;
            well[1][7] = pieceNum;
            well[1][9] = pieceNum;
        } else if(pieceNum == 8){       //create Z piece
            well[0][8] = pieceNum;
            well[1][8] = pieceNum;
            well[0][7] = pieceNum;
            well[1][9] = pieceNum;
        }

        ableToMoveRight = true;
        ableToMoveLeft = true;
        ableToMoveDown = true;
        rotation = 0;
        //check if the new piece is hitting a fixed piece
        for (int i = well.length-2; i >= 0; --i) {
            for (int j = well[i].length - 1; j >= 0; --j) {
                if(well[i][j] == pieceNum && well[i+1][j] == 1){
                    ableToMoveDown = false;
                }
            }
        }
        if(!ableToMoveDown){
            isGameOver = true;
        }


    }

    public void moveRight() {
        //if ableToMoveRight is false no need to run this code
        if(ableToMoveRight){
            //check along right wall
            for (int i = 0; i < well.length; ++i) {
                if (well[i][well[i].length - 1] == pieceNum) {
                    ableToMoveRight = false;
                }
            }

            //check if current piece is hitting a fixed piece
            for(int i = 0; i < well.length; ++i){
                for(int j = 0; j < well[i].length - 1; ++j){
                    if(well[i][j] == pieceNum && well[i][j+1] == 1){
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
                    if(well[i][j] == pieceNum){
                        well[i][j + 1] = pieceNum;
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
                if(well[i][0] == pieceNum){
                    ableToMoveLeft = false;
                }
            }

            //check if current piece is hitting a fixed piece
            for(int i = 0; i < well.length; ++i){
                for(int j = 1; j < well[i].length; ++j){
                    if(well[i][j] == pieceNum && well[i][j-1] == 1){
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
                    if(well[i][j] == pieceNum){
                        well[i][j] = 0;
                        well[i][j-1] = pieceNum;
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
                if(well[well.length-1][j] == pieceNum){
                    ableToMoveDown = false;
                }
            }

            //check if current falling piece is hitting a fixed piece
            for (int i = well.length-2; i >= 0; --i) {
                for (int j = well[i].length - 1; j >= 0; --j) {
                    if(well[i][j] == pieceNum && well[i+1][j] == 1){
                        ableToMoveDown = false;
                    }
                }
            }
        }


        //if piece can move down then do it
        if(ableToMoveDown){
            for (int i = well.length-2; i >= 0; --i) {
                for (int j = well[i].length - 1; j >= 0; --j) {
                    if(well[i][j] == pieceNum){
                        well[i][j] = 0;
                        well[i+1][j] = pieceNum;
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
                if(well[i][j] != 1){
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
                if(well[i][j] == pieceNum){    //find the top left piece
                    row = i;
                    col = j;
                }
            }
        }

        if(pieceNum == 2){              //I piece
            if(rotation == 0 || rotation == 2){
                //check bounds
                if(!(col-1 < 0) && !(col+2 > well[row].length-1)     //checks for walls
                        && well[row+1][col-1] != 1 && well[row+1][col] != 1 //checks for fixed piece
                        && well[row+1][col+1] != 1 && well[row+1][col+2] != 1){

                    well[row][col] = 0;        //clear the current piece
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+3][col] = 0;

                    well[row+1][col-1] = pieceNum;     //create the rotated piece
                    well[row+1][col] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row+1][col+2] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1 || rotation == 3){
                if(!(row+4 > well.length-1)         //checks bottom wall
                        && well[row][col+1] != 1 && well[row+1][col+1] != 1       //checks for fixed piece
                        && well[row+2][col+1] != 1 && well[row+3][col+1] != 1){
                    //0,0 0,1 0,2 0,3
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;
                    well[row][col+3] = 0;
                    //1,0 1,1 1,2 1,3
                    well[row][col+1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row+2][col+1] = pieceNum;
                    well[row+3][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }
            }
        } else if(pieceNum == 3){       //J piece
            if(rotation == 0){
                if(!(col+1 > well[row].length-1)
                        && well[row][col-1] != 1 && well[row+1][col-1] != 1
                        && well[row+1][col] != 1 && well[row+1][col+1] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+2][col-1] = 0;

                    well[row][col-1] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+1][col] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }


            } else if(rotation == 1){
                if(!(row+2 > well.length-1)     //check for walls
                        && well[row][col+1] != 1 && well[row][col+2] != 1       //check for fixed piece
                        && well[row+1][col+1] != 1 && well[row+2][col+1] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col+1] = 0;
                    well[row+1][col+2] = 0;

                    well[row][col+1] = pieceNum;
                    well[row][col+2] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row+2][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 2){
                if(!(col-1 < 0)     //check for walls
                        && well[row+1][col] != 1 && well[row+1][col-1] != 1     //check for fixed piece
                        && well[row+1][col+1] != 1 && well[row+2][col+1] != 1){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;

                    well[row+1][col] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row+2][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }
            } else if(rotation == 3){
                if(!(row-1 < 0)     //check for walls
                        && well[row+1][col] != 1 && well[row+1][col+1] != 1     //check for fixed piece
                        && well[row][col+1] != 1 && well[row-1][col+1] != 1){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;
                    well[row+1][col+2] = 0;

                    well[row+1][col] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row][col+1] = pieceNum;
                    well[row-1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }
            }
        } else if(pieceNum == 4) {      //L piece
            if(rotation == 0){
                if(!(col-1 < 0)     //check for walls
                        && well[row+1][col-1] != 1 && well[row+1][col] != 1     //check for fixed piece
                        && well[row+2][col-1] != 1 && well[row+1][col+1] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+2][col+1] = 0;

                    well[row+1][col-1] = pieceNum;
                    well[row+1][col] = pieceNum;
                    well[row+2][col-1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1){
                if(!(row-1 < 0)     //check for walls
                        && well[row-1][col] != 1 && well[row-1][col+1] != 1     //check for fixed piece
                        && well[row][col+1] != 1 && well[row+1][col+1] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;

                    well[row-1][col] = pieceNum;
                    well[row-1][col+1] = pieceNum;
                    well[row][col+1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 2){
                if(!(col+2 > well[row].length-1)        //check for walls
                        && well[row+1][col] != 1 && well[row+1][col+1] != 1     //check for fixed piece
                        && well[row+1][col+2] != 1 && well[row][col+2] != 1){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col+1] = 0;
                    well[row+2][col+1] = 0;

                    well[row+1][col] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row+1][col+2] = pieceNum;
                    well[row][col+2] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 3){
                if(!(row+2 > well.length-1)     //check for walls
                        && well[row][col-1] != 1 && well[row+1][col-1] != 1     //check for fixed piece
                        && well[row+2][col-1] != 1 && well[row+2][col] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;
                    well[row+1][col-2] = 0;

                    well[row][col-1] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+2][col-1] = pieceNum;
                    well[row+2][col] = pieceNum;
                } else {
                    isRotated = false;
                }

            }
        } else if(pieceNum == 5){       //O piece
            //a square doesnt rotate because its a square
        } else if(pieceNum == 6){       //S piece
            if(rotation == 0 || rotation == 2){
                if(!(row+2 > well.length-1)
                        && well[row][col-1] != 1 && well[row+1][col-1] != 1
                        && well[row+1][col] != 1 && well[row+2][col] != 1){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;

                    well[row][col-1] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+1][col] = pieceNum;
                    well[row+2][col] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1 || rotation == 3){
                if(!(col+2 > well[row].length-1)
                        && well[row+2][col] != 1 && well[row+2][col+1] != 1
                        && well[row+1][col+1] != 1 && well[row+1][col+2] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col+1] = 0;
                    well[row+2][col+1] = 0;

                    well[row+1][col] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row][col+1] = pieceNum;
                    well[row][col+2] = pieceNum;
                } else {
                    isRotated = false;
                }


            }



        } else if(pieceNum == 7){       //T piece
            if(rotation == 0){
                if(!(row+2 > well.length-1
                        && well[row][col] != 1 && well[row+1][col] != 1
                        && well[row+2][col] != 1 && well[row+1][col+1] != 1)){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;
                    well[row+1][col+1] = 0;

                    well[row][col] = pieceNum;
                    well[row+1][col] = pieceNum;
                    well[row+2][col] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1){
                if(!(col-1 < 0)
                        && well[row+2][col] != 1 && well[row+1][col] != 1
                        && well[row+1][col-1] != 1 && well[row+1][col+1] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row][col+1] = 0;

                    well[row+2][col] = pieceNum;
                    well[row+1][col] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 2){
                if(!(row-1 < 0)
                        && well[row][col] != 1 && well[row][col+1] != 1
                        && well[row+1][col+1] != 1 && well[row-1][col+1] != 1){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row][col+2] = 0;
                    well[row+1][col+1] = 0;

                    well[row][col] = pieceNum;
                    well[row][col+1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                    well[row-1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 3){
                if(!(col+1 > well[row].length-1)
                        && well[row][col] != 1 && well[row+1][col] != 1
                        && well[row+1][col-1] != 1 && well[row+1][col+1] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+2][col] = 0;
                    well[row+1][col-1] = 0;

                    well[row][col] = pieceNum;
                    well[row+1][col] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+1][col+1] = pieceNum;
                } else {
                    isRotated = false;
                }

            }


        } else if(pieceNum == 8){       //Z piece
            if(rotation == 0 || rotation == 2){
                if(!(row+2 > well.length-1)
                        && well[row+1][col+1] != 1 && well[row+2][col+1] != 1
                        && well[row][col+2] != 1 && well[row+1][col+2] != 1){
                    well[row][col] = 0;
                    well[row][col+1] = 0;
                    well[row+1][col+1] = 0;
                    well[row+1][col+2] = 0;

                    well[row+1][col+1] = pieceNum;
                    well[row+2][col+1] = pieceNum;
                    well[row][col+2] = pieceNum;
                    well[row+1][col+2] = pieceNum;
                } else {
                    isRotated = false;
                }

            } else if(rotation == 1 || rotation == 3){
                if(!(col-2 < 0)
                        && well[row][col-2] != 1 && well[row][col-1] != 1
                        && well[row+1][col-1] != 1 && well[row+1][col] != 1){
                    well[row][col] = 0;
                    well[row+1][col] = 0;
                    well[row+1][col-1] = 0;
                    well[row+2][col-1] = 0;

                    well[row][col-2] = pieceNum;
                    well[row][col-1] = pieceNum;
                    well[row+1][col-1] = pieceNum;
                    well[row+1][col] = pieceNum;
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

        if(!isPaused){
            //Copy the state of well[][] over to grid[][]
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {      //print the last piece
                    if (well[i][j] == 0) {
                        grid[i][j].setImage(Empty);
                    } else if(well[i][j] == 1){
                        //fixed piece dont change sprite
                    } else if(well[i][j] == 2){
                        grid[i][j].setImage(IPiece);
                    } else if(well[i][j] == 3){
                        grid[i][j].setImage(JPiece);
                    } else if(well[i][j] == 4){
                        grid[i][j].setImage(LPiece);
                    } else if(well[i][j] == 5){
                        grid[i][j].setImage(OPiece);
                    } else if(well[i][j] == 6){
                        grid[i][j].setImage(SPiece);
                    } else if(well[i][j] == 7){
                        grid[i][j].setImage(TPiece);
                    } else if(well[i][j] == 8){
                        grid[i][j].setImage(ZPiece);
                    }
                }
            }
            m.addTetrisScore(score);

            isPaused = true;        //dont print any more pieces
        }

    }



    @Override
    public void start() {
        c.put(left, "LeftTB");
        c.put(right, "RightTB");
        c.put(bottom, "BottomTB");
        c.put(statsRect, "Grey Rect");
        c.put(scoreSpr, "Score");
        c.put(lvlSpr, "Level");
        c.put(rotateControls, "Rotate Controls");
        c.put(moveControls, "Move Controls");
        c.put(howToExit, "Exit Rules");
        c.put(howToPlayAgain, "Play Again Rules");

        //initialize vars
        isGameOver = false;
        isPaused = false;
        drop = 0;
        score = 0;

        howToExit.setVisible(false);
        howToPlayAgain.setVisible(false);

        rotateControls.setText("SPACE to rotate", Color.CYAN, 2);
        moveControls.setText("ARROW KEYS to move", Color.CYAN, 1.7);


        for (int i = 0; i < well.length; ++i) {
            for (int j = 0; j < well[i].length; ++j) {
                well[i][j] = 0;
            }
        }

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


            if (!isGameOver) {

                if (drop > 60 - (lvlNum - 1) * 10) {        //at higher lvl drop piece faster
                    dropAll();
                }
                drop++;

                if (lvlNum != 6) {
                    lvlSpr.setText("Level: " + lvlNum, Color.CYAN, 2);        //update level
                    scoreSpr.setText("Score: " + score, Color.CYAN, 2);       //update score
                } else {
                    //if max level set color to red and level to say max
                    lvlSpr.setText("Level: MAX", Color.RED, 2);
                    scoreSpr.setText("Score: " + score, Color.RED, 2);
                }


                //Copy the state of well[][] over to grid[][]
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (well[i][j] == 0) {
                            grid[i][j].setImage(Empty);
                        } else if (well[i][j] == 1) {
                            if(grid[i][j].getImage() == Empty){
                                if(prevPieceNum == 2)
                                    grid[i][j].setImage(IPiece);
                                else if(prevPieceNum == 3)
                                    grid[i][j].setImage(JPiece);
                                else if(prevPieceNum == 4)
                                    grid[i][j].setImage(LPiece);
                                else if(prevPieceNum == 5)
                                    grid[i][j].setImage(OPiece);
                                else if(prevPieceNum == 6)
                                    grid[i][j].setImage(SPiece);
                                else if(prevPieceNum == 7)
                                    grid[i][j].setImage(TPiece);
                                else if(prevPieceNum == 8)
                                    grid[i][j].setImage(ZPiece);


                            }
                        } else if (well[i][j] == 2) {
                            grid[i][j].setImage(IPiece);
                        } else if (well[i][j] == 3) {
                            grid[i][j].setImage(JPiece);
                        } else if (well[i][j] == 4) {
                            grid[i][j].setImage(LPiece);
                        } else if (well[i][j] == 5) {
                            grid[i][j].setImage(OPiece);
                        } else if (well[i][j] == 6) {
                            grid[i][j].setImage(SPiece);
                        } else if (well[i][j] == 7) {
                            grid[i][j].setImage(TPiece);
                        } else if (well[i][j] == 8) {
                            grid[i][j].setImage(ZPiece);
                        }
                    }
                }

                //update the level number
                if (score < 1000)
                    lvlNum = 1;
                else if (score < 2000)
                    lvlNum = 2;
                else if (score < 3000)
                    lvlNum = 3;
                else if (score < 4000)
                    lvlNum = 4;
                else if (score < 5000)
                    lvlNum = 5;
                else
                    lvlNum = 6;




            } else {
                howToExit.setVisible(true);
                howToExit.setText("Press B to exit", Color.RED, 3);
                howToPlayAgain.setVisible(true);
                howToPlayAgain.setText("Press A to play again", Color.RED, 3);
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
            case KeyEvent.VK_B:
                if(isPaused)
                    c.setElement("MENU");
                break;
            case KeyEvent.VK_A:
                if(isPaused)
                    start();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


