package pacman;

import engine.Canvas;
import engine.GameElement;
import engine.Sprite;
//import engine.SpriteClickable;
//import org.w3c.dom.css.Rect;
import engine.SpriteText;
import util.ResourceLoader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
//import java.awt.Point;

public class PacmanTest implements GameElement, MouseMotionListener {
    Sprite backgroundspr;
    Sprite boardspr;
    Sprite pacmanspr;
    Sprite redspr;
    Sprite doorspr;
    SpriteText scorespr;
    Canvas c;


    //Pacman vars
    int x = 940;
    int y = 765;
    int speed = 2;
    int width = 45;
    int height = 45;
    Rectangle pacmanR = new Rectangle(x, y, width, height);
    int score = 0;

    //Blinky (red)
    int redx = 950;
    int redy = 525;
    int redSpeed = 1;
    String redLastDirection = "left";
    String redLastLockDirection = "lockleft";
    String redLastLockDirection2 = "lockup";
    Random rand = new Random(123);
    int redCounter = 0;
    int redWidth = 45;
    int redHeight = 45;
    Rectangle redR = new Rectangle(redx, redy, redWidth, redHeight);
    int redCoolDown = 400;

    //region Pellet variables
    int pelletNumberH1 = 24;
    int pelletNumberH2 = 26;
    int pelletNumberH3 = 20;
    int pelletNumberH4 = 24;
    int pelletNumberH5 = 18;
    int pelletNumberH6 = 20;
    int pelletNumberH7 = 26;
    int pelletNumberV1 = 8;
    int pelletNumberV2 = 2;
    int pelletNumberV3 = 20;
    int pelletNumberV4 = 4;
    int pelletNumberV5 = 7;
    int pelletNumberV6 = 7;
    int pelletNumberV7 = 4;
    int pelletNumberV8 = 20;
    int pelletNumberV9 = 2;
    int pelletNumberV10 = 8;
    int pelletNumber = 240;
    int pelletSize = 10;
    int pelletDistanceX = 30;
    int pelletDistanceY = 28;
    Rectangle pelletR;
    ArrayList<Sprite> pelletList = new ArrayList<>();
    //endregion

    //Boundaries
    ArrayList<Rectangle> boundaryList = new ArrayList<>();

    //Put buffered images here
    BufferedImage neutral = ResourceLoader.getImage("pacman/circle.png");
    BufferedImage background = ResourceLoader.getImage("pacman/background.png");
    BufferedImage board = ResourceLoader.getImage("pacman/board.png");
    BufferedImage pellet = ResourceLoader.getImage("pacman/pellet.png");
    BufferedImage door = ResourceLoader.getImage("pacman/door.png");


