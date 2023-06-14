package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Player extends Entity{
    public boolean played = false;

    public boolean hasGun = false;
    public int banishedGhosts = 0;
    public int multiplier = 7;
    public int attackState = 2;
    public int defaultState = 1;
    public int deadState = 3;
    public int deadState2 = 4;

    public int state = defaultState;

    public BufferedImage attackU, attackDwn, attackLft, attackRght, dead;
    public final int screenX;
    public final int screenY;
    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 21;
        solidArea.width = 17;
        solidArea.height = 17;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        screenX = gp.screenWidth/2 - (gp.tileSizeRes/2);
        screenY = gp.screenHeight/2 - (gp.tileSizeRes/2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 16*gp.tileSizeRes; //16
        worldY = 89*gp.tileSizeRes; //89
        speed = 1.4F;
        direction = "up";
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void getPlayerImage(){

        up1 = setup("/player/david_up_1", gp.tileSize, gp.tileHeight);
        up2 = setup("/player/david_up_2", gp.tileSize, gp.tileHeight);
        down1 = setup("/player/david_down_1", gp.tileSize, gp.tileHeight);
        down2 = setup("/player/david_down_2", gp.tileSize, gp.tileHeight);
        left1 = setup("/player/david_left_1", gp.tileSize, gp.tileHeight);
        left2 = setup("/player/david_left_2", gp.tileSize, gp.tileHeight);
        right1 = setup("/player/david_right_1", gp.tileSize, gp.tileHeight);
        right2 = setup("/player/david_right_2", gp.tileSize, gp.tileHeight);
        attackU = setup("/player/johnCrossBack", 30, 60);
        attackDwn = setup("/player/johnCross", 30, 60);
        attackLft = setup("/player/johnCrossLeft", 24, 60);
        attackRght = setup("/player/johnCrossRight", 24, 60);
        dead = setup("/player/dead", 120, 60);
    }
    public void update() throws IOException, InterruptedException {
        collisionOn = false;
//        gp.cChecker.checkTile(this);
        if(hasGun && !gp.bullet.launched) getGun();
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        gp.cChecker.checkObjectDemonic();
        gp.cChecker.checkObjectP();
        gp.cChecker.checkObjectT();
                if(state == attackState){

                    int mDist1 = (int) Math.abs(gp.monster.worldX - worldX);
                    int mDst2 = (int) Math.abs(gp.monster.worldY - worldY);
                    int monsterDistance = (int) Math.sqrt(mDist1*mDist1 + mDst2*mDst2);

                    int dist1 = (int) Math.abs(gp.obj[4].worldX - worldX);
                    int dist2 = (int) Math.abs(gp.obj[4].worldY - worldY);
                    int distance = (int) Math.sqrt(dist1*dist1 + dist2*dist2);

                    int sDist1 = (int) Math.abs(gp.obj[2].worldX + 96 - worldX);
                    int sDist2 = (int) Math.abs(gp.obj[2].worldY + 24 - worldY);
                    int sDistance = (int) Math.sqrt(sDist1*sDist1 + sDist2*sDist2);

                    int wDist1 = (int) Math.abs(gp.obj[3].worldX + 19 - worldX);
                    int wDist2 = (int) Math.abs(gp.obj[3].worldY + 7 - worldY);
                    int wDistance = (int) Math.sqrt(wDist1*wDist1 + wDist2*wDist2);

                    int bDist1 = (int) Math.abs(gp.obj[5].worldX + 12 - worldX);
                    int bDist2 = (int) Math.abs(gp.obj[5].worldY + 14 - worldY);
                    int bDistance = (int) Math.sqrt(bDist1*bDist1 + bDist2*bDist2);

                    if(direction == "up"){
                        direction = "attackUp";
                        if(gp.monster.worldY < worldY && monsterDistance <= 145 && gp.monster.state == gp.monster.defState){
                            gp.monster.state = gp.monster.attState;
                        }
                        if(gp.obj[4].attacked == 0){

                            if(gp.obj[4].worldY < worldY && distance<=199){
                                gp.obj[4].state = gp.obj[4].attackedState;
                                gp.obj[4].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 4;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[2].attacked == 0){
                            if(gp.obj[2].worldY < worldY && sDistance<=199){
                                gp.obj[2].state = gp.obj[2].attackedState;
                                gp.obj[2].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 2;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[3].attacked == 0){
                            if(gp.obj[3].worldY < worldY && wDistance<=199){
                                gp.obj[3].state = gp.obj[3].attackedState;
                                gp.obj[3].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 3;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[5].attacked == 0){
                            if(gp.obj[5].worldY < worldY && bDistance<=199){
                                gp.obj[5].state = gp.obj[5].attackedState;
                                gp.obj[5].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 5;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }

                    }
                    else if(direction == "down"){
                        direction = "attackDown";
                        if(gp.monster.worldY > worldY && monsterDistance <= 145 && gp.monster.state == gp.monster.defState){
                            gp.monster.state = gp.monster.attState;
                        }
                        if(gp.obj[4].attacked == 0) {
                            if (gp.obj[4].worldY > worldY && distance<=199) {
                                gp.obj[4].state = gp.obj[4].attackedState;
                                gp.obj[4].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 4;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[2].attacked == 0) {
                            if (gp.obj[2].worldY > worldY && sDistance<=199) {
                                gp.obj[2].state = gp.obj[2].attackedState;
                                gp.obj[2].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 2;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[3].attacked == 0) {
                            if (gp.obj[3].worldY > worldY && wDistance<=199) {
                                gp.obj[3].state = gp.obj[3].attackedState;
                                gp.obj[3].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 3;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[5].attacked == 0) {
                            if (gp.obj[5].worldY > worldY && bDistance<=199) {
                                gp.obj[5].state = gp.obj[5].attackedState;
                                gp.obj[5].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 5;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                    }
                    else if(direction == "left"){
                        direction = "attackLeft";
                        if(gp.monster.worldX < worldX && monsterDistance <= 145 && gp.monster.state == gp.monster.defState){
                            gp.monster.state = gp.monster.attState;
                        }
                        if(gp.obj[4].attacked == 0) {
                            if (gp.obj[4].worldX < worldX && distance<=199) {
                                gp.obj[4].state = gp.obj[4].attackedState;
                                gp.obj[4].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 4;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[2].attacked == 0) {
                            if (gp.obj[2].worldX < worldX && sDistance<=199) {
                                gp.obj[2].state = gp.obj[2].attackedState;
                                gp.obj[2].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 2;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[3].attacked == 0) {
                            if (gp.obj[3].worldX < worldX && wDistance<=199) {
                                gp.obj[3].state = gp.obj[3].attackedState;
                                gp.obj[3].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 3;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[5].attacked == 0) {
                            if (gp.obj[5].worldX < worldX && bDistance<=199) {
                                gp.obj[5].state = gp.obj[5].attackedState;
                                gp.obj[5].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 5;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                    }
                    else if(direction == "right"){
                        direction = "attackRight";
                        if(gp.monster.worldX > worldX && monsterDistance <= 145 && gp.monster.state == gp.monster.defState){
                            gp.monster.state = gp.monster.attState;
                        }
                        if(gp.obj[4].attacked == 0 && distance<=199) {
                            if (gp.obj[4].worldX > worldX) {
                                gp.obj[4].state = gp.obj[4].attackedState;
                                gp.obj[4].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 4;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[2].attacked == 0 && sDistance<=199) {
                            if (gp.obj[2].worldX > worldX) {
                                gp.obj[2].state = gp.obj[2].attackedState;
                                gp.obj[2].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 2;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[3].attacked == 0 && wDistance<=199) {
                            if (gp.obj[3].worldX > worldX) {
                                gp.obj[3].state = gp.obj[3].attackedState;
                                gp.obj[3].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 3;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                        if(gp.obj[5].attacked == 0 && bDistance<=199) {
                            if (gp.obj[5].worldX > worldX) {
                                gp.obj[5].state = gp.obj[5].attackedState;
                                gp.obj[5].attacked = 1;
                                banishedGhosts++;
                                gp.lastObject = 5;
                                if(banishedGhosts < 5) gp.photoGenerator();
                            }
                        }
                    }
            }
            if(state == deadState2){
                if(!played) {
                    TimeUnit.SECONDS.sleep(3);
                    gp.playMusicNoLoop2(22);
                    gp.gameState = gp.deadState;
                    played = true;
                }
            }
            if(state == defaultState){
                if(direction == "attackUp"){
                    direction = "up";
                }
                else if(direction == "attackDown"){
                    direction = "down";
                }
                else if(direction == "attackLeft"){
                    direction = "left";
                }
                else if(direction == "attackRight"){
                    direction = "right";
                }
                if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){

                    float mag = (float) Math.sqrt(speed*speed+speed*speed);
                    speed /= mag;
                    speed = speed*multiplier;
                    if(keyH.upPressed){
                        direction = "up";
                        if (collisionOn == false) worldY -= (speed);
                    }
                    if(keyH.downPressed){
                        direction = "down";
                        if (collisionOn == false) worldY += (speed);
                    }
                    if(keyH.rightPressed){
                        direction = "right";
                        if (collisionOn == false) worldX += (speed);
                    }
                    if(keyH.leftPressed){
                        direction = "left";
                        if (collisionOn == false) worldX -= (speed);
                    }
                    spriteCounter++;
                    if(spriteCounter > 17){
                        if(spriteNum == 1){
                            spriteNum = 2;
                        }
                        else if(spriteNum == 2){
                            spriteNum = 1;
                        }
                        spriteCounter = 0;
                    }
                }
            }
    }

    public void pickUpObject(int i){
        if(i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName){
                case "key":
                    hasKey++;
                    gp.obj[0] = null;
                    break;
                case "Gun":
                    hasGun = true;
                    break;
            }
        }
    }

    public void getGun(){
        if(direction == "right"){
            gp.obj[1].worldX = (int) worldX;
            gp.obj[1].worldY = (int) worldY+24;
        }
        if(direction == "left"){
            gp.obj[1].worldX = (int) worldX-20;
            gp.obj[1].worldY = (int) worldY+24;
        }
        if(direction == "up"){
            gp.obj[1].worldX = (int) worldX+16;
            gp.obj[1].worldY = (int) worldY-12;
        }
        if(direction == "down"){
            gp.obj[1].worldX = (int) worldX+17;
            gp.obj[1].worldY = (int) worldY+30;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
            case "attackUp":
                image = attackU;
                break;
            case "attackDown":
                image = attackDwn;
                break;
            case "attackLeft":
                image = attackLft;
                break;
            case "attackRight":
                image = attackRght;
                break;
        }
        if(state == deadState || state == deadState2) g2.drawImage(dead, screenX, screenY,null);
        else g2.drawImage(image, screenX, screenY,null);
    }
}
