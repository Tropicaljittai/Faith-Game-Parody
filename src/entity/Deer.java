package entity;

import main.GamePanel;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Deer extends Entity{
    public int attacked = 0;
    public int spriteCounter2 = 0;
    public int hunterState2 = 5;
    public int spriteNum2 = 1;
    public int width, height;
    public int defState = 1;
    public int attackedState = 2;
    public int hunterState = 4;
    public int state;
    public int sleepState = 3;
    public int deadState = 6;

    GamePanel gp;
    public BufferedImage idle1, idle2, run1, run2, run3, run4, dead;
    public int dist1, dist2, distance;
    public Deer(GamePanel gp) throws IOException {
        this.gp = gp;
        worldX = 37*gp.tileSizeRes; //37
        worldY = 53*gp.tileSizeRes; //53
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.deerWidth;
        solidArea.height = gp.deerHeight;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        speed = 2.5F;
        state = defState;
        getImage();
    }

    public Deer(GamePanel gp, int i) throws IOException {
        this.gp = gp;
        worldX = 59*gp.tileSizeRes;
        worldY = 22*gp.tileSizeRes;
        speed = 3.5F;
        state = hunterState2;
        getImage();
    }

    public void getImage() throws IOException {
        idle1 = ImageIO.read(getClass().getResourceAsStream("/player/deerIdle1.png"));
        idle2 = ImageIO.read(getClass().getResourceAsStream("/player/deerIdle2.png"));
        run1 = ImageIO.read(getClass().getResourceAsStream("/player/deerRun1.png"));
        run2 = ImageIO.read(getClass().getResourceAsStream("/player/deerRun2.png"));
        run3 = ImageIO.read(getClass().getResourceAsStream("/player/deerRun1_right.png"));
        run4 = ImageIO.read(getClass().getResourceAsStream("/player/deerRun2_right.png"));
        dead = ImageIO.read(getClass().getResourceAsStream("/objects/deadDeer.png"));
    }
    public void runAway(){
        attacked+=1;
        int diffX = (int) (gp.player.worldX - worldX - 10);
        int diffY = (int) (gp.player.worldY - worldY + 11);

        float angle = (float)Math.atan2(diffY, diffX);

        worldX -= (speed * Math.cos(angle))*1.3;
        worldY -= (speed * Math.sin(angle))*1.3;

        distance = (int) Math.sqrt(dist1*dist1 + dist2*dist2);

        if(distance >= 500 && state == attackedState) {
            state = sleepState;
        }
    }

    public void hunterChase(){
       worldX += speed;

        if(distance >= 1000 && state == hunterState) {
            state = sleepState;
        }
    }
    public void update(){
        dist1 = (int) Math.abs(gp.player.worldX - worldX);
        dist2 = (int) Math.abs(gp.player.worldY - worldY);
        distance = (int) Math.sqrt(dist1*dist1 + dist2*dist2);
        if(attacked > 0 && state == sleepState && gp.player.hasGun){
            state = defState;
            worldX = 50*gp.tileSizeRes;
            worldY = 50*gp.tileSizeRes;
        }
        if(state == deadState){
            gp.demonDeer.state = gp.demonDeer.defState;
        }
        if(state == defState) {
            if(attacked == 0){
                if(distance <= 145) state = attackedState;
            }
            else {
                if(distance <= 100) state = attackedState;
            }
        }

        if(distance <= 180 && state == hunterState2) {
            state = hunterState;
        }
        if(state == attackedState) runAway();
        if(state == hunterState) {
            gp.hunter.state = gp.hunter.hunterState;
            hunterChase();
        }
        spriteCounter++;
        spriteCounter2++;

        if(spriteCounter2 > 70){
            if(spriteNum2 == 1){
                spriteNum2 = 2;
            }
            else if(spriteNum2 == 2){
                spriteNum2 = 3;
            }
            else if(spriteNum2 == 3){
                spriteNum2 = 4;
            }
            else if(spriteNum2 == 4){
                spriteNum2 = 5;
            }
            else if(spriteNum2 == 5){
                spriteNum2 = 1;
            }
            spriteCounter2 = 0;
        }

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

    public void draw(Graphics2D g2, GamePanel gp){
        if(state == defState || state == hunterState2){
            width = gp.deerWidth;
            height = gp.deerHeight;
            BufferedImage image = null;
            if (!(spriteNum2 == 5)){
                image = idle1;
            }
            if (spriteNum2 == 5){
                image = idle2;
            }
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, width, height, null);
        }

        if(state == attackedState){
            BufferedImage image = null;
            if(gp.player.worldX <= worldX){
                if (spriteNum == 1){
                    image = run3;
                }
                if (spriteNum == 2){
                    image = run4;
                }
            }

            if(gp.player.worldX >= worldX){
                if (spriteNum == 1){
                    image = run1;
                }
                if (spriteNum == 2){
                    image = run2;
                }
            }

            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, gp.deerRunWidth, gp.deerRunHeight, null);
        }

        if(state == hunterState){
            BufferedImage image = null;
            if (spriteNum == 1){
                image = run3;
            }
            if (spriteNum == 2){
                image = run4;
            }

            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, gp.deerRunWidth, gp.deerRunHeight, null);
        }

        if(state == deadState){
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(dead, screenX, screenY, 27*3, 15*3, null);
        }

    }
}
