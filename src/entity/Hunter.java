package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hunter extends Entity{
    public boolean found = false;
    int dist1;
    int dist2 ;
    int distance;
    GamePanel gp;
    public int defState = 1;
    public int hunterState = 3;
    public int sleepState = 2;
    public int state = defState;
    public Hunter(GamePanel gp) throws IOException {
        this.gp = gp;
        worldX = 56*gp.tileSizeRes; //57
        worldY = 22*gp.tileSizeRes; //22
        speed = 3.5F;
        getImage();
    }

    public void getImage() throws IOException {
        right1 = ImageIO.read(getClass().getResourceAsStream("/player/hunterRight1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/player/hunterRight2.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/player/hunterDed.png"));
    }

    public void chaseDeer(){
        worldX += speed;

        distance = (int) Math.sqrt(dist1*dist1 + dist2*dist2);

        if(distance >= 1000) {
            gp.obj[1].worldX = 17*gp.tileSizeRes;
            gp.obj[1].worldY = 17*gp.tileSizeRes;
            found = true;
            state = sleepState;
        }
    }

    public void update(){
        dist1 = (int) Math.abs(gp.player.worldX - worldX);
        dist2 = (int) Math.abs(gp.player.worldY - worldY);
        distance = (int) Math.sqrt(dist1*dist1 + dist2*dist2);

        spriteCounter++;

        if(distance <= 240 && state == defState) {
            state = hunterState;
        }
        if(state == hunterState){
            gp.deer2.state = gp.deer2.hunterState;
            chaseDeer();
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

        if(state == hunterState && !found){
            BufferedImage image = null;
            if (spriteNum == 1){
                image = right1;
            }
            if (spriteNum == 2){
                image = right2;
            }
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, 45, 57, null);
        }

        if(state == defState && !found){
            BufferedImage image = null;
            image= right1;
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, 45, 57, null);
        }

        if(found){
            worldX = 16*gp.tileSizeRes;
            worldY = 16*gp.tileSizeRes;
            BufferedImage image = null;
            image= left1;
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, 36*3, 60, null);
        }

    }
}
