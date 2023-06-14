package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Struct;
import java.util.Random;


public class m_Capybara extends Entity {
    public int repel = 0;
    BufferedImage dead;
    public boolean spawned, killedPlayer, repeled;
    public int state;
    public boolean positionSet = false;
    public int defState = 1;
    public int sleepState = 3;
    public int dist1, dist2;
    public int attState = 2;
    public int endState = 4;
    public int deadState = 10;
    public m_Capybara(){
        worldY = 9000; //outside of screen
        worldX = 9000;
        speed = 4;
        state = sleepState;
        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 4;
        solidArea.width = 12;
        solidArea.height = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        direction = "up";

        get_image();
    }

    public void get_image(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monsterimg/capybara2.png"));
            dead = ImageIO.read(getClass().getResourceAsStream("/monsterimg/dead.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public float findAngle(GamePanel gp){
        int diffX = (int) (gp.player.worldX - worldX - 10);
        int diffY = (int) (gp.player.worldY - worldY + 11);

        float angle = (float)Math.atan2(diffY, diffX);
        return angle;
    }
    public void set_action(GamePanel gp){


        float angle = (findAngle(gp));

        worldX += speed * Math.cos(angle)+(0.2*repel);
        worldY += speed * Math.sin(angle)+(0.2*repel);
        if((Math.abs(gp.player.worldX - worldX) <=9 && Math.abs(gp.player.worldY - worldY) <=12) && !killedPlayer){
            killedPlayer(gp);
        }
    }

    public void killedPlayer(GamePanel gp){

        speed = 0;
        gp.player.state = gp.player.deadState;
        if(!gp.monster.killedPlayer) {
            gp.stopMusic();
            gp.playMusicNoLoop(2);
        }
        killedPlayer = true;
    }

    public void end(GamePanel gp){

        worldX += speed * Math.cos(1);
        worldY += speed * Math.sin(1);
    }

    public void set_Run(GamePanel gp){


        float angle = (findAngle(gp));

        worldX -= (speed * Math.cos(angle))*1.1;
        worldY -= (speed * Math.sin(angle))*1.1;

        dist1 = (int) Math.abs(gp.player.worldX - worldX);
        dist2 = (int) Math.abs(gp.player.worldY - worldY);
        if(!repeled && state == attState){
            Random r = new Random();
            int j = 12 + r.nextInt(7) ;
            gp.playMusicNoLoop2(j);
            repeled = true;
        }

        if(Math.sqrt(dist1*dist1+dist2*dist2) >= 520) {
            repel++;
            state = sleepState;
            positionSet = false;
            repeled = false;
            gp.playMusic();
        }
    }
    public void update(GamePanel gp){
        gp.cChecker.checkObjectM();
        if(gp.player.hasGun && !gp.bullet.launched){
            solidArea.x = 0;
            solidArea.y = 0;
            solidArea.width = gp.tileSizeMonster*3;
            solidArea.height = gp.tileHeightMonster*3;
            if(Math.abs(gp.obj[7].worldX - gp.player.worldX) <= 100 && Math.abs(gp.obj[7].worldY - gp.player.worldY) <= 100) {
                if(!positionSet){
                    Random r = new Random();
                    int j = r.nextInt(10) + 1;
                    gp.stopMusic();
                    gp.playMusicNoLoop2(j);
                    worldX = gp.player.worldX - 320;
                    worldY = gp.player.worldY + gp.tileSizeRes;
                    positionSet = true;
                    state = endState;
                }
            }
        }
        if(gp.bullet.hit && state == endState){
            end(gp);
        }
        else if(state == endState){
            set_action(gp);
        }
        collisionOn = false;
        if(state == defState) set_action(gp);
        else if (state == attState) set_Run(gp);

//            double mag = Math.sqrt(speed*speed+speed*speed);
//            speed = speed == 0 ? 0 : speed / mag;
//
//            if(direction == "up"){
//                if (collisionOn == false) worldY -= speed;
//            }
//            if(direction == "down"){
//                if (collisionOn == false) worldY += speed;
//            }
//            if(direction == "right"){
//                if (collisionOn == false) worldX += speed;
//            }
//            if(direction == "left"){
//                if (collisionOn == false) worldX -= speed;
//            }
            if(!killedPlayer){
                spriteCounter++;

                if(spriteCounter > 6){
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
    public void draw(Graphics2D g2, GamePanel gp){
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
        }
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(state == deadState) g2.drawImage(dead, screenX, screenY, 50*3, 14*3, null);
        else g2.drawImage(image, screenX, screenY, gp.tileSizeMonster*3, gp.tileHeightMonster*3, null);
    }
}
