package pacman;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
//import engine.SpriteClickable;
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
    Canvas c;


    //Pacman vars
    int x = 935;
    int y = 590;
    int speed = 2;
    int width = 45;
    int height = 45;
    Rectangle pacmanR = new Rectangle(x, y, width, height);

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
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block31
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block32
        boundaryList.add(new Rectangle(0, 0, 1, 1));

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
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block39
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block40
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block41
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block42
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block43
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block44
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block45
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block46
        boundaryList.add(new Rectangle(0, 0, 1, 1));

        //Block47
        boundaryList.add(new Rectangle(0, 0, 1, 1));

    }

    @Override
    public void start() {
        c.put(backgroundspr, "background");
        c.put(boardspr, "board");
        c.put(pacmanspr, "pacman");

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

        //Pacman Movement
        if(c.getKeysDown().contains('s') && blockCollideTestY(true)){
            y = y + speed;
            //spr.setImage(down);
        } else if(c.getKeysDown().contains('w') && blockCollideTestY(false)){
            y = y - speed;
            //spr.setImage(up);
        } else if(c.getKeysDown().contains('d') && blockCollideTestX(true)){
            x = x + speed;
            //spr.setImage(right);
        } else if(c.getKeysDown().contains('a') && blockCollideTestX(false)){
            x = x - speed;
            //spr.setImage(left);
        }

        for(int i = 0; i < pelletNumber; i++){

            pelletR = new Rectangle(pelletList.get(i).getX(), pelletList.get(i).getY(), pelletSize, pelletSize);
            //if(pacmanR.intersects(pelletR) && pelletList.get(i).isVisible()){

                pelletList.get(i).setVisible(false);

            //}

        }


        pacmanspr.setX(x);
        pacmanspr.setY(y);


    }

    //Returns true if pacman can't move without colliding in the desired x direction
    public boolean blockCollideTestX(boolean direction){

        Rectangle pacmanXT = new Rectangle(x+speed, y, width, height);
        Rectangle pacmanXF = new Rectangle(x-speed, y, width, height);

        //True direction is up and false is down
        return Collision(direction, pacmanXT, pacmanXF);
    }

    //Returns true if pacman can't move without colliding in the desired y direction
    public boolean blockCollideTestY(boolean direction){

        Rectangle pacmanYT = new Rectangle(x, y+speed, width, height);
        Rectangle pacmanYF = new Rectangle(x, y-speed, width, height);

        //True direction is up and false is down
        return Collision(direction, pacmanYT, pacmanYF);
    }

    private boolean Collision(boolean direction, Rectangle pacmanT, Rectangle pacmanF) {
        if(direction){
            return Boundary(pacmanT);

        }else {

            return Boundary(pacmanF);
        }
    }

    private boolean Boundary(Rectangle pacman) {

        for (Rectangle rectangle : boundaryList) {

            if (pacman.intersects(rectangle)) {

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