    public PacmanTest(Canvas c) {
        this.c = c;

        //Background
        backgroundspr = new Sprite(background, 528, 0, 864, 1080, 0);

        //Board
        boardspr = new Sprite(board, 540, 116, 840, 887, 1);

        //Door
        doorspr = new Sprite(door, 934, 482, 56, 8, 2);

        //Score
        scorespr = new SpriteText(924, 80, 200, 200, 2);
        scorespr.setText(""+score, Color.lightGray, 2);

        //Pacman
        pacmanspr = new Sprite(neutral, x, y, width, height, 2);

        //Blinky (red)
        redspr = new Sprite(neutral, redx, redy, redWidth, redHeight, 3);

        //region Pellets
        //Horizontal Lines always take precedence over vertical lines
        //H1 and V1 both start at the top left corner
        //Horizontal Line 1
        for(int i = 0; i < pelletNumberH1+2; i++){

            if(i != 12 && i != 13)
            pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 165, pelletSize, pelletSize, 2));

        }
        //Horizontal Line 2
        for(int i = 0; i < pelletNumberH2; i++){

            pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 279, pelletSize, pelletSize, 2));

        }
        //Horizontal Line 3
        for(int i = 0; i < pelletNumberH3+6; i++){

            if(i != 6 && i != 7 && i != 12 && i != 13 && i != 18 && i!= 19) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 360, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 4
        for(int i = 0; i < pelletNumberH4+2; i++){

            if(i != 12 && i != 13) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 699, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 5
        for(int i = 0; i < pelletNumberH5+8; i++){

            if(i != 0 && i != 3 && i != 4 && i != 12 && i != 13 && i != 21 && i != 22 && i != 25) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 783, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 6
        for(int i = 0; i < pelletNumberH6+6; i++){

            if(i != 6 && i != 7 && i != 12 && i != 13 && i != 18 && i!= 19) {
                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 867, pelletSize, pelletSize, 2));
            }
        }
        //Horizontal Line 7
        for(int i = 0; i < pelletNumberH7; i++){

                pelletList.add(new Sprite(pellet, 585 + (pelletDistanceX * i), 948, pelletSize, pelletSize, 2));

        }


        //Vertical Line 1
        for(int i = 0; i < pelletNumberV1+21; i++){
            if(i == 1 || i == 3 || i == 5 || i == 6 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 585, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 2
        for(int i = 0; i < pelletNumberV2+27; i++){
            if(i == 24 || i == 23) {
                pelletList.add(new Sprite(pellet, 645, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 3
        for(int i = 0; i < pelletNumberV3+9; i++){
            if(i != 0 && i != 4 && i != 7 && i != 19 && i != 22 && i != 25 && i != 26 && i != 27 && i != 28)
            pelletList.add(new Sprite(pellet, 735, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));

        }
        //Vertical Line 4
        for(int i = 0; i < pelletNumberV4+25; i++){
            if(i == 5 || i == 6 || i == 23 || i == 24) {
                pelletList.add(new Sprite(pellet, 825, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 5
        for(int i = 0; i < pelletNumberV5+22; i++){
            if(i == 1 || i == 2 || i == 3 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 915, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 6
        for(int i = 0; i < pelletNumberV6+22; i++){
            if(i == 1 || i == 2 || i == 3 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 1005, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 7
        for(int i = 0; i < pelletNumberV7+25; i++){
            if(i == 5 || i == 6 || i == 23 || i == 24) {
                pelletList.add(new Sprite(pellet, 1095, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 8
        for(int i = 0; i < pelletNumberV8+9; i++){
            if(i != 0 && i != 4 && i != 7 && i != 19 && i != 22 && i != 25 && i != 26 && i != 27 && i != 28)
                pelletList.add(new Sprite(pellet, 1185, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));

        }
        //Vertical Line 9
        for(int i = 0; i < pelletNumberV9+27; i++){
            if(i == 24 || i == 23) {
                pelletList.add(new Sprite(pellet, 1275, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
        //Vertical Line 10
        for(int i = 0; i < pelletNumberV10+21; i++){
            if(i == 1 || i == 3 || i == 5 || i == 6 || i == 27 || i == 26 || i == 21 || i == 20) {
                pelletList.add(new Sprite(pellet, 1335, 165 + (pelletDistanceY * i), pelletSize, pelletSize, 2));
            }
        }
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
        c.put(backgroundspr, "background");
        c.put(boardspr, "board");
        c.put(scorespr, "score");
        c.put(doorspr, "door");
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
        if(redCoolDown > 0){
            redCoolDown = redCoolDown -1;
        }
        if(redCoolDown == 0){
            if(redx > 942) redx = redx -1;
            else if(redx < 942) redx = redx + 1;
            else if(redy > 424) redy = redy - 1;
            else if(redy < 424) redy = redy + 1;
            else redCoolDown = -1;
        }
        if(redCoolDown == -1) redLastDirection = blinkyMove(redLastDirection);

        //Pellets
        for(int i = 0; i < pelletNumber; i++){

            pelletR = new Rectangle(pelletList.get(i).getX(), pelletList.get(i).getY(), pelletSize, pelletSize);
            if(pacmanR.intersects(pelletR) && pelletList.get(i).isVisible()){

                pelletList.get(i).setVisible(false);
                score = score + 10;

            }

        }

        //Pacman coordinates update
        pacmanspr.setX(x);
        pacmanspr.setY(y);

        //Update Score
        scorespr.setText(""+score, Color.lightGray, 2);

        //Blinky coordinates update
        redspr.setX(redx);
        redspr.setY(redy);


    }


    //Blinky Direction deciding method
    public String blinkyMove(String lastDirection){

        int xDifference = Math.abs(x-redx);
        int yDifference = Math.abs(y-redy);
        int lockNumber = 160;
        int randomDirection = rand.nextInt(2);

        //Lockdown
        if(lastDirection.equals("lockdown") && redCounter < lockNumber){

            if(redCounter == lockNumber-1){
                redCounter = 0;
                redLastLockDirection2 = redLastLockDirection;
                redLastLockDirection = "lockdown";
                return "down";
            }
            redCounter = redCounter + 1;


            if(redx < x && blockCollideTestX(true, "blinky")) {
                redx = redx + redSpeed;
                redCounter = 0;
                return "right";
            } else if(redx > x && blockCollideTestX(false, "blinky")){
                redx = redx - redSpeed;
                redCounter = 0;
                return "left";

            } else if(blockCollideTestY(true, "blinky")) {
                redy = redy + redSpeed;
                return "lockdown";
            }

        }

        //Lockup
        if(lastDirection.equals("lockup") && redCounter < lockNumber){

            if(redCounter == lockNumber-1){
                redCounter = 0;
                redLastLockDirection2 = redLastLockDirection;
                redLastLockDirection = "lockup";
                return "up";
            }
            redCounter = redCounter + 1;

            if(redx < x && blockCollideTestX(true, "blinky")) {
                redx = redx + redSpeed;
                redCounter = 0;
                return "right";
            } else if(redx > x && blockCollideTestX(false, "blinky")){
                redx = redx - redSpeed;
                redCounter = 0;
                return "left";

            } else if(blockCollideTestY(false, "blinky")){
                redy = redy - redSpeed;
                return "lockup";
            }

        }

        //Lockright
        if(lastDirection.equals("lockright") && redCounter < lockNumber){

            if(redCounter == lockNumber-1){
                redCounter = 0;
                redLastLockDirection2 = redLastLockDirection;
                redLastLockDirection = "lockright";
                return "right";
            }
            redCounter = redCounter + 1;

            if(redy < y && blockCollideTestY(true, "blinky")) {
                redy = redy + redSpeed;
                redCounter = 0;
                return "down";
            } else if(redy > y && blockCollideTestY(false, "blinky")){
                redy = redy - redSpeed;
                redCounter = 0;
                return "up";

            } else if(blockCollideTestX(true, "blinky")){
                redx = redx + redSpeed;
                return "lockright";
            }

        }

        //Lockleft
        if(lastDirection.equals("lockleft") && redCounter < lockNumber){

            if(redCounter == lockNumber-1){
                redCounter = 0;
                redLastLockDirection2 = redLastLockDirection;
                redLastLockDirection = "lockleft";
                return "left";
            }
            redCounter = redCounter + 1;

            if(redy < y && blockCollideTestY(true, "blinky")) {
                redy = redy + redSpeed;
                redCounter = 0;
                return "down";
            } else if(redy > y && blockCollideTestY(false, "blinky")){
                redy = redy - redSpeed;
                redCounter = 0;
                return "up";

            } else if(blockCollideTestX(false, "blinky")) {
                redx = redx - redSpeed;
                return "lockleft";
            }

        }

        //Down
        if(yDifference > xDifference && redy < y){

            if(blockCollideTestY(true, "blinky")) {
                redy = redy + redSpeed;
                return "down";
            }else if(redx < x) {

                if (blockCollideTestX(true, "blinky")) {
                    redx = redx + redSpeed;
                    return "right";
                } else {
                    if(randomDirection == 0) return "lockleft";
                    else return "lockup";
                }
            }else if(redx > x) {

                if (blockCollideTestX(false, "blinky")) {
                    redx = redx - redSpeed;
                    return "left";
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
        else if(yDifference > xDifference && redy > y){

            if(blockCollideTestY(false, "blinky")){
                redy = redy - redSpeed;
                return "up";
            }else if(redx < x) {

                if (blockCollideTestX(true, "blinky")) {
                    redx = redx + redSpeed;
                    return "right";
                } else {
                    if(randomDirection == 0) return "lockdown";
                    else return "lockleft";
                }
            }else if(redx > x) {

                if (blockCollideTestX(false, "blinky")) {
                    redx = redx - redSpeed;
                    return "left";
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
        else if(xDifference > yDifference && redx < x){

            if(blockCollideTestX(true, "blinky")){
                redx = redx + redSpeed;
                return "right";
            }else if(redy < y) {

                if (blockCollideTestY(true, "blinky")) {
                    redy = redy + redSpeed;
                    return "down";
                } else {
                    if(randomDirection == 0) return "lockup";
                    else return "lockleft";
                }
            }else if(redy > y) {

                if (blockCollideTestY(false, "blinky")) {
                    redy = redy - redSpeed;
                    return "up";
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
        else if(xDifference > yDifference && redx > x){

            if(blockCollideTestX(false, "blinky")) {
                redx = redx - redSpeed;
                return "left";
            }else if(redy < y) {

                if (blockCollideTestY(true, "blinky")) {
                    redy = redy + redSpeed;
                    return "down";
                } else {
                    if(randomDirection == 0) return "lockright";
                    else return "lockup";
                }
            }else if(redy > y) {

                if (blockCollideTestY(false, "blinky")) {
                    redy = redy - redSpeed;
                    return "up";
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
         else if(redy < y){

            if(blockCollideTestY(true, "blinky")) {
                redy = redy + redSpeed;
                return "down";
            }else if(redx < x) {

                if (blockCollideTestX(true, "blinky")) {
                    redx = redx + redSpeed;
                    return "right";
                }
            }else if(redx > x) {

                if (blockCollideTestX(false, "blinky")) {
                    redx = redx - redSpeed;
                    return "left";
                }
            }
        }
        //Up
        else if(redy > y){

            if(blockCollideTestY(false, "blinky")){
                redy = redy - redSpeed;
                return "up";
            }else if(redx < x) {

                if (blockCollideTestX(true, "blinky")) {
                    redx = redx + redSpeed;
                    return "right";
                }
            }else if(redx > x) {

                if (blockCollideTestX(false, "blinky")) {
                    redx = redx - redSpeed;
                    return "left";
                }
            }

        }
        //Right
        else if(redx < x){

            if(blockCollideTestX(true, "blinky")){
                redx = redx + redSpeed;
                return "right";
            }else if(redy < y) {

                if (blockCollideTestY(true, "blinky")) {
                    redy = redy + redSpeed;
                    return "down";
                }
            }else if(redy > y) {

                if (blockCollideTestY(false, "blinky")) {
                    redy = redy - redSpeed;
                    return "up";
                }
            }
        }
        //Left
        else if(redx > x){

            if(blockCollideTestX(false, "blinky")) {
                redx = redx - redSpeed;
                return "left";
            }else if(redy < y) {

                if (blockCollideTestY(true, "blinky")) {
                    redy = redy + redSpeed;
                    return "down";
                }
            }else if(redy > y) {

                if (blockCollideTestY(false, "blinky")) {
                    redy = redy - redSpeed;
                    return "up";
                }
            }
        }

        return "left";

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

