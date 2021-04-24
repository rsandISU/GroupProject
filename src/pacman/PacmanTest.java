package pacman;

import engine.*;
import engine.Canvas;
import menu.Menu;
import util.ResourceLoader;
import util.SoundLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class PacmanTest implements GameElement, MouseMotionListener {
    //region Sprites, Canvas, Menu
    Sprite backgroundspr;
    Sprite boardspr;
    Sprite pacmanspr;
    Sprite redspr;
    Sprite doorspr;
    SpriteText scorespr;
    SpriteText gameoverspr;
    SpriteText namespr;
    SpriteText inputspr;
    SpriteText ready;
    Menu m;
    Canvas c;
    //endregion

    //region Sounds

    private Soundable introMusic = SoundLoader.getSound("pacman/introsong.wav");
    private Soundable ghostSiren = SoundLoader.getSound("pacman/ghostsiren.wav");
    private Soundable waka = SoundLoader.getSound("pacman/waka.wav");
    private Soundable ghostEat = SoundLoader.getSound("pacman/ghosteat.wav");
    private Soundable death = SoundLoader.getSound("pacman/death.wav");
    //endregion

    //region Game vars
    private int score = 0;
    private int level = 1;
    private boolean levelComplete = false;
    private final int introLength = 270;
    private int intro = introLength;
    private boolean game = true;
    private boolean gameOver = false;
    //endregion

    //region Pellets
    private final int pelletNumber = 240;
    private final int pelletSize = 10;
    private final ArrayList<Sprite> pelletList = new ArrayList<>();
    //endregion

    //region Power Pellets
    private final ArrayList<Sprite> powerPelletList = new ArrayList<>();
    private boolean powerPelletCountDown = false;
    private int powerPelletStartTime = 800;
    private int powerPelletTime = powerPelletStartTime;
    //endregion

    //region Boundaries
    private final ArrayList<Rectangle> boundaryList = new ArrayList<>();
    //endregion

    //region Buffered Images
    BufferedImage neutral = ResourceLoader.getImage("pacman/circle.png");
    BufferedImage background = ResourceLoader.getImage("pacman/background.png");
    BufferedImage board = ResourceLoader.getImage("pacman/board.png");
    BufferedImage pellet = ResourceLoader.getImage("pacman/pellet.png");
    BufferedImage door = ResourceLoader.getImage("pacman/door.png");
    BufferedImage scaredghost1 = ResourceLoader.getImage("pacman/scaredghost1.png");
    BufferedImage scaredghost2 = ResourceLoader.getImage("pacman/scaredghost2.png");
    BufferedImage eatenghost = ResourceLoader.getImage("pacman/eatenghost.png");
    //endregion

    //region Pacman vars
    private int x = 940;
    private int y = 765;
    private int speed = 3;
    private int width = 45;
    private int height = 45;
    private Rectangle pacmanR = new Rectangle(x, y, width, height);
    int deathCountStart = 80;
    int deathCount = 0;
    BufferedImage pacmanright1 = ResourceLoader.getImage("pacman/pacmanright1.png");
    BufferedImage pacmanright2 = ResourceLoader.getImage("pacman/pacmanright2.png");
    BufferedImage pacmanleft1 = ResourceLoader.getImage("pacman/pacmanleft1.png");
    BufferedImage pacmanleft2 = ResourceLoader.getImage("pacman/pacmanleft2.png");
    BufferedImage pacmandown1 = ResourceLoader.getImage("pacman/pacmandown1.png");
    BufferedImage pacmandown2 = ResourceLoader.getImage("pacman/pacmandown2.png");
    BufferedImage pacmanup1 = ResourceLoader.getImage("pacman/pacmanup1.png");
    BufferedImage pacmanup2 = ResourceLoader.getImage("pacman/pacmanup2.png");
    BufferedImage pacmandeath1 = ResourceLoader.getImage("pacman/pacmandeath1.png");
    BufferedImage pacmandeath2 = ResourceLoader.getImage("pacman/pacmandeath2.png");
    BufferedImage pacmandeath3 = ResourceLoader.getImage("pacman/pacmandeath3.png");
    BufferedImage pacmandeath4 = ResourceLoader.getImage("pacman/pacmandeath4.png");
    BufferedImage pacmandeath5 = ResourceLoader.getImage("pacman/pacmandeath5.png");
    BufferedImage pacmandeath6 = ResourceLoader.getImage("pacman/pacmandeath6.png");
    BufferedImage pacmandeath7 = ResourceLoader.getImage("pacman/pacmandeath7.png");
    BufferedImage pacmandeath8 = ResourceLoader.getImage("pacman/pacmandeath8.png");
    BufferedImage pacmandeath9 = ResourceLoader.getImage("pacman/pacmandeath9.png");
    BufferedImage pacmandeath10 = ResourceLoader.getImage("pacman/pacmandeath10.png");
    BufferedImage pacmandeath11 = ResourceLoader.getImage("pacman/pacmandeath11.png");
    //endregion

    //region Animation vars
    private int animation = 0;
    private boolean ghostAnimationType = false;
    private int pacmanAnimationType = 0;
    //endregion

    //region Blinky (red)
    private int blinkyX = 950;
    private int blinkyY = 525;
    private int blinkyLastX = 0;
    private int blinkyLastY = 0;
    private int blinkySpeed = 2;
    private String blinkyLastDirection = "left";
    private String blinkyLastLockDirection = "lockleft";
    String blinkyLastLockDirection2 = "lockup";
    private final Random blinkyRand = new Random(123);
    private int blinkyTargetX = x;
    private int blinkyTargetY = y;
    private String blinkyTarget = "pacman";
    private int blinkyCounter = 0;
    private int blinkyWidth = 45;
    private int blinkyHeight = 45;
    Rectangle blinkyR = new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight);
    private int blinkyCoolDown = 400;
    private boolean blinkyScared = false;
    private int blinkyEatenPause = 20;
    BufferedImage blinkyright1 = ResourceLoader.getImage("pacman/blinkyright1.png");
    BufferedImage blinkyright2 = ResourceLoader.getImage("pacman/blinkyright2.png");
    BufferedImage blinkyleft1 = ResourceLoader.getImage("pacman/blinkyleft1.png");
    BufferedImage blinkyleft2 = ResourceLoader.getImage("pacman/blinkyleft2.png");
    BufferedImage blinkydown1 = ResourceLoader.getImage("pacman/blinkydown1.png");
    BufferedImage blinkydown2 = ResourceLoader.getImage("pacman/blinkydown2.png");
    BufferedImage blinkyup1 = ResourceLoader.getImage("pacman/blinkyup1.png");
    BufferedImage blinkyup2 = ResourceLoader.getImage("pacman/blinkyup2.png");
    //endregion

    public PacmanTest(Canvas c, Menu m) {

        //region This
        this.c = c;
        this.m = m;
        //endregion

        //region Sprites
        //Background
        backgroundspr = new Sprite(background, 528, 0, 864, 1080, 0);

        //Board
        boardspr = new Sprite(board, 540, 116, 840, 887, 1);

        //Door
        doorspr = new Sprite(door, 934, 482, 56, 8, 2);

        //Score
        scorespr = new SpriteText(924, 80, 200, 200, 2);
        scorespr.setText(""+score, Color.lightGray, 2);

        //Ready
        ready = new SpriteText(894, 604, 2);
        ready.setText("Ready!", Color.yellow, 2.4);

        //Pacman
        pacmanspr = new Sprite(pacmanright1, x, y, width, height, 2);

        //Blinky (red)
        redspr = new Sprite(blinkyup1, blinkyX, blinkyY, blinkyWidth, blinkyHeight, 3);

        //Game Over Sprites
        namespr = new SpriteText(750, 800, 200, 200, 6);
        namespr.setVisible(false);
        inputspr = new SpriteText(924, 80, 200, 200, 6);
        inputspr.setVisible(false);
        gameoverspr = new SpriteText(595, 100, 500, 500, 6);
        gameoverspr.setVisible(false);
        //endregion

        //region Pellets
        //Horizontal Lines always take precedence over vertical lines
        //H1 and V1 both start at the top left corner
        //Horizontal Line 1
        //Pellet variables
        int pelletNumberH1 = 24;
        int pelletDistanceX = 30;
        for(int i = 0; i < pelletNumberH1 +2; i++){

            if(i != 12 && i != 13)
            pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 165, pelletSize, pelletSize, 2));

        }
        //Horizontal Line 2
        int pelletNumberH2 = 26;
        for(int i = 0; i < pelletNumberH2; i++){

            pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 279, pelletSize, pelletSize, 2));

        }
        //Horizontal Line 3
        int pelletNumberH3 = 20;
        for(int i = 0; i < pelletNumberH3 +6; i++){

            if(i != 6 && i != 7 && i != 12 && i != 13 && i != 18 && i!= 19) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 360, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 4
        int pelletNumberH4 = 24;
        for(int i = 0; i < pelletNumberH4 +2; i++){

            if(i != 12 && i != 13) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 699, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 5
        int pelletNumberH5 = 18;
        for(int i = 0; i < pelletNumberH5 +8; i++){

            if(i != 0 && i != 3 && i != 4 && i != 12 && i != 13 && i != 21 && i != 22 && i != 25) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 783, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 6
        int pelletNumberH6 = 20;
        for(int i = 0; i < pelletNumberH6 +6; i++){

            if(i != 6 && i != 7 && i != 12 && i != 13 && i != 18 && i!= 19) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 867, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 7
        int pelletNumberH7 = 26;
        for(int i = 0; i < pelletNumberH7; i++){

                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 948, pelletSize, pelletSize, 2));

        }


        //Vertical Line 1
        int pelletNumberV1 = 8;
        int pelletDistanceY = 28;
        for(int i = 0; i < pelletNumberV1 +21; i++){
            if(i == 1 || i == 3 || i == 5 || i == 6 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 585, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 2
        int pelletNumberV2 = 2;
        for(int i = 0; i < pelletNumberV2 +27; i++){
            if(i == 24 || i == 23) {
                pelletList.add(new Sprite(pellet, 645, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 3
        int pelletNumberV3 = 20;
        for(int i = 0; i < pelletNumberV3 +9; i++){
            if(i != 0 && i != 4 && i != 7 && i != 19 && i != 22 && i != 25 && i != 26 && i != 27 && i != 28)
            pelletList.add(new Sprite(pellet, 735, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));

        }
        //Vertical Line 4
        int pelletNumberV4 = 4;
        for(int i = 0; i < pelletNumberV4 +25; i++){
            if(i == 5 || i == 6 || i == 23 || i == 24) {
                pelletList.add(new Sprite(pellet, 825, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 5
        int pelletNumberV5 = 7;
        for(int i = 0; i < pelletNumberV5 +22; i++){
            if(i == 1 || i == 2 || i == 3 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 915, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 6
        int pelletNumberV6 = 7;
        for(int i = 0; i < pelletNumberV6 +22; i++){
            if(i == 1 || i == 2 || i == 3 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 1005, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 7
        int pelletNumberV7 = 4;
        for(int i = 0; i < pelletNumberV7 +25; i++){
            if(i == 5 || i == 6 || i == 23 || i == 24) {
                pelletList.add(new Sprite(pellet, 1095, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 8
        int pelletNumberV8 = 20;
        for(int i = 0; i < pelletNumberV8 +9; i++){
            if(i != 0 && i != 4 && i != 7 && i != 19 && i != 22 && i != 25 && i != 26 && i != 27 && i != 28)
                pelletList.add(new Sprite(pellet, 1185, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));

        }
        //Vertical Line 9
        int pelletNumberV9 = 2;
        for(int i = 0; i < pelletNumberV9 +27; i++){
            if(i == 24 || i == 23) {
                pelletList.add(new Sprite(pellet, 1275, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 10
        int pelletNumberV10 = 8;
        for(int i = 0; i < pelletNumberV10 +21; i++){
            if(i == 1 || i == 3 || i == 5 || i == 6 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 1335, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //endregion

        //region Power Pellets
        powerPelletList.add(new Sprite(pellet, 575, 211, 30, 30, 2));
        powerPelletList.add(new Sprite(pellet, 1325, 211, 30, 30, 2));
        powerPelletList.add(new Sprite(pellet, 575, 775, 30, 30, 2));
        powerPelletList.add(new Sprite(pellet, 1325, 775, 30, 30, 2));
        //endregion

        //region Boundaries
        //Block1
        boundaryList.add(new Rectangle(628, 196, 80, 52));
        //Block2
        boundaryList.add(new Rectangle(772, 196, 114, 52));
        //Block3
        boundaryList.add(new Rectangle(1040, 196, 114, 52));
        //Block4
        boundaryList.add(new Rectangle(1216, 196, 80, 52));
        //Block5
        boundaryList.add(new Rectangle(628, 308, 80, 28));
        //Block6
        boundaryList.add(new Rectangle(1216, 308, 80, 28));
        //Block7
        boundaryList.add(new Rectangle(772, 563, 28, 107));
        //Block8
        boundaryList.add(new Rectangle(1126, 563, 28, 107));
        //Block9
        boundaryList.add(new Rectangle(772, 732, 114, 28));
        //Block10
        boundaryList.add(new Rectangle(1040, 732, 114, 28));
        //Block12
        boundaryList.add(new Rectangle(772, 308, 28, 194));
        //Block13
        boundaryList.add(new Rectangle(800, 391, 86, 28));
        //Block14
        boundaryList.add(new Rectangle(860, 308, 204, 28));
        //Block15
        boundaryList.add(new Rectangle(948, 336, 28, 83));
        //Block16
        boundaryList.add(new Rectangle(1126, 308, 28, 194));
        //Block17
        boundaryList.add(new Rectangle(1040, 391, 86, 28));
        //Block18
        boundaryList.add(new Rectangle(860, 642, 204, 28));
        //Block19
        boundaryList.add(new Rectangle(948, 670, 28, 90));
        //Block20
        boundaryList.add(new Rectangle(628, 900, 257, 28));
        //Block21
        boundaryList.add(new Rectangle(772, 818, 28, 82));
        //Block22
        boundaryList.add(new Rectangle(860, 818, 204, 28));
        //Block23
        boundaryList.add(new Rectangle(948, 846, 28, 82));
        //Block24
        boundaryList.add(new Rectangle(1040, 900, 256, 28));
        //Block25
        boundaryList.add(new Rectangle(1126, 818, 28, 82));
        //Block26
        boundaryList.add(new Rectangle(680, 732, 28, 114));
        //Block27
        boundaryList.add(new Rectangle(628, 732, 52, 28));
        //Block28
        boundaryList.add(new Rectangle(1216, 732, 28, 114));
        //Block29
        boundaryList.add(new Rectangle(1244, 732, 52, 28));
        //Block30
        boundaryList.add(new Rectangle(948, 138, 28, 110));
        //Block31
        boundaryList.add(new Rectangle(562, 818, 58, 28));
        //Block32
        boundaryList.add(new Rectangle(1302, 818, 58, 28));
        //Block33
        boundaryList.add(new Rectangle(550, 390, 158, 112));
        //Block34
        boundaryList.add(new Rectangle(550, 126, 12, 264));
        //Block35
        boundaryList.add(new Rectangle(562, 126, 798, 12));
        //Block36
        boundaryList.add(new Rectangle(1360, 126, 12, 264));
        //Block37
        boundaryList.add(new Rectangle(1216, 390, 156, 112));
        //Block38
        boundaryList.add(new Rectangle(1216, 563, 156, 112));
        //Block39
        boundaryList.add(new Rectangle(1360, 670, 12, 326));
        //Block40
        boundaryList.add(new Rectangle(562, 984, 798, 12));
        //Block41
        boundaryList.add(new Rectangle(550, 670, 12, 326));
        //Block42
        boundaryList.add(new Rectangle(550, 563, 158, 112));
        //Block43
        boundaryList.add(new Rectangle(992, 478, 62, 12));
        //Block44
        boundaryList.add(new Rectangle(1052, 478, 12, 108));
        //Block45
        boundaryList.add(new Rectangle(872, 574, 180, 12));
        //Block46
        boundaryList.add(new Rectangle(860, 478, 12, 108));
        //Block47
        boundaryList.add(new Rectangle(872, 478, 62 + 56, 12));
        //endregion

    }

    @Override
    public void start() {

        //region Put
        c.put(backgroundspr, "background");
        c.put(boardspr, "board");
        c.put(scorespr, "score");
        c.put(namespr, "name");
        c.put(gameoverspr, "gameover");
        c.put(inputspr, "inputname");
        c.put(ready, "start");
        c.put(doorspr, "door");
        c.put(pacmanspr, "pacman");
        c.put(redspr, "Blinky");
        //endregion

        //region Pellets and PowerPellets
        for(int i = 0; i < pelletNumber; i++){
            c.put(pelletList.get(i), "pellet" + i);

        }
        for(int i = 0; i < 4; i++){
            c.put(powerPelletList.get(i), "powerPellet" + i);

        }
        //endregion

    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

        //region Intro
        if(intro > 0){
            intro = intro - 1;
            ready.setVisible(true);
            game = false;
            if(!introMusic.clipPlaying()){
                introMusic.play();
            }
        }else if(!gameOver){
            game = true;
            introMusic.haltClip();
            ready.setVisible(false);
        }
        //endregion

        //region Pacman Movement
        pacmanR = new Rectangle(x, y, width, height);
        if(game) {
            if (c.getKeysDown().contains('s') && blockCollideTestY(true, "pacman")) {
                y = y + speed;
                if (pacmanAnimationType == 0) {
                    pacmanspr.setImage(pacmandown1);
                } else if (pacmanAnimationType == 1) {
                    pacmanspr.setImage(pacmandown2);
                } else {
                    pacmanspr.setImage(neutral);
                }
            } else if (c.getKeysDown().contains('w') && blockCollideTestY(false, "pacman")) {
                y = y - speed;
                if (pacmanAnimationType == 0) {
                    pacmanspr.setImage(pacmanup1);
                } else if (pacmanAnimationType == 1) {
                    pacmanspr.setImage(pacmanup2);
                } else {
                    pacmanspr.setImage(neutral);
                }
            } else if (c.getKeysDown().contains('d') && blockCollideTestX(true, "pacman")) {
                x = x + speed;
                if (pacmanAnimationType == 0) {
                    pacmanspr.setImage(pacmanright1);
                } else if (pacmanAnimationType == 1) {
                    pacmanspr.setImage(pacmanright2);
                } else {
                    pacmanspr.setImage(neutral);
                }
            } else if (c.getKeysDown().contains('a') && blockCollideTestX(false, "pacman")) {
                x = x - speed;
                if (pacmanAnimationType == 0) {
                    pacmanspr.setImage(pacmanleft1);
                } else if (pacmanAnimationType == 1) {
                    pacmanspr.setImage(pacmanleft2);
                } else {
                    pacmanspr.setImage(neutral);
                }
            }
        }
        //endregion

        //region Animation
        animation = animation +1;
        if(animation % 10 == 0) ghostAnimationType = !ghostAnimationType;
        if(animation % 5 == 0){
            pacmanAnimationType = pacmanAnimationType + 1;
            if(pacmanAnimationType > 2) pacmanAnimationType = 0;
        }
        //endregion

        //region Blinky movement
        blinkyR = new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight);
        if(game) {
            if (blinkyCoolDown > 0) {
                blinkyCoolDown = blinkyCoolDown - 1;
            }
            if (blinkyCoolDown == 0) {
                if (blinkyX > 942) blinkyX = blinkyX - 1;
                else if (blinkyX < 942) blinkyX = blinkyX + 1;
                else if (blinkyY > 424) blinkyY = blinkyY - 1;
                else if (blinkyY < 424) blinkyY = blinkyY + 1;
                else blinkyCoolDown = -1;
            }
            if (blinkyCoolDown == -1) {
                blinkyLastX = blinkyX;
                blinkyLastY = blinkyY;
                //This is the actual movement line
                blinkyLastDirection = blinkyMove(blinkyLastDirection);
                if(!ghostSiren.clipPlaying()) ghostSiren.play();
                if (blinkyLastX == blinkyX && blinkyLastY == blinkyY) {
                    blinkyTarget = "pacman";
                }
            }
            if (blinkyScared) {
                blinkyTarget = "away";
            }
        }
        //endregion

        //region Wrapping
        //Pacman
        //Left to right
        if(x < 540){
            //Blinky
            if(Math.abs(blinkyX - x) < 300 && Math.abs(blinkyY - y) < 200 && blinkyTarget.equals("pacman")){
                //Blinky
                blinkyTarget = "leftwrap";
                blinkyTargetX = 538;
                blinkyTargetY = 512;
            }
            x = 1340;
        }
        //Right to left
        if(x > 1340){
            //Blinky
            if(Math.abs(blinkyX - x) < 300 && Math.abs(blinkyY - y) < 200 && blinkyTarget.equals("pacman")){
                //Blinky
                blinkyTarget = "rightwrap";
                blinkyTargetX = 1342;
                blinkyTargetY = 512;
            }
            x = 540;
        }

        //Blinky
        //Left to right
        if(blinkyX < 540){
            blinkyX = 1340;
            if(blinkyTarget.equals("leftwrap")) blinkyTarget = "pacman";

        }
        //Right to left
        if(blinkyX > 1340) {
            blinkyX = 540;
            if(blinkyTarget.equals("rightwrap")) blinkyTarget = "pacman";
        }

//endregion

        //region Pellets
        for(int i = 0; i < pelletNumber; i++){

            Rectangle pelletR = new Rectangle(pelletList.get(i).getX(), pelletList.get(i).getY(), pelletSize, pelletSize);
            if(pacmanR.intersects(pelletR) && pelletList.get(i).isVisible()){

                if(!waka.clipPlaying()) waka.play();
                pelletList.get(i).setVisible(false);
                score = score + 10;

            }

        }
        //endregion

        //region Power Pellets
        for(int i = 0; i < 4; i++){

            int powerPelletSize = 30;
            Rectangle powerPelletR = new Rectangle(powerPelletList.get(i).getX(), powerPelletList.get(i).getY(), powerPelletSize, powerPelletSize);
            if(pacmanR.intersects(powerPelletR) && powerPelletList.get(i).isVisible()){

                powerPelletList.get(i).setVisible(false);
                powerPelletTime = powerPelletStartTime;
                powerPelletCountDown = true;
               //Blinky
                if(blinkyCoolDown == -1){
                   blinkyScared = true;
               }

            }

        }

        //Blinky
        if(powerPelletCountDown){
            powerPelletTime = powerPelletTime - 1;
            if(powerPelletTime < 0){
                blinkyScared = false;

            }

        }
//endregion

        //region Eating Ghosts
        //Blinky
        if(pacmanR.intersects(new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight)) && blinkyScared && redspr.getImage() != eatenghost){

            score = score + 200;
            ghostEat.play();
            blinkyEatenPause = 20;
            redspr.setImage(eatenghost);

        }
        if(redspr.getImage() == eatenghost){
            blinkyCoolDown = 400;
            if(blinkyEatenPause < 0){
                blinkyScared = false;
                blinkyTarget = "pacman";
                redspr.setImage(blinkyup1);
                blinkyX = 950;
                blinkyY = 525;
            }
            blinkyEatenPause = blinkyEatenPause - 1;
        }
        //endregion

        //region Pacman coordinates update
        pacmanspr.setX(x);
        pacmanspr.setY(y);
        //endregion

        //region Blinky coordinates update
        redspr.setX(blinkyX);
        redspr.setY(blinkyY);
        if(blinkyTarget.equals("pacman")) blinkyTargetX = x;
        if(blinkyTarget.equals("pacman")) blinkyTargetY = y;
        if(blinkyTarget.equals("leftwrap")){
            blinkyTargetX = 538;
            blinkyTargetY = 512;
        }
        if(blinkyTarget.equals("rightwrap")){
            blinkyTargetX = 1342;
            blinkyTargetY = 512;
        }
        if(blinkyTarget.equals("away") && x > blinkyX) blinkyTargetX = x - 1000;
        if(blinkyTarget.equals("away") && x < blinkyX) blinkyTargetX = x + 1000;
        if(blinkyTarget.equals("away") && y < blinkyY) blinkyTargetY = y - 1000;
        if(blinkyTarget.equals("away") && y > blinkyX) blinkyTargetY = y + 1000;
        //endregion

        //region Pellet Scanning
        int collectedCount = 0;
        for(int i = 0; i < pelletNumber; i++){

            if(!pelletList.get(i).isVisible()){

            collectedCount = collectedCount + 1;

            }
                if(collectedCount == pelletNumber) levelComplete = true;
        }
        if(levelComplete){
            level = level + 1;
            levelReset();
        }
//endregion

        //region Update Score
        scorespr.setText(""+score, Color.lightGray, 2);
        //endregion

        //region GAME OVER
        if(pacmanR.intersects(new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight)) && !blinkyScared){

            if(game){
                m.addPacmanScore(score);
            }

            if(!gameOver) deathCount = deathCountStart;

            game = false;
            gameOver = true;


            if(deathCount > 0){
            pacmanspr.setImage(pacmandeath1);
                if(deathCount > 75){
                    pacmanspr.setImage(pacmandeath2);
                }else if(deathCount > 68) {
                    pacmanspr.setImage(pacmandeath3);
                }else if(deathCount > 61) {
                    pacmanspr.setImage(pacmandeath4);
                }else if(deathCount > 54) {
                    pacmanspr.setImage(pacmandeath5);
                }else if(deathCount > 47) {
                    pacmanspr.setImage(pacmandeath6);
                }else if(deathCount > 40) {
                    pacmanspr.setImage(pacmandeath7);
                }else if(deathCount > 33) {
                    pacmanspr.setImage(pacmandeath8);
                }else if(deathCount > 26) {
                    pacmanspr.setImage(pacmandeath9);
                }else if(deathCount > 19) {
                    pacmanspr.setImage(pacmandeath10);
                }else if(deathCount > 12) {
                    pacmanspr.setImage(pacmandeath11);
                }else if(deathCount > 5) {
                    pacmanspr.setVisible(false);
                }


                    if(!death.clipPlaying()) death.play();
                deathCount = deathCount - 1;

            }else {



                backgroundspr.setLayer(5);
                gameoverspr.setText("GAME OVER", Color.lightGray, 7);
                namespr.setText("PRESS SPACEBAR", Color.lightGray, 2.4);
                namespr.setVisible(true);
                inputspr.setVisible(true);
                gameoverspr.setVisible(true);


                if (c.getKeysDown().contains(' ')) {
                    gameReset();
                    c.setElement("MENU");
                }
            }

        }
        //endregion


    }


    //Blinky Direction deciding method
    public String blinkyMove(String lastDirection){

        int xDifference = Math.abs(blinkyTargetX - blinkyX);
        int yDifference = Math.abs(blinkyTargetY - blinkyY);
        int lockNumber = 160;
        int randomDirection = blinkyRand.nextInt(2);

        //Lockdown
        if(lastDirection.equals("lockdown") && blinkyCounter < lockNumber){

            if(blinkyCounter == lockNumber-1){
                blinkyCounter = 0;
                blinkyLastLockDirection2 = blinkyLastLockDirection;
                blinkyLastLockDirection = "lockdown";
                return "down";
            }
            blinkyCounter = blinkyCounter + 1;


            if(blinkyX < blinkyTargetX && blockCollideTestX(true, "blinky")) {
                ghostMove("blinky", "right");
                blinkyCounter = 0;
                return "right";
            } else if(blinkyX > blinkyTargetX && blockCollideTestX(false, "blinky")){
                ghostMove("blinky", "left");
                blinkyCounter = 0;
                return "left";

            } else if(blockCollideTestY(true, "blinky")) {
                ghostMove("blinky", "down");
                return "lockdown";
            }

        }

        //Lockup
        if(lastDirection.equals("lockup") && blinkyCounter < lockNumber){

            if(blinkyCounter == lockNumber-1){
                blinkyCounter = 0;
                blinkyLastLockDirection2 = blinkyLastLockDirection;
                blinkyLastLockDirection = "lockup";
                return "up";
            }
            blinkyCounter = blinkyCounter + 1;

            if(blinkyX < blinkyTargetX && blockCollideTestX(true, "blinky")) {
                ghostMove("blinky", "right");
                blinkyCounter = 0;
                return "right";
            } else if(blinkyX > blinkyTargetX && blockCollideTestX(false, "blinky")){
                ghostMove("blinky", "left");
                blinkyCounter = 0;
                return "left";

            } else if(blockCollideTestY(false, "blinky")){
                ghostMove("blinky", "up");
                return "lockup";
            }

        }

        //Lockright
        if(lastDirection.equals("lockright") && blinkyCounter < lockNumber){

            if(blinkyCounter == lockNumber-1){
                blinkyCounter = 0;
                blinkyLastLockDirection2 = blinkyLastLockDirection;
                blinkyLastLockDirection = "lockright";
                return "right";
            }
            blinkyCounter = blinkyCounter + 1;

            if(blinkyY < blinkyTargetY && blockCollideTestY(true, "blinky")) {
                ghostMove("blinky", "down");
                blinkyCounter = 0;
                return "down";
            } else if(blinkyY > blinkyTargetY && blockCollideTestY(false, "blinky")){
                ghostMove("blinky", "up");
                blinkyCounter = 0;
                return "up";

            } else if(blockCollideTestX(true, "blinky")){
                ghostMove("blinky", "right");
                return "lockright";
            }

        }

        //Lockleft
        if(lastDirection.equals("lockleft") && blinkyCounter < lockNumber){

            if(blinkyCounter == lockNumber-1){
                blinkyCounter = 0;
                blinkyLastLockDirection2 = blinkyLastLockDirection;
                blinkyLastLockDirection = "lockleft";
                return "left";
            }
            blinkyCounter = blinkyCounter + 1;

            if(blinkyY < blinkyTargetY && blockCollideTestY(true, "blinky")) {
                ghostMove("blinky", "down");
                blinkyCounter = 0;
                return "down";
            } else if(blinkyY > blinkyTargetY && blockCollideTestY(false, "blinky")){
                ghostMove("blinky", "up");
                blinkyCounter = 0;
                return "up";

            } else if(blockCollideTestX(false, "blinky")) {
                ghostMove("blinky", "left");
                return "lockleft";
            }

        }

        //Down
        if(yDifference > xDifference && blinkyY < blinkyTargetY){

            if(blockCollideTestY(true, "blinky")) {
                ghostMove("blinky", "down");
                return "down";
            }else if(blinkyX < blinkyTargetX) {

                if (blockCollideTestX(true, "blinky")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockleft";
                    else return "lockup";
                }
            }else if(blinkyX > blinkyTargetX) {

                if (blockCollideTestX(false, "blinky")) {

                    return "lockleft";
                }else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockup";
                }
            }else{
                if(randomDirection == 0) return "lockright";
                else return "lockleft";
            }
        }
        //Up
        else if(yDifference > xDifference && blinkyY > blinkyTargetY){

            if(blockCollideTestY(false, "blinky")){
                ghostMove("blinky", "up");
                return "up";
            }else if(blinkyX < blinkyTargetX) {

                if (blockCollideTestX(true, "blinky")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockdown";
                    else return "lockleft";
                }
            }else if(blinkyX > blinkyTargetX) {

                if (blockCollideTestX(false, "blinky")) {

                    return "lockleft";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockdown";
                }
            }else{
                if(randomDirection == 0) return "lockright";
                else return "lockleft";
            }

        }
        //Right
        else if(xDifference > yDifference && blinkyX < blinkyTargetX){

            if(blockCollideTestX(true, "blinky")){
                ghostMove("blinky", "right");
                return "right";
            }else if(blinkyY < blinkyTargetY) {

                if (blockCollideTestY(true, "blinky")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockup";
                    else return "lockleft";
                }
            }else if(blinkyY > blinkyTargetY) {

                if (blockCollideTestY(false, "blinky")) {

                    return "lockup";
                } else {
                    if(randomDirection == 0) return "lockdown";
                    else return "lockleft";
                }
            }else{
                if(randomDirection == 0) return "lockdown";
                else return "lockup";
            }
        }
        //Left
        else if(xDifference > yDifference && blinkyX > blinkyTargetX){

            if(blockCollideTestX(false, "blinky")) {
                ghostMove("blinky", "left");
                return "left";
            }else if(blinkyY < blinkyTargetY) {

                if (blockCollideTestY(true, "blinky")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockup";
                }
            }else if(blinkyY > blinkyTargetY) {

                if (blockCollideTestY(false, "blinky")) {

                    return "lockup";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockdown";
                }
            }else{
                if(randomDirection == 0) return "lockdown";
                else return "lockup";
            }
        }
        //Down
         else if(blinkyY < blinkyTargetY){

            if(blockCollideTestY(true, "blinky")) {
                ghostMove("blinky", "down");
                return "down";
            }
        }
        //Up
        else if(blinkyY > blinkyTargetY){

            if(blockCollideTestY(false, "blinky")){
                ghostMove("blinky", "up");
                return "up";
            }else if(blinkyX < blinkyTargetX) {

                if (blockCollideTestX(true, "blinky")) {
                    ghostMove("blinky", "right");
                    return "right";
                }
            }else if(blinkyX > blinkyTargetX) {

                if (blockCollideTestX(false, "blinky")) {
                    ghostMove("blinky", "left");
                    return "left";
                }
            }

        }
        //Right
        else if(blinkyX < blinkyTargetX){

            if(blockCollideTestX(true, "blinky")){
                ghostMove("blinky", "right");
                return "right";
            }else if(blinkyY < blinkyTargetY) {

                if (blockCollideTestY(true, "blinky")) {
                    ghostMove("blinky", "down");
                    return "down";
                }
            }else if(blinkyY > blinkyTargetY) {

                if (blockCollideTestY(false, "blinky")) {
                    ghostMove("blinky", "up");
                    return "up";
                }
            }
        }
        //Left
        else if(blinkyX > blinkyTargetX){

            if(blockCollideTestX(false, "blinky")) {
                ghostMove("blinky", "left");
                return "left";
            }else if(blinkyY < blinkyTargetY) {

                if (blockCollideTestY(true, "blinky")) {
                    ghostMove("blinky", "down");
                    return "down";
                }
            }else if(blinkyY > blinkyTargetY) {

                if (blockCollideTestY(false, "blinky")) {
                    ghostMove("blinky", "up");
                    return "up";
                }
            }
        }

        return "left";

    }

    //Generic Move Method
    public void ghostMove(String ghost, String direction){

        if(ghost.equals("blinky")){
            if(direction.equals("down")){
                blinkyY = blinkyY + blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) redspr.setImage(blinkydown1);
                    else redspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) redspr.setImage(blinkydown2);
                    else redspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("up")){
                blinkyY = blinkyY - blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) redspr.setImage(blinkyup1);
                    else redspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) redspr.setImage(blinkyup2);
                    else redspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("right")){
                blinkyX = blinkyX + blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) redspr.setImage(blinkyright1);
                    else redspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) redspr.setImage(blinkyright2);
                    else redspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("left")){
                blinkyX = blinkyX - blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) redspr.setImage(blinkyleft1);
                    else redspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) redspr.setImage(blinkyleft2);
                    else redspr.setImage(scaredghost2);
                }
            }
        }


    }

    public void gameReset(){
        //Reset level
        levelReset();

        //Reset game
        score = 0;
        animation = 0;
        level = 1;
        levelComplete = false;
        gameOver = false;
        powerPelletStartTime = 800;

        //Undo game over
        backgroundspr.setLayer(0);
        namespr.setVisible(false);
        inputspr.setVisible(false);
        gameoverspr.setVisible(false);
        pacmanspr.setVisible(true);


    }

    public void levelReset(){

        intro = introLength;
        levelComplete = false;
        powerPelletStartTime = Math.max(20, powerPelletStartTime - (level * 20));
        //Pacman vars
        x = 940;
        y = 765;
        speed = 3;
        width = 45;
        height = 45;
        pacmanspr.setImage(pacmanright1);

        //Animation
        animation = 0;
        ghostAnimationType = false;
        pacmanAnimationType = 0;

        //Blinky (red)
        blinkyX = 950;
        blinkyY = 525;
        blinkySpeed = 2;
        blinkyLastDirection = "left";
        blinkyLastLockDirection = "lockleft";
        blinkyLastLockDirection2 = "lockup";
        blinkyTargetX = x;
        blinkyTargetY = y;
        blinkyTarget = "pacman";
        blinkyCounter = 0;
        blinkyWidth = 45;
        blinkyHeight = 45;
        blinkyCoolDown = 400;
        blinkyScared = false;
        redspr.setImage(blinkyup1);
        blinkyEatenPause = 20;

        //Pellets
        for(int i = 0; i < pelletNumber; i++){
            if(!pelletList.get(i).isVisible()) {
                pelletList.get(i).setVisible(true);
            }
        }

        //Powerpellets
        for(int i = 0; i < 4; i++) {
            if (!powerPelletList.get(i).isVisible()) {
                powerPelletList.get(i).setVisible(true);
            }
        }

    }

    //Returns true if pacman/ghost can't move without colliding in the desired x direction
    public boolean blockCollideTestX(boolean direction, String name){

        Rectangle XT = new Rectangle();
        Rectangle XF = new Rectangle();

        if(name.equals("pacman")) {
            XT = new Rectangle(x + speed, y, width, height);
            XF = new Rectangle(x - speed, y, width, height);
        }

        if(name.equals("blinky")) {
            XT = new Rectangle(blinkyX + blinkySpeed, blinkyY, blinkyWidth, blinkyHeight);
            XF = new Rectangle(blinkyX - blinkySpeed, blinkyY, blinkyWidth, blinkyHeight);
        }

        //True direction is up and false is down
        return Collision(direction, XT, XF);
    }

    //Returns true if pacman/ghost can't move without colliding in the desired y direction
    public boolean blockCollideTestY(boolean direction, String name){

        Rectangle YT = new Rectangle();
        Rectangle YF = new Rectangle();

        if(name.equals("pacman")) {
            YT = new Rectangle(x, y + speed, width, height);
            YF = new Rectangle(x, y - speed, width, height);
        }

        if(name.equals("blinky")) {
            YT = new Rectangle(blinkyX, blinkyY + blinkySpeed, blinkyWidth, blinkyHeight);
            YF = new Rectangle(blinkyX, blinkyY - blinkySpeed, blinkyWidth, blinkyHeight);
        }

        //True direction is up and false is down
        return Collision(direction, YT, YF);
    }

    private boolean Collision(boolean direction, Rectangle T, Rectangle F) {
        if(direction){
            return Boundary(T);

        }else {

            return Boundary(F);
        }
    }

    private boolean Boundary(Rectangle pacOrGhost) {

        for (Rectangle rectangle : boundaryList) {

            if (pacOrGhost.intersects(rectangle)) {

                return false;

            }
        }

        return true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {


        //Coordinates for testing
        int x=e.getX();
        int y=e.getY();
        System.out.println(x+","+y);//these co-ords are relative to the component
    }




}

