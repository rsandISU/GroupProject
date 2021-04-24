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
    Sprite blinkyspr;
    Sprite pinkyspr;
    Sprite inkyspr;
    Sprite clydespr;
    Sprite doorspr;
    SpriteText scorespr;
    SpriteText gameoverspr;
    SpriteText namespr;
    SpriteText inputspr;
    SpriteText readyspr;
    SpriteText quitspr;
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
    private int powerPelletStartTime = 800 * 4;
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
    private int blinkyX = 962;
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
    private int blinkyCoolDown = 100;
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

    //region Pinky (pink)
    private int pinkyX = 1005;
    private int pinkyY = 525;
    private int pinkyLastX = 0;
    private int pinkyLastY = 0;
    private int pinkySpeed = 2;
    private String pinkyLastDirection = "left";
    private String pinkyLastLockDirection = "lockleft";
    String pinkyLastLockDirection2 = "lockup";
    private final Random pinkyRand = new Random(123);
    private int pinkyTargetX = x;
    private int pinkyTargetY = y;
    private String pinkyTarget = "pacman";
    private int pinkyCounter = 0;
    private int pinkyWidth = 45;
    private int pinkyHeight = 45;
    Rectangle pinkyR = new Rectangle(pinkyX, pinkyY, pinkyWidth, pinkyHeight);
    private int pinkyCoolDown = 200;
    private boolean pinkyScared = false;
    private int pinkyEatenPause = 20;
    BufferedImage pinkyright1 = ResourceLoader.getImage("pacman/pinkyright1.png");
    BufferedImage pinkyright2 = ResourceLoader.getImage("pacman/pinkyright2.png");
    BufferedImage pinkyleft1 = ResourceLoader.getImage("pacman/pinkyleft1.png");
    BufferedImage pinkyleft2 = ResourceLoader.getImage("pacman/pinkyleft2.png");
    BufferedImage pinkydown1 = ResourceLoader.getImage("pacman/pinkydown1.png");
    BufferedImage pinkydown2 = ResourceLoader.getImage("pacman/pinkydown2.png");
    BufferedImage pinkyup1 = ResourceLoader.getImage("pacman/pinkyup1.png");
    BufferedImage pinkyup2 = ResourceLoader.getImage("pacman/pinkyup2.png");
    //endregion

    //region Inky (blue)
    private int inkyX = 919;
    private int inkyY = 525;
    private int inkyLastX = 0;
    private int inkyLastY = 0;
    private int inkySpeed = 2;
    private String inkyLastDirection = "left";
    private String inkyLastLockDirection = "lockleft";
    String inkyLastLockDirection2 = "lockup";
    private final Random inkyRand = new Random(123);
    private int inkyTargetX = x;
    private int inkyTargetY = y;
    private String inkyTarget = "pacman";
    private int inkyCounter = 0;
    private int inkyWidth = 45;
    private int inkyHeight = 45;
    Rectangle inkyR = new Rectangle(inkyX, inkyY, inkyWidth, inkyHeight);
    private int inkyCoolDown = 300;
    private boolean inkyScared = false;
    private int inkyEatenPause = 20;
    BufferedImage inkyright1 = ResourceLoader.getImage("pacman/inkyright1.png");
    BufferedImage inkyright2 = ResourceLoader.getImage("pacman/inkyright2.png");
    BufferedImage inkyleft1 = ResourceLoader.getImage("pacman/inkyleft1.png");
    BufferedImage inkyleft2 = ResourceLoader.getImage("pacman/inkyleft2.png");
    BufferedImage inkydown1 = ResourceLoader.getImage("pacman/inkydown1.png");
    BufferedImage inkydown2 = ResourceLoader.getImage("pacman/inkydown2.png");
    BufferedImage inkyup1 = ResourceLoader.getImage("pacman/inkyup1.png");
    BufferedImage inkyup2 = ResourceLoader.getImage("pacman/inkyup2.png");
    //endregion

    //region Clyde (orange)
    private int clydeX = 876;
    private int clydeY = 525;
    private int clydeLastX = 0;
    private int clydeLastY = 0;
    private int clydeSpeed = 2;
    private String clydeLastDirection = "left";
    private String clydeLastLockDirection = "lockleft";
    String clydeLastLockDirection2 = "lockup";
    private final Random clydeRand = new Random(123);
    private int clydeTargetX = x;
    private int clydeTargetY = y;
    private String clydeTarget = "pacman";
    private int clydeCounter = 0;
    private int clydeWidth = 45;
    private int clydeHeight = 45;
    Rectangle clydeR = new Rectangle(clydeX, clydeY, clydeWidth, clydeHeight);
    private int clydeCoolDown = 400;
    private boolean clydeScared = false;
    private int clydeEatenPause = 20;
    BufferedImage clyderight1 = ResourceLoader.getImage("pacman/clyderight1.png");
    BufferedImage clyderight2 = ResourceLoader.getImage("pacman/clyderight2.png");
    BufferedImage clydeleft1 = ResourceLoader.getImage("pacman/clydeleft1.png");
    BufferedImage clydeleft2 = ResourceLoader.getImage("pacman/clydeleft2.png");
    BufferedImage clydedown1 = ResourceLoader.getImage("pacman/clydedown1.png");
    BufferedImage clydedown2 = ResourceLoader.getImage("pacman/clydedown2.png");
    BufferedImage clydeup1 = ResourceLoader.getImage("pacman/clydeup1.png");
    BufferedImage clydeup2 = ResourceLoader.getImage("pacman/clydeup2.png");
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

        //Quit
        quitspr = new SpriteText(780, 40, 200, 200, 2);
        quitspr.setText("Press Q to quit", Color.lightGray, 2);

        //Ready
        readyspr = new SpriteText(894, 604, 2);
        readyspr.setText("Ready!", Color.yellow, 2.4);

        //Pacman
        pacmanspr = new Sprite(pacmanright1, x, y, width, height, 2);

        //Blinky (red)
        blinkyspr = new Sprite(blinkyup1, blinkyX, blinkyY, blinkyWidth, blinkyHeight, 3);

        //Plinky (pink)
        pinkyspr = new Sprite(pinkyup1, pinkyX, pinkyY, pinkyWidth, pinkyHeight, 3);

        //Inky (blue)
        inkyspr = new Sprite(inkyup1, inkyX, inkyY, inkyWidth, inkyHeight, 3);

        //Clyde (orange)
        clydespr = new Sprite(clydeup1, clydeX, clydeY, clydeWidth, clydeHeight, 3);
        
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
        boundaryList.add(new Rectangle(628, 196, 78, 50));
        //Block2
        boundaryList.add(new Rectangle(772, 196, 114, 50));
        //Block3
        boundaryList.add(new Rectangle(1040, 196, 114, 50));
        //Block4
        boundaryList.add(new Rectangle(1218, 196, 78, 50));
        //Block5
        boundaryList.add(new Rectangle(628, 308, 78, 28));
        //Block6
        boundaryList.add(new Rectangle(1216, 310, 78, 28));
        //Block7
        boundaryList.add(new Rectangle(772, 563, 28, 107));
        //Block8
        boundaryList.add(new Rectangle(1126, 563, 28, 107));
        //Block9
        boundaryList.add(new Rectangle(772, 732, 114, 26));
        //Block10
        boundaryList.add(new Rectangle(1040, 732, 114, 26));
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
        boundaryList.add(new Rectangle(860, 640, 204, 26));
        //Block19
        boundaryList.add(new Rectangle(948, 670, 28, 90));
        //Block20
        boundaryList.add(new Rectangle(628, 900, 257, 28));
        //Block21
        boundaryList.add(new Rectangle(772, 820, 28, 80));
        //Block22
        boundaryList.add(new Rectangle(860, 818, 204, 28));
        //Block23
        boundaryList.add(new Rectangle(948, 846, 28, 82));
        //Block24
        boundaryList.add(new Rectangle(1040, 900, 256, 28));
        //Block25
        boundaryList.add(new Rectangle(1128, 818, 28, 80));
        //Block26
        boundaryList.add(new Rectangle(680, 732, 28, 114));
        //Block27
        boundaryList.add(new Rectangle(628, 732, 52, 26));
        //Block28
        boundaryList.add(new Rectangle(1216, 732, 28, 114));
        //Block29
        boundaryList.add(new Rectangle(1244, 732, 52, 26));
        //Block30
        boundaryList.add(new Rectangle(948, 138, 28, 108));
        //Block31
        boundaryList.add(new Rectangle(562, 818, 56, 28));
        //Block32
        boundaryList.add(new Rectangle(1304, 818, 56, 28));
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
        boundaryList.add(new Rectangle(1216, 563, 156, 107));
        //Block39
        boundaryList.add(new Rectangle(1360, 670, 12, 326));
        //Block40
        boundaryList.add(new Rectangle(562, 984, 798, 12));
        //Block41
        boundaryList.add(new Rectangle(550, 670, 12, 326));
        //Block42
        boundaryList.add(new Rectangle(550, 563, 158, 107));
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
        c.put(readyspr, "start");
        c.put(quitspr, "quit");
        c.put(doorspr, "door");
        c.put(pacmanspr, "pacman");
        c.put(blinkyspr, "blinky");
        c.put(pinkyspr, "pinky");
        c.put(inkyspr, "inky");
        c.put(clydespr, "clyde");
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
            readyspr.setVisible(true);
            game = false;
            if(!introMusic.clipPlaying()){
                introMusic.play();
            }
        }else if(!gameOver){
            game = true;
            introMusic.haltClip();
            readyspr.setVisible(false);
        }
        //endregion

        //region Quitting

        if(c.getKeysDown().contains('q')){

            gameReset();

            c.setElement("MENU");

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

        //region blinky movement
        blinkyR = new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight);
        if(game) {
            if (blinkyCoolDown > 0) {
                blinkyCoolDown = blinkyCoolDown - 1;
            }
            if (blinkyCoolDown == 0) {
                if (blinkyX > 942){
                    blinkyX = blinkyX - 1;
                    if(ghostAnimationType) blinkyspr.setImage(blinkyleft1);
                    else blinkyspr.setImage(blinkyleft2);
                }
                else if (blinkyX < 942){
                    blinkyX = blinkyX + 1;
                    if(ghostAnimationType) blinkyspr.setImage(blinkyright1);
                    else blinkyspr.setImage(blinkyright2);
                }
                else if (blinkyY > 424){
                    blinkyY = blinkyY - 1;
                    if(ghostAnimationType) blinkyspr.setImage(blinkyup1);
                    else blinkyspr.setImage(blinkyup2);
                }
                else if (blinkyY < 424){
                    blinkyY = blinkyY + 1;
                    if(ghostAnimationType) blinkyspr.setImage(blinkydown1);
                    else blinkyspr.setImage(blinkydown2);
                    }
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

        //region pinky movement
        pinkyR = new Rectangle(pinkyX, pinkyY, pinkyWidth, pinkyHeight);
        if(game) {
            if (pinkyCoolDown > 0) {
                pinkyCoolDown = pinkyCoolDown - 1;
            }
            if (pinkyCoolDown == 0) {
                if (pinkyX > 942){
                    pinkyX = pinkyX - 1;
                    if(ghostAnimationType) pinkyspr.setImage(pinkyleft1);
                    else pinkyspr.setImage(pinkyleft2);
                }
                else if (pinkyX < 942){
                    pinkyX = pinkyX + 1;
                    if(ghostAnimationType) pinkyspr.setImage(pinkyright1);
                    else pinkyspr.setImage(pinkyright2);
                }
                else if (pinkyY > 424){
                    pinkyY = pinkyY - 1;
                    if(ghostAnimationType) pinkyspr.setImage(pinkyup1);
                    else pinkyspr.setImage(pinkyup2);
                }
                else if (pinkyY < 424){
                    pinkyY = pinkyY + 1;
                    if(ghostAnimationType) pinkyspr.setImage(pinkydown1);
                    else pinkyspr.setImage(pinkydown2);
                }
                else pinkyCoolDown = -1;
            }
            if (pinkyCoolDown == -1) {
                pinkyLastX = pinkyX;
                pinkyLastY = pinkyY;
                //This is the actual movement line
                pinkyLastDirection = pinkyMove(pinkyLastDirection);
                if(!ghostSiren.clipPlaying()) ghostSiren.play();
                if (pinkyLastX == pinkyX && pinkyLastY == pinkyY) {
                    pinkyTarget = "pacman";
                }
            }
            if (pinkyScared) {
                pinkyTarget = "away";
            }
        }
        //endregion

        //region inky movement
        inkyR = new Rectangle(inkyX, inkyY, inkyWidth, inkyHeight);
        if(game) {
            if (inkyCoolDown > 0) {
                inkyCoolDown = inkyCoolDown - 1;
            }
            if (inkyCoolDown == 0) {
                if (inkyX > 942){
                    inkyX = inkyX - 1;
                    if(ghostAnimationType) inkyspr.setImage(inkyleft1);
                    else inkyspr.setImage(inkyleft2);
                }
                else if (inkyX < 942){
                    inkyX = inkyX + 1;
                    if(ghostAnimationType) inkyspr.setImage(inkyright1);
                    else inkyspr.setImage(inkyright2);
                }
                else if (inkyY > 424){
                    inkyY = inkyY - 1;
                    if(ghostAnimationType) inkyspr.setImage(inkyup1);
                    else inkyspr.setImage(inkyup2);
                }
                else if (inkyY < 424){
                    inkyY = inkyY + 1;
                    if(ghostAnimationType) inkyspr.setImage(inkydown1);
                    else inkyspr.setImage(inkydown2);
                }
                else inkyCoolDown = -1;
            }
            if (inkyCoolDown == -1) {
                inkyLastX = inkyX;
                inkyLastY = inkyY;
                //This is the actual movement line
                inkyLastDirection = inkyMove(inkyLastDirection);
                if(!ghostSiren.clipPlaying()) ghostSiren.play();
                if (inkyLastX == inkyX && inkyLastY == inkyY) {
                    inkyTarget = "pacman";
                }
            }
            if (inkyScared) {
                inkyTarget = "away";
            }
        }
        //endregion

        //region clyde movement
        clydeR = new Rectangle(clydeX, clydeY, clydeWidth, clydeHeight);
        if(game) {
            if (clydeCoolDown > 0) {
                clydeCoolDown = clydeCoolDown - 1;
            }
            if (clydeCoolDown == 0) {
                if (clydeX > 942){
                    clydeX = clydeX - 1;
                    if(ghostAnimationType) clydespr.setImage(clydeleft1);
                    else clydespr.setImage(clydeleft2);
                }
                else if (clydeX < 942){
                    clydeX = clydeX + 1;
                    if(ghostAnimationType) clydespr.setImage(clyderight1);
                    else clydespr.setImage(clyderight2);
                }
                else if (clydeY > 424){
                    clydeY = clydeY - 1;
                    if(ghostAnimationType) clydespr.setImage(clydeup1);
                    else clydespr.setImage(clydeup2);
                }
                else if (clydeY < 424){
                    clydeY = clydeY + 1;
                    if(ghostAnimationType) clydespr.setImage(clydedown1);
                    else clydespr.setImage(clydedown2);
                }
                else clydeCoolDown = -1;
            }
            if (clydeCoolDown == -1) {
                clydeLastX = clydeX;
                clydeLastY = clydeY;
                //This is the actual movement line
                clydeLastDirection = clydeMove(clydeLastDirection);
                if(!ghostSiren.clipPlaying()) ghostSiren.play();
                if (clydeLastX == clydeX && clydeLastY == clydeY) {
                    clydeTarget = "pacman";
                }
            }
            if (clydeScared) {
                clydeTarget = "away";
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
            //Blinky
            if(Math.abs(pinkyX - x) < 300 && Math.abs(pinkyY - y) < 200 && pinkyTarget.equals("pacman")){
                //pinky
                pinkyTarget = "leftwrap";
                pinkyTargetX = 538;
                pinkyTargetY = 512;
            }
            //inky
            if(Math.abs(inkyX - x) < 300 && Math.abs(inkyY - y) < 200 && inkyTarget.equals("pacman")){
                //inky
                inkyTarget = "leftwrap";
                inkyTargetX = 538;
                inkyTargetY = 512;
            }
            //clyde
            if(Math.abs(clydeX - x) < 300 && Math.abs(clydeY - y) < 200 && clydeTarget.equals("pacman")){
                //clyde
                clydeTarget = "leftwrap";
                clydeTargetX = 538;
                clydeTargetY = 512;
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
            //pinky
            if(Math.abs(pinkyX - x) < 300 && Math.abs(pinkyY - y) < 200 && pinkyTarget.equals("pacman")){
                //pinky
                pinkyTarget = "rightwrap";
                pinkyTargetX = 1342;
                pinkyTargetY = 512;
            }
            //inky
            if(Math.abs(inkyX - x) < 300 && Math.abs(inkyY - y) < 200 && inkyTarget.equals("pacman")){
                //inky
                inkyTarget = "rightwrap";
                inkyTargetX = 1342;
                inkyTargetY = 512;
            }
            //clyde
            if(Math.abs(clydeX - x) < 300 && Math.abs(clydeY - y) < 200 && clydeTarget.equals("pacman")){
                //clyde
                clydeTarget = "rightwrap";
                clydeTargetX = 1342;
                clydeTargetY = 512;
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
        //pinky
        //Left to right
        if(pinkyX < 540){
            pinkyX = 1340;
            if(pinkyTarget.equals("leftwrap")) pinkyTarget = "pacman";

        }
        //Right to left
        if(pinkyX > 1340) {
            pinkyX = 540;
            if(pinkyTarget.equals("rightwrap")) pinkyTarget = "pacman";
        }
        //inky
        //Left to right
        if(inkyX < 540){
            inkyX = 1340;
            if(inkyTarget.equals("leftwrap")) inkyTarget = "pacman";

        }
        //Right to left
        if(inkyX > 1340) {
            inkyX = 540;
            if(inkyTarget.equals("rightwrap")) inkyTarget = "pacman";
        }
        //clyde
        //Left to right
        if(clydeX < 540){
            clydeX = 1340;
            if(clydeTarget.equals("leftwrap")) clydeTarget = "pacman";

        }
        //Right to left
        if(clydeX > 1340) {
            clydeX = 540;
            if(clydeTarget.equals("rightwrap")) clydeTarget = "pacman";
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
                //pinky
                if(pinkyCoolDown == -1){
                    pinkyScared = true;
                }
                //inky
                if(inkyCoolDown == -1){
                    inkyScared = true;
                }
                //clyde
                if(clydeCoolDown == -1){
                    clydeScared = true;
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
        //pinky
        if(powerPelletCountDown){
            powerPelletTime = powerPelletTime - 1;
            if(powerPelletTime < 0){
                pinkyScared = false;

            }

        }
        //inky
        if(powerPelletCountDown){
            powerPelletTime = powerPelletTime - 1;
            if(powerPelletTime < 0){
                inkyScared = false;

            }

        }
        //clyde
        if(powerPelletCountDown){
            powerPelletTime = powerPelletTime - 1;
            if(powerPelletTime < 0){
                clydeScared = false;

            }

        }
//endregion

        //region Eating Ghosts
        //Blinky
        if(pacmanR.intersects(new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight)) && blinkyScared && blinkyspr.getImage() != eatenghost){

            score = score + 200;
            ghostEat.play();
            blinkyEatenPause = 20;
            blinkyspr.setImage(eatenghost);

        }
        if(blinkyspr.getImage() == eatenghost){
            blinkyCoolDown = 400;
            if(blinkyEatenPause < 0){
                blinkyScared = false;
                blinkyTarget = "pacman";
                blinkyspr.setImage(blinkyup1);
                blinkyX = 962;
                blinkyY = 525;
            }
            blinkyEatenPause = blinkyEatenPause - 1;
        }
        //pinky
        if(pacmanR.intersects(new Rectangle(pinkyX, pinkyY, pinkyWidth, pinkyHeight)) && pinkyScared && pinkyspr.getImage() != eatenghost){

            score = score + 200;
            ghostEat.play();
            pinkyEatenPause = 20;
            pinkyspr.setImage(eatenghost);

        }
        if(pinkyspr.getImage() == eatenghost){
            pinkyCoolDown = 400;
            if(pinkyEatenPause < 0){
                pinkyScared = false;
                pinkyTarget = "pacman";
                pinkyspr.setImage(pinkyup1);
                pinkyX = 1005;
                pinkyY = 525;
            }
            pinkyEatenPause = pinkyEatenPause - 1;
        }
        //inky
        if(pacmanR.intersects(new Rectangle(inkyX, inkyY, inkyWidth, inkyHeight)) && inkyScared && inkyspr.getImage() != eatenghost){

            score = score + 200;
            ghostEat.play();
            inkyEatenPause = 20;
            inkyspr.setImage(eatenghost);

        }
        if(inkyspr.getImage() == eatenghost){
            inkyCoolDown = 400;
            if(inkyEatenPause < 0){
                inkyScared = false;
                inkyTarget = "pacman";
                inkyspr.setImage(inkyup1);
                inkyX = 919;
                inkyY = 525;
            }
            inkyEatenPause = inkyEatenPause - 1;
        }
        //clyde
        if(pacmanR.intersects(new Rectangle(clydeX, clydeY, clydeWidth, clydeHeight)) && clydeScared && clydespr.getImage() != eatenghost){

            score = score + 200;
            ghostEat.play();
            clydeEatenPause = 20;
            clydespr.setImage(eatenghost);

        }
        if(clydespr.getImage() == eatenghost){
            clydeCoolDown = 400;
            if(clydeEatenPause < 0){
                clydeScared = false;
                clydeTarget = "pacman";
                clydespr.setImage(clydeup1);
                clydeX = 876;
                clydeY = 525;
            }
            clydeEatenPause = clydeEatenPause - 1;
        }
        //endregion

        //region Pacman coordinates update
        pacmanspr.setX(x);
        pacmanspr.setY(y);
        //endregion

        //region Blinky coordinates update
        blinkyspr.setX(blinkyX);
        blinkyspr.setY(blinkyY);
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

        //region pinky coordinates update
        pinkyspr.setX(pinkyX);
        pinkyspr.setY(pinkyY);
        if(pinkyTarget.equals("pacman")) pinkyTargetX = x;
        if(pinkyTarget.equals("pacman")) pinkyTargetY = y;
        if(pinkyTarget.equals("leftwrap")){
            pinkyTargetX = 538;
            pinkyTargetY = 512;
        }
        if(pinkyTarget.equals("rightwrap")){
            pinkyTargetX = 1342;
            pinkyTargetY = 512;
        }
        if(pinkyTarget.equals("away") && x > pinkyX) pinkyTargetX = x - 1000;
        if(pinkyTarget.equals("away") && x < pinkyX) pinkyTargetX = x + 1000;
        if(pinkyTarget.equals("away") && y < pinkyY) pinkyTargetY = y - 1000;
        if(pinkyTarget.equals("away") && y > pinkyX) pinkyTargetY = y + 1000;
        //endregion

        //region inky coordinates update
        inkyspr.setX(inkyX);
        inkyspr.setY(inkyY);
        if(inkyTarget.equals("pacman")) inkyTargetX = x;
        if(inkyTarget.equals("pacman")) inkyTargetY = y;
        if(inkyTarget.equals("leftwrap")){
            inkyTargetX = 538;
            inkyTargetY = 512;
        }
        if(inkyTarget.equals("rightwrap")){
            inkyTargetX = 1342;
            inkyTargetY = 512;
        }
        if(inkyTarget.equals("away") && x > inkyX) inkyTargetX = x - 1000;
        if(inkyTarget.equals("away") && x < inkyX) inkyTargetX = x + 1000;
        if(inkyTarget.equals("away") && y < inkyY) inkyTargetY = y - 1000;
        if(inkyTarget.equals("away") && y > inkyX) inkyTargetY = y + 1000;
        //endregion

        //region clyde coordinates update
        clydespr.setX(clydeX);
        clydespr.setY(clydeY);
        if(clydeTarget.equals("pacman")) clydeTargetX = x;
        if(clydeTarget.equals("pacman")) clydeTargetY = y;
        if(clydeTarget.equals("leftwrap")){
            clydeTargetX = 538;
            clydeTargetY = 512;
        }
        if(clydeTarget.equals("rightwrap")){
            clydeTargetX = 1342;
            clydeTargetY = 512;
        }
        if(clydeTarget.equals("away") && x > clydeX) clydeTargetX = x - 1000;
        if(clydeTarget.equals("away") && x < clydeX) clydeTargetX = x + 1000;
        if(clydeTarget.equals("away") && y < clydeY) clydeTargetY = y - 1000;
        if(clydeTarget.equals("away") && y > clydeX) clydeTargetY = y + 1000;
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
        if((pacmanR.intersects(new Rectangle(blinkyX, blinkyY, blinkyWidth, blinkyHeight)) && !blinkyScared)
                || (pacmanR.intersects(new Rectangle(pinkyX, pinkyY, pinkyWidth, pinkyHeight)) && !pinkyScared)
                || (pacmanR.intersects(new Rectangle(inkyX, inkyY, inkyWidth, inkyHeight)) && !inkyScared)
                || (pacmanR.intersects(new Rectangle(clydeX, clydeY, clydeWidth, clydeHeight)) && !clydeScared)){

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

    //pinky Direction deciding method
    public String pinkyMove(String lastDirection){

        int xDifference = Math.abs(pinkyTargetX - pinkyX);
        int yDifference = Math.abs(pinkyTargetY - pinkyY);
        int lockNumber = 160;
        int randomDirection = pinkyRand.nextInt(2);
        int randomDirection4 = pinkyRand.nextInt(4);
        int choose = pinkyRand.nextInt(40);

        if(choose == 1 && !lastDirection.equals("lockdown")&& !lastDirection.equals("lockup")&& !lastDirection.equals("lockleft")&& !lastDirection.equals("lockright")) {
            //Random AI
            if (randomDirection4 == 0) return "lockup";
            if (randomDirection4 == 1) return "lockdown";
            if (randomDirection4 == 2) return "lockright";
            return "lockleft";
        }

        //Lockdown
        if(lastDirection.equals("lockdown") && pinkyCounter < lockNumber){

            if(pinkyCounter == lockNumber-1){
                pinkyCounter = 0;
                pinkyLastLockDirection2 = pinkyLastLockDirection;
                pinkyLastLockDirection = "lockdown";
                return "down";
            }
            pinkyCounter = pinkyCounter + 1;


            if(pinkyX < pinkyTargetX && blockCollideTestX(true, "pinky")) {
                ghostMove("pinky", "right");
                pinkyCounter = 0;
                return "right";
            } else if(pinkyX > pinkyTargetX && blockCollideTestX(false, "pinky")){
                ghostMove("pinky", "left");
                pinkyCounter = 0;
                return "left";

            } else if(blockCollideTestY(true, "pinky")) {
                ghostMove("pinky", "down");
                return "lockdown";
            }

        }

        //Lockup
        if(lastDirection.equals("lockup") && pinkyCounter < lockNumber){

            if(pinkyCounter == lockNumber-1){
                pinkyCounter = 0;
                pinkyLastLockDirection2 = pinkyLastLockDirection;
                pinkyLastLockDirection = "lockup";
                return "up";
            }
            pinkyCounter = pinkyCounter + 1;

            if(pinkyX < pinkyTargetX && blockCollideTestX(true, "pinky")) {
                ghostMove("pinky", "right");
                pinkyCounter = 0;
                return "right";
            } else if(pinkyX > pinkyTargetX && blockCollideTestX(false, "pinky")){
                ghostMove("pinky", "left");
                pinkyCounter = 0;
                return "left";

            } else if(blockCollideTestY(false, "pinky")){
                ghostMove("pinky", "up");
                return "lockup";
            }

        }

        //Lockright
        if(lastDirection.equals("lockright") && pinkyCounter < lockNumber){

            if(pinkyCounter == lockNumber-1){
                pinkyCounter = 0;
                pinkyLastLockDirection2 = pinkyLastLockDirection;
                pinkyLastLockDirection = "lockright";
                return "right";
            }
            pinkyCounter = pinkyCounter + 1;

            if(pinkyY < pinkyTargetY && blockCollideTestY(true, "pinky")) {
                ghostMove("pinky", "down");
                pinkyCounter = 0;
                return "down";
            } else if(pinkyY > pinkyTargetY && blockCollideTestY(false, "pinky")){
                ghostMove("pinky", "up");
                pinkyCounter = 0;
                return "up";

            } else if(blockCollideTestX(true, "pinky")){
                ghostMove("pinky", "right");
                return "lockright";
            }

        }

        //Lockleft
        if(lastDirection.equals("lockleft") && pinkyCounter < lockNumber){

            if(pinkyCounter == lockNumber-1){
                pinkyCounter = 0;
                pinkyLastLockDirection2 = pinkyLastLockDirection;
                pinkyLastLockDirection = "lockleft";
                return "left";
            }
            pinkyCounter = pinkyCounter + 1;

            if(pinkyY < pinkyTargetY && blockCollideTestY(true, "pinky")) {
                ghostMove("pinky", "down");
                pinkyCounter = 0;
                return "down";
            } else if(pinkyY > pinkyTargetY && blockCollideTestY(false, "pinky")){
                ghostMove("pinky", "up");
                pinkyCounter = 0;
                return "up";

            } else if(blockCollideTestX(false, "pinky")) {
                ghostMove("pinky", "left");
                return "lockleft";
            }

        }

        //Down
        if(yDifference > xDifference && pinkyY < pinkyTargetY){

            if(blockCollideTestY(true, "pinky")) {
                ghostMove("pinky", "down");
                return "down";
            }else if(pinkyX < pinkyTargetX) {

                if (blockCollideTestX(true, "pinky")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockleft";
                    else return "lockup";
                }
            }else if(pinkyX > pinkyTargetX) {

                if (blockCollideTestX(false, "pinky")) {

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
        else if(yDifference > xDifference && pinkyY > pinkyTargetY){

            if(blockCollideTestY(false, "pinky")){
                ghostMove("pinky", "up");
                return "up";
            }else if(pinkyX < pinkyTargetX) {

                if (blockCollideTestX(true, "pinky")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockdown";
                    else return "lockleft";
                }
            }else if(pinkyX > pinkyTargetX) {

                if (blockCollideTestX(false, "pinky")) {

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
        else if(xDifference > yDifference && pinkyX < pinkyTargetX){

            if(blockCollideTestX(true, "pinky")){
                ghostMove("pinky", "right");
                return "right";
            }else if(pinkyY < pinkyTargetY) {

                if (blockCollideTestY(true, "pinky")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockup";
                    else return "lockleft";
                }
            }else if(pinkyY > pinkyTargetY) {

                if (blockCollideTestY(false, "pinky")) {

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
        else if(xDifference > yDifference && pinkyX > pinkyTargetX){

            if(blockCollideTestX(false, "pinky")) {
                ghostMove("pinky", "left");
                return "left";
            }else if(pinkyY < pinkyTargetY) {

                if (blockCollideTestY(true, "pinky")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockup";
                }
            }else if(pinkyY > pinkyTargetY) {

                if (blockCollideTestY(false, "pinky")) {

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
        else if(pinkyY < pinkyTargetY){

            if(blockCollideTestY(true, "pinky")) {
                ghostMove("pinky", "down");
                return "down";
            }
        }
        //Up
        else if(pinkyY > pinkyTargetY){

            if(blockCollideTestY(false, "pinky")){
                ghostMove("pinky", "up");
                return "up";
            }else if(pinkyX < pinkyTargetX) {

                if (blockCollideTestX(true, "pinky")) {
                    ghostMove("pinky", "right");
                    return "right";
                }
            }else if(pinkyX > pinkyTargetX) {

                if (blockCollideTestX(false, "pinky")) {
                    ghostMove("pinky", "left");
                    return "left";
                }
            }

        }
        //Right
        else if(pinkyX < pinkyTargetX){

            if(blockCollideTestX(true, "pinky")){
                ghostMove("pinky", "right");
                return "right";
            }else if(pinkyY < pinkyTargetY) {

                if (blockCollideTestY(true, "pinky")) {
                    ghostMove("pinky", "down");
                    return "down";
                }
            }else if(pinkyY > pinkyTargetY) {

                if (blockCollideTestY(false, "pinky")) {
                    ghostMove("pinky", "up");
                    return "up";
                }
            }
        }
        //Left
        else if(pinkyX > pinkyTargetX){

            if(blockCollideTestX(false, "pinky")) {
                ghostMove("pinky", "left");
                return "left";
            }else if(pinkyY < pinkyTargetY) {

                if (blockCollideTestY(true, "pinky")) {
                    ghostMove("pinky", "down");
                    return "down";
                }
            }else if(pinkyY > pinkyTargetY) {

                if (blockCollideTestY(false, "pinky")) {
                    ghostMove("pinky", "up");
                    return "up";
                }
            }
        }

        return "left";

    }

    //inky Direction deciding method
    public String inkyMove(String lastDirection){

        int xDifference = Math.abs(inkyTargetX - inkyX);
        int yDifference = Math.abs(inkyTargetY - inkyY);
        int lockNumber = 160;
        int randomDirection = inkyRand.nextInt(2);
        int randomDirection4 = inkyRand.nextInt(4);
        int choose = inkyRand.nextInt(30);

        if(choose == 1 && !lastDirection.equals("lockdown")&& !lastDirection.equals("lockup")&& !lastDirection.equals("lockleft")&& !lastDirection.equals("lockright")) {
            //Random AI
            if (randomDirection4 == 0) return "lockup";
            if (randomDirection4 == 1) return "lockdown";
            if (randomDirection4 == 2) return "lockright";
            return "lockleft";
        }
        //Lockdown
        if(lastDirection.equals("lockdown") && inkyCounter < lockNumber){

            if(inkyCounter == lockNumber-1){
                inkyCounter = 0;
                inkyLastLockDirection2 = inkyLastLockDirection;
                inkyLastLockDirection = "lockdown";
                return "down";
            }
            inkyCounter = inkyCounter + 1;


            if(inkyX < inkyTargetX && blockCollideTestX(true, "inky")) {
                ghostMove("inky", "right");
                inkyCounter = 0;
                return "right";
            } else if(inkyX > inkyTargetX && blockCollideTestX(false, "inky")){
                ghostMove("inky", "left");
                inkyCounter = 0;
                return "left";

            } else if(blockCollideTestY(true, "inky")) {
                ghostMove("inky", "down");
                return "lockdown";
            }

        }

        //Lockup
        if(lastDirection.equals("lockup") && inkyCounter < lockNumber){

            if(inkyCounter == lockNumber-1){
                inkyCounter = 0;
                inkyLastLockDirection2 = inkyLastLockDirection;
                inkyLastLockDirection = "lockup";
                return "up";
            }
            inkyCounter = inkyCounter + 1;

            if(inkyX < inkyTargetX && blockCollideTestX(true, "inky")) {
                ghostMove("inky", "right");
                inkyCounter = 0;
                return "right";
            } else if(inkyX > inkyTargetX && blockCollideTestX(false, "inky")){
                ghostMove("inky", "left");
                inkyCounter = 0;
                return "left";

            } else if(blockCollideTestY(false, "inky")){
                ghostMove("inky", "up");
                return "lockup";
            }

        }

        //Lockright
        if(lastDirection.equals("lockright") && inkyCounter < lockNumber){

            if(inkyCounter == lockNumber-1){
                inkyCounter = 0;
                inkyLastLockDirection2 = inkyLastLockDirection;
                inkyLastLockDirection = "lockright";
                return "right";
            }
            inkyCounter = inkyCounter + 1;

            if(inkyY < inkyTargetY && blockCollideTestY(true, "inky")) {
                ghostMove("inky", "down");
                inkyCounter = 0;
                return "down";
            } else if(inkyY > inkyTargetY && blockCollideTestY(false, "inky")){
                ghostMove("inky", "up");
                inkyCounter = 0;
                return "up";

            } else if(blockCollideTestX(true, "inky")){
                ghostMove("inky", "right");
                return "lockright";
            }

        }

        //Lockleft
        if(lastDirection.equals("lockleft") && inkyCounter < lockNumber){

            if(inkyCounter == lockNumber-1){
                inkyCounter = 0;
                inkyLastLockDirection2 = inkyLastLockDirection;
                inkyLastLockDirection = "lockleft";
                return "left";
            }
            inkyCounter = inkyCounter + 1;

            if(inkyY < inkyTargetY && blockCollideTestY(true, "inky")) {
                ghostMove("inky", "down");
                inkyCounter = 0;
                return "down";
            } else if(inkyY > inkyTargetY && blockCollideTestY(false, "inky")){
                ghostMove("inky", "up");
                inkyCounter = 0;
                return "up";

            } else if(blockCollideTestX(false, "inky")) {
                ghostMove("inky", "left");
                return "lockleft";
            }

        }

        //Down
        if(yDifference > xDifference && inkyY < inkyTargetY){

            if(blockCollideTestY(true, "inky")) {
                ghostMove("inky", "down");
                return "down";
            }else if(inkyX < inkyTargetX) {

                if (blockCollideTestX(true, "inky")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockleft";
                    else return "lockup";
                }
            }else if(inkyX > inkyTargetX) {

                if (blockCollideTestX(false, "inky")) {

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
        else if(yDifference > xDifference && inkyY > inkyTargetY){

            if(blockCollideTestY(false, "inky")){
                ghostMove("inky", "up");
                return "up";
            }else if(inkyX < inkyTargetX) {

                if (blockCollideTestX(true, "inky")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockdown";
                    else return "lockleft";
                }
            }else if(inkyX > inkyTargetX) {

                if (blockCollideTestX(false, "inky")) {

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
        else if(xDifference > yDifference && inkyX < inkyTargetX){

            if(blockCollideTestX(true, "inky")){
                ghostMove("inky", "right");
                return "right";
            }else if(inkyY < inkyTargetY) {

                if (blockCollideTestY(true, "inky")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockup";
                    else return "lockleft";
                }
            }else if(inkyY > inkyTargetY) {

                if (blockCollideTestY(false, "inky")) {

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
        else if(xDifference > yDifference && inkyX > inkyTargetX){

            if(blockCollideTestX(false, "inky")) {
                ghostMove("inky", "left");
                return "left";
            }else if(inkyY < inkyTargetY) {

                if (blockCollideTestY(true, "inky")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockup";
                }
            }else if(inkyY > inkyTargetY) {

                if (blockCollideTestY(false, "inky")) {

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
        else if(inkyY < inkyTargetY){

            if(blockCollideTestY(true, "inky")) {
                ghostMove("inky", "down");
                return "down";
            }
        }
        //Up
        else if(inkyY > inkyTargetY){

            if(blockCollideTestY(false, "inky")){
                ghostMove("inky", "up");
                return "up";
            }else if(inkyX < inkyTargetX) {

                if (blockCollideTestX(true, "inky")) {
                    ghostMove("inky", "right");
                    return "right";
                }
            }else if(inkyX > inkyTargetX) {

                if (blockCollideTestX(false, "inky")) {
                    ghostMove("inky", "left");
                    return "left";
                }
            }

        }
        //Right
        else if(inkyX < inkyTargetX){

            if(blockCollideTestX(true, "inky")){
                ghostMove("inky", "right");
                return "right";
            }else if(inkyY < inkyTargetY) {

                if (blockCollideTestY(true, "inky")) {
                    ghostMove("inky", "down");
                    return "down";
                }
            }else if(inkyY > inkyTargetY) {

                if (blockCollideTestY(false, "inky")) {
                    ghostMove("inky", "up");
                    return "up";
                }
            }
        }
        //Left
        else if(inkyX > inkyTargetX){

            if(blockCollideTestX(false, "inky")) {
                ghostMove("inky", "left");
                return "left";
            }else if(inkyY < inkyTargetY) {

                if (blockCollideTestY(true, "inky")) {
                    ghostMove("inky", "down");
                    return "down";
                }
            }else if(inkyY > inkyTargetY) {

                if (blockCollideTestY(false, "inky")) {
                    ghostMove("inky", "up");
                    return "up";
                }
            }
        }

        return "left";

    }

    //clyde Direction deciding method
    public String clydeMove(String lastDirection){

        int xDifference = Math.abs(clydeTargetX - clydeX);
        int yDifference = Math.abs(clydeTargetY - clydeY);
        int lockNumber = 160;
        int randomDirection = clydeRand.nextInt(2);
        int randomDirection4 = clydeRand.nextInt(4);
        int choose = clydeRand.nextInt(20);

        if(choose == 1 && !lastDirection.equals("lockdown")&& !lastDirection.equals("lockup")&& !lastDirection.equals("lockleft")&& !lastDirection.equals("lockright")) {
            //Random AI
            if (randomDirection4 == 0) return "lockup";
            if (randomDirection4 == 1) return "lockdown";
            if (randomDirection4 == 2) return "lockright";
            return "lockleft";
        }
        //Lockdown
        if(lastDirection.equals("lockdown") && clydeCounter < lockNumber){

            if(clydeCounter == lockNumber-1){
                clydeCounter = 0;
                clydeLastLockDirection2 = clydeLastLockDirection;
                clydeLastLockDirection = "lockdown";
                return "down";
            }
            clydeCounter = clydeCounter + 1;


            if(clydeX < clydeTargetX && blockCollideTestX(true, "clyde")) {
                ghostMove("clyde", "right");
                clydeCounter = 0;
                return "right";
            } else if(clydeX > clydeTargetX && blockCollideTestX(false, "clyde")){
                ghostMove("clyde", "left");
                clydeCounter = 0;
                return "left";

            } else if(blockCollideTestY(true, "clyde")) {
                ghostMove("clyde", "down");
                return "lockdown";
            }

        }

        //Lockup
        if(lastDirection.equals("lockup") && clydeCounter < lockNumber){

            if(clydeCounter == lockNumber-1){
                clydeCounter = 0;
                clydeLastLockDirection2 = clydeLastLockDirection;
                clydeLastLockDirection = "lockup";
                return "up";
            }
            clydeCounter = clydeCounter + 1;

            if(clydeX < clydeTargetX && blockCollideTestX(true, "clyde")) {
                ghostMove("clyde", "right");
                clydeCounter = 0;
                return "right";
            } else if(clydeX > clydeTargetX && blockCollideTestX(false, "clyde")){
                ghostMove("clyde", "left");
                clydeCounter = 0;
                return "left";

            } else if(blockCollideTestY(false, "clyde")){
                ghostMove("clyde", "up");
                return "lockup";
            }

        }

        //Lockright
        if(lastDirection.equals("lockright") && clydeCounter < lockNumber){

            if(clydeCounter == lockNumber-1){
                clydeCounter = 0;
                clydeLastLockDirection2 = clydeLastLockDirection;
                clydeLastLockDirection = "lockright";
                return "right";
            }
            clydeCounter = clydeCounter + 1;

            if(clydeY < clydeTargetY && blockCollideTestY(true, "clyde")) {
                ghostMove("clyde", "down");
                clydeCounter = 0;
                return "down";
            } else if(clydeY > clydeTargetY && blockCollideTestY(false, "clyde")){
                ghostMove("clyde", "up");
                clydeCounter = 0;
                return "up";

            } else if(blockCollideTestX(true, "clyde")){
                ghostMove("clyde", "right");
                return "lockright";
            }

        }

        //Lockleft
        if(lastDirection.equals("lockleft") && clydeCounter < lockNumber){

            if(clydeCounter == lockNumber-1){
                clydeCounter = 0;
                clydeLastLockDirection2 = clydeLastLockDirection;
                clydeLastLockDirection = "lockleft";
                return "left";
            }
            clydeCounter = clydeCounter + 1;

            if(clydeY < clydeTargetY && blockCollideTestY(true, "clyde")) {
                ghostMove("clyde", "down");
                clydeCounter = 0;
                return "down";
            } else if(clydeY > clydeTargetY && blockCollideTestY(false, "clyde")){
                ghostMove("clyde", "up");
                clydeCounter = 0;
                return "up";

            } else if(blockCollideTestX(false, "clyde")) {
                ghostMove("clyde", "left");
                return "lockleft";
            }

        }

        //Down
        if(yDifference > xDifference && clydeY < clydeTargetY){

            if(blockCollideTestY(true, "clyde")) {
                ghostMove("clyde", "down");
                return "down";
            }else if(clydeX < clydeTargetX) {

                if (blockCollideTestX(true, "clyde")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockleft";
                    else return "lockup";
                }
            }else if(clydeX > clydeTargetX) {

                if (blockCollideTestX(false, "clyde")) {

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
        else if(yDifference > xDifference && clydeY > clydeTargetY){

            if(blockCollideTestY(false, "clyde")){
                ghostMove("clyde", "up");
                return "up";
            }else if(clydeX < clydeTargetX) {

                if (blockCollideTestX(true, "clyde")) {

                    return "lockright";
                } else {
                    if(randomDirection == 0) return "lockdown";
                    else return "lockleft";
                }
            }else if(clydeX > clydeTargetX) {

                if (blockCollideTestX(false, "clyde")) {

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
        else if(xDifference > yDifference && clydeX < clydeTargetX){

            if(blockCollideTestX(true, "clyde")){
                ghostMove("clyde", "right");
                return "right";
            }else if(clydeY < clydeTargetY) {

                if (blockCollideTestY(true, "clyde")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockup";
                    else return "lockleft";
                }
            }else if(clydeY > clydeTargetY) {

                if (blockCollideTestY(false, "clyde")) {

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
        else if(xDifference > yDifference && clydeX > clydeTargetX){

            if(blockCollideTestX(false, "clyde")) {
                ghostMove("clyde", "left");
                return "left";
            }else if(clydeY < clydeTargetY) {

                if (blockCollideTestY(true, "clyde")) {

                    return "lockdown";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockup";
                }
            }else if(clydeY > clydeTargetY) {

                if (blockCollideTestY(false, "clyde")) {

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
        else if(clydeY < clydeTargetY){

            if(blockCollideTestY(true, "clyde")) {
                ghostMove("clyde", "down");
                return "down";
            }
        }
        //Up
        else if(clydeY > clydeTargetY){

            if(blockCollideTestY(false, "clyde")){
                ghostMove("clyde", "up");
                return "up";
            }else if(clydeX < clydeTargetX) {

                if (blockCollideTestX(true, "clyde")) {
                    ghostMove("clyde", "right");
                    return "right";
                }
            }else if(clydeX > clydeTargetX) {

                if (blockCollideTestX(false, "clyde")) {
                    ghostMove("clyde", "left");
                    return "left";
                }
            }

        }
        //Right
        else if(clydeX < clydeTargetX){

            if(blockCollideTestX(true, "clyde")){
                ghostMove("clyde", "right");
                return "right";
            }else if(clydeY < clydeTargetY) {

                if (blockCollideTestY(true, "clyde")) {
                    ghostMove("clyde", "down");
                    return "down";
                }
            }else if(clydeY > clydeTargetY) {

                if (blockCollideTestY(false, "clyde")) {
                    ghostMove("clyde", "up");
                    return "up";
                }
            }
        }
        //Left
        else if(clydeX > clydeTargetX){

            if(blockCollideTestX(false, "clyde")) {
                ghostMove("clyde", "left");
                return "left";
            }else if(clydeY < clydeTargetY) {

                if (blockCollideTestY(true, "clyde")) {
                    ghostMove("clyde", "down");
                    return "down";
                }
            }else if(clydeY > clydeTargetY) {

                if (blockCollideTestY(false, "clyde")) {
                    ghostMove("clyde", "up");
                    return "up";
                }
            }
        }

        return "left";

    }
    
    //Generic Move Method
    public void ghostMove(String ghost, String direction){

        //blinky
        if(ghost.equals("blinky")){
            if(direction.equals("down")){
                blinkyY = blinkyY + blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) blinkyspr.setImage(blinkydown1);
                    else blinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) blinkyspr.setImage(blinkydown2);
                    else blinkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("up")){
                blinkyY = blinkyY - blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) blinkyspr.setImage(blinkyup1);
                    else blinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) blinkyspr.setImage(blinkyup2);
                    else blinkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("right")){
                blinkyX = blinkyX + blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) blinkyspr.setImage(blinkyright1);
                    else blinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) blinkyspr.setImage(blinkyright2);
                    else blinkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("left")){
                blinkyX = blinkyX - blinkySpeed;
                if(ghostAnimationType) {
                    if(!blinkyScared) blinkyspr.setImage(blinkyleft1);
                    else blinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!blinkyScared) blinkyspr.setImage(blinkyleft2);
                    else blinkyspr.setImage(scaredghost2);
                }
            }
        }

        //pinky
        if(ghost.equals("pinky")){
            if(direction.equals("down")){
                pinkyY = pinkyY + pinkySpeed;
                if(ghostAnimationType) {
                    if(!pinkyScared) pinkyspr.setImage(pinkydown1);
                    else pinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!pinkyScared) pinkyspr.setImage(pinkydown2);
                    else pinkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("up")){
                pinkyY = pinkyY - pinkySpeed;
                if(ghostAnimationType) {
                    if(!pinkyScared) pinkyspr.setImage(pinkyup1);
                    else pinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!pinkyScared) pinkyspr.setImage(pinkyup2);
                    else pinkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("right")){
                pinkyX = pinkyX + pinkySpeed;
                if(ghostAnimationType) {
                    if(!pinkyScared) pinkyspr.setImage(pinkyright1);
                    else pinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!pinkyScared) pinkyspr.setImage(pinkyright2);
                    else pinkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("left")){
                pinkyX = pinkyX - pinkySpeed;
                if(ghostAnimationType) {
                    if(!pinkyScared) pinkyspr.setImage(pinkyleft1);
                    else pinkyspr.setImage(scaredghost1);
                }
                else {
                    if(!pinkyScared) pinkyspr.setImage(pinkyleft2);
                    else pinkyspr.setImage(scaredghost2);
                }
            }
        }

        //inky
        if(ghost.equals("inky")){
            if(direction.equals("down")){
                inkyY = inkyY + inkySpeed;
                if(ghostAnimationType) {
                    if(!inkyScared) inkyspr.setImage(inkydown1);
                    else inkyspr.setImage(scaredghost1);
                }
                else {
                    if(!inkyScared) inkyspr.setImage(inkydown2);
                    else inkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("up")){
                inkyY = inkyY - inkySpeed;
                if(ghostAnimationType) {
                    if(!inkyScared) inkyspr.setImage(inkyup1);
                    else inkyspr.setImage(scaredghost1);
                }
                else {
                    if(!inkyScared) inkyspr.setImage(inkyup2);
                    else inkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("right")){
                inkyX = inkyX + inkySpeed;
                if(ghostAnimationType) {
                    if(!inkyScared) inkyspr.setImage(inkyright1);
                    else inkyspr.setImage(scaredghost1);
                }
                else {
                    if(!inkyScared) inkyspr.setImage(inkyright2);
                    else inkyspr.setImage(scaredghost2);
                }
            }
            if(direction.equals("left")){
                inkyX = inkyX - inkySpeed;
                if(ghostAnimationType) {
                    if(!inkyScared) inkyspr.setImage(inkyleft1);
                    else inkyspr.setImage(scaredghost1);
                }
                else {
                    if(!inkyScared) inkyspr.setImage(inkyleft2);
                    else inkyspr.setImage(scaredghost2);
                }
            }
        }

        //clyde
        if(ghost.equals("clyde")){
            if(direction.equals("down")){
                clydeY = clydeY + clydeSpeed;
                if(ghostAnimationType) {
                    if(!clydeScared) clydespr.setImage(clydedown1);
                    else clydespr.setImage(scaredghost1);
                }
                else {
                    if(!clydeScared) clydespr.setImage(clydedown2);
                    else clydespr.setImage(scaredghost2);
                }
            }
            if(direction.equals("up")){
                clydeY = clydeY - clydeSpeed;
                if(ghostAnimationType) {
                    if(!clydeScared) clydespr.setImage(clydeup1);
                    else clydespr.setImage(scaredghost1);
                }
                else {
                    if(!clydeScared) clydespr.setImage(clydeup2);
                    else clydespr.setImage(scaredghost2);
                }
            }
            if(direction.equals("right")){
                clydeX = clydeX + clydeSpeed;
                if(ghostAnimationType) {
                    if(!clydeScared) clydespr.setImage(clyderight1);
                    else clydespr.setImage(scaredghost1);
                }
                else {
                    if(!clydeScared) clydespr.setImage(clyderight2);
                    else clydespr.setImage(scaredghost2);
                }
            }
            if(direction.equals("left")){
                clydeX = clydeX - clydeSpeed;
                if(ghostAnimationType) {
                    if(!clydeScared) clydespr.setImage(clydeleft1);
                    else clydespr.setImage(scaredghost1);
                }
                else {
                    if(!clydeScared) clydespr.setImage(clydeleft2);
                    else clydespr.setImage(scaredghost2);
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
        powerPelletStartTime = 800 * 4;

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
        powerPelletStartTime = Math.max(20, powerPelletStartTime - (level * 80));
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
        blinkyX = 962;
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
        blinkyCoolDown = 100;
        blinkyScared = false;
        blinkyspr.setImage(blinkyup1);
        blinkyEatenPause = 20;

        //pinky (pink)
        pinkyX = 1005;
        pinkyY = 525;
        pinkySpeed = 2;
        pinkyLastDirection = "left";
        pinkyLastLockDirection = "lockleft";
        pinkyLastLockDirection2 = "lockup";
        pinkyTargetX = x;
        pinkyTargetY = y;
        pinkyTarget = "pacman";
        pinkyCounter = 0;
        pinkyWidth = 45;
        pinkyHeight = 45;
        pinkyCoolDown = 200;
        pinkyScared = false;
        pinkyspr.setImage(pinkyup1);
        pinkyEatenPause = 20;

        //inky (blue)
        inkyX = 919;
        inkyY = 525;
        inkySpeed = 2;
        inkyLastDirection = "left";
        inkyLastLockDirection = "lockleft";
        inkyLastLockDirection2 = "lockup";
        inkyTargetX = x;
        inkyTargetY = y;
        inkyTarget = "pacman";
        inkyCounter = 0;
        inkyWidth = 45;
        inkyHeight = 45;
        inkyCoolDown = 300;
        inkyScared = false;
        inkyspr.setImage(inkyup1);
        inkyEatenPause = 20;

        //clyde (orange)
        clydeX = 876;
        clydeY = 525;
        clydeSpeed = 2;
        clydeLastDirection = "left";
        clydeLastLockDirection = "lockleft";
        clydeLastLockDirection2 = "lockup";
        clydeTargetX = x;
        clydeTargetY = y;
        clydeTarget = "pacman";
        clydeCounter = 0;
        clydeWidth = 45;
        clydeHeight = 45;
        clydeCoolDown = 400;
        clydeScared = false;
        clydespr.setImage(clydeup1);
        clydeEatenPause = 20;

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

        //pacman
        if(name.equals("pacman")) {
            XT = new Rectangle(x + speed, y, width, height);
            XF = new Rectangle(x - speed, y, width, height);
        }

        //blinky
        if(name.equals("blinky")) {
            XT = new Rectangle(blinkyX + blinkySpeed, blinkyY, blinkyWidth, blinkyHeight);
            XF = new Rectangle(blinkyX - blinkySpeed, blinkyY, blinkyWidth, blinkyHeight);
        }

        //pinky
        if(name.equals("pinky")) {
            XT = new Rectangle(pinkyX + pinkySpeed, pinkyY, pinkyWidth, pinkyHeight);
            XF = new Rectangle(pinkyX - pinkySpeed, pinkyY, pinkyWidth, pinkyHeight);
        }

        //inky
        if(name.equals("inky")) {
            XT = new Rectangle(inkyX + inkySpeed, inkyY, inkyWidth, inkyHeight);
            XF = new Rectangle(inkyX - inkySpeed, inkyY, inkyWidth, inkyHeight);
        }

        //clyde
        if(name.equals("clyde")) {
            XT = new Rectangle(clydeX + clydeSpeed, clydeY, clydeWidth, clydeHeight);
            XF = new Rectangle(clydeX - clydeSpeed, clydeY, clydeWidth, clydeHeight);
        }

        //True direction is up and false is down
        return Collision(direction, XT, XF);
    }

    //Returns true if pacman/ghost can't move without colliding in the desired y direction
    public boolean blockCollideTestY(boolean direction, String name){

        Rectangle YT = new Rectangle();
        Rectangle YF = new Rectangle();

        //pacman
        if(name.equals("pacman")) {
            YT = new Rectangle(x, y + speed, width, height);
            YF = new Rectangle(x, y - speed, width, height);
        }
        
        //blinky
        if(name.equals("blinky")) {
            YT = new Rectangle(blinkyX, blinkyY + blinkySpeed, blinkyWidth, blinkyHeight);
            YF = new Rectangle(blinkyX, blinkyY - blinkySpeed, blinkyWidth, blinkyHeight);
        }

        //pinky
        if(name.equals("pinky")) {
            YT = new Rectangle(pinkyX, pinkyY + pinkySpeed, pinkyWidth, pinkyHeight);
            YF = new Rectangle(pinkyX, pinkyY - pinkySpeed, pinkyWidth, pinkyHeight);
        }

        //inky
        if(name.equals("inky")) {
            YT = new Rectangle(inkyX, inkyY + inkySpeed, inkyWidth, inkyHeight);
            YF = new Rectangle(inkyX, inkyY - inkySpeed, inkyWidth, inkyHeight);
        }

        //clyde
        if(name.equals("clyde")) {
            YT = new Rectangle(clydeX, clydeY + clydeSpeed, clydeWidth, clydeHeight);
            YF = new Rectangle(clydeX, clydeY - clydeSpeed, clydeWidth, clydeHeight);
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
        //int x=e.getX();
        //int y=e.getY();
        //System.out.println(x+","+y);//these co-ords are relative to the component
    }




}

