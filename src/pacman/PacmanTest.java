package pacman;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
//import engine.SpriteClickable;
//import org.w3c.dom.css.Rect;
import util.ResourceLoader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.awt.Point;

public class PacmanTest implements GameElement, MouseMotionListener {
    Sprite backgroundspr;
    Sprite boardspr;
    Sprite pacmanspr;
    Sprite redspr;
    Canvas c;


    //Pacman vars
    int x = 935;
    int y = 590;
    int speed = 2;
    int width = 45;
    int height = 45;
    Rectangle pacmanR = new Rectangle(x, y, width, height);

    //Blinky (red)
    int redx = 950;
    int redy = 525;
    int redSpeed = 1;
    String redxDirection = "right";
    String redyDirection = "down";
    int redWidth = 45;
    int redHeight = 45;
    Rectangle redR = new Rectangle(redx, redy, redWidth, redHeight);

    //Pellets
    int pelletNumber1 = 5;
    int pelletNumber2 = 5;
    int pelletNumber = pelletNumber1 + pelletNumber2;
    int pelletSize = 50;
    Rectangle pelletR;
    ArrayList<Sprite> pelletList = new ArrayList<>();

    //Boundaries

    ArrayList<Rectangle> boundaryList = new ArrayList<>();

    //Put buffered images here
    BufferedImage neutral = ResourceLoader.getImage("pacman/circle.png");
    BufferedImage background = ResourceLoader.getImage("pacman/background.png");
    BufferedImage board = ResourceLoader.getImage("pacman/board.png");



    public PacmanTest(Canvas c) {
        this.c = c;

        //Background
        backgroundspr = new Sprite(background, 528, 0, 864, 1080, 0);

        //Board
        boardspr = new Sprite(board, 540, 116, 840, 887, 1);

        //Pacman
        pacmanspr = new Sprite(neutral, x, y, width, height, 2);

        //Blinky (red)
        redspr = new Sprite(neutral, redx, redy, redWidth, redHeight, 2);

        //Pellets
        for(int i = 0; i < pelletNumber1; i++){

            pelletList.add(new Sprite(neutral, 100 * i, 300, 45, 45, 2));

        }

        for(int i = 0; i < pelletNumber2; i++){

            pelletList.add(new Sprite(neutral, 500, 100 * i, 45, 45, 2));

        }


        //Boundaries
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
        boundaryList.add(new Rectangle(872, 478, 62, 12));

    }

    @Override
    public void start() {
        c.put(backgroundspr, "background");
        c.put(boardspr, "board");
        c.put(pacmanspr, "pacman");
        c.put(redspr, "Blinky");

        for(int i = 0; i < pelletNumber; i++){
            c.put(pelletList.get(i), "pellet" + i);

        }



    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

        pacmanR = new Rectangle(x, y, width, height);

        redR = new Rectangle(redx, redy, redWidth, redHeight);

        //Pacman Movement
        if(c.getKeysDown().contains('s') && blockCollideTestY(true, "pacman")){
            y = y + speed;
            //spr.setImage(down);
        } else if(c.getKeysDown().contains('w') && blockCollideTestY(false, "pacman")){
            y = y - speed;
            //spr.setImage(up);
        } else if(c.getKeysDown().contains('d') && blockCollideTestX(true, "pacman")){
            x = x + speed;
            //spr.setImage(right);
        } else if(c.getKeysDown().contains('a') && blockCollideTestX(false, "pacman")){
            x = x - speed;
            //spr.setImage(left);
        }


        //Blinky movement
        if(blinkyDirection().equals("down") && blockCollideTestY(true, "blinky")){
            redy = redy + redSpeed;
            //spr.setImage(down);
        } else if(blinkyDirection().equals("up") && blockCollideTestY(false, "blinky")){
            redy = redy - redSpeed;
            //spr.setImage(up);
        } else if(blinkyDirection().equals("right") && blockCollideTestX(true, "blinky")){
            redx = redx + redSpeed;
            //spr.setImage(right);
        } else if(blinkyDirection().equals("left") && blockCollideTestX(false, "blinky")){
            redx = redx - redSpeed;
            //spr.setImage(left);
        }



        //Pellets
        for(int i = 0; i < pelletNumber; i++){

            pelletR = new Rectangle(pelletList.get(i).getX(), pelletList.get(i).getY(), pelletSize, pelletSize);
            //if(pacmanR.intersects(pelletR) && pelletList.get(i).isVisible()){

                pelletList.get(i).setVisible(false);

            //}

        }

        //Pacman coordinates update
        pacmanspr.setX(x);
        pacmanspr.setY(y);

        //Blinky coordinates update
        //redspr.setX(redx);
        //redspr.setY(redy);


    }


    //Blinky Direction deciding method
    public String blinkyDirection(){

        int xDifference = Math.abs(x-redx);
        int yDifference = Math.abs(y-redy);

        //If farther up/down than left/right
        if(yDifference > xDifference){
            //Down check
            if(redy < y - redSpeed && blockCollideTestY(true, "blinky")){
                return "down";
            }else if(redxDirection.equals("right") && blockCollideTestX(true, "blinky")){
                return "right";
            }else if(blockCollideTestX(false, "blinky")){
                redxDirection = "left";
                return "left";
            }else if(blockCollideTestY(false, "blinky")){
                redxDirection = "right";
                return "up";

            }

            //Up check
            if(redy > y + redSpeed && blockCollideTestY(false, "blinky")){
                return "up";
            }else if(redxDirection.equals("right") && blockCollideTestX(true, "blinky")){
                return "right";
            }else if(blockCollideTestX(false, "blinky")){
                redxDirection = "left";
                return "left";
            }else if(blockCollideTestY(true, "blinky")){
                redxDirection = "right";
                return "down";

            }
        } else if(xDifference > yDifference){

            //Right check
            if(redx < x - redSpeed && blockCollideTestX(true, "blinky")){
                return "right";
            }else if(redyDirection.equals("down") && blockCollideTestY(true, "blinky")) {
                return "down";
            }else if(blockCollideTestY(false, "blinky")){
                redyDirection = "up";
                return "up";
            }else if(blockCollideTestX(false, "blinky")){
                redyDirection = "down";
                return "left";
            }

            //Left check
            if(redx > x + redSpeed && blockCollideTestX(false, "blinky")){
                return "left";
            }else if(redyDirection.equals("down") && blockCollideTestY(true, "blinky")) {
                return "down";
            }else if(blockCollideTestY(false, "blinky")){
                redyDirection = "up";
                return "up";
            }else if(blockCollideTestX(true, "blinky")){
                redyDirection = "down";
                return "right";
            }
        }

        return "ERROR";

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
            XT = new Rectangle(redx + redSpeed, redy, redWidth, redHeight);
            XF = new Rectangle(redx - redSpeed, redy, redWidth, redHeight);
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
            YT = new Rectangle(redx, redy  + redSpeed, redWidth, redHeight);
            YF = new Rectangle(redx, redy - redSpeed, redWidth, redHeight);
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

