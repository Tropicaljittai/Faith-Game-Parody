package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SuperObject {
    public int attacked = 0;
    public int defState = 1;
    public int state;
    public int attackedState = 2;
    public BufferedImage image, image2, image3, image4;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle();
    public int objCounter = 0, objNum = 1, objStateCounter = 0;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public void drawFox(Graphics2D g2, GamePanel gp) throws IOException {
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.fox.attacked) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sacFox.png"));
            g2.drawImage(image, screenX, screenY, 15*3, 39, null);
        }
        else g2.drawImage(image, screenX, screenY, 39, 27, null);
    }
    public void drawTree(Graphics2D g2, GamePanel gp){
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        g2.drawImage(image, screenX, screenY, gp.tileSizeTree, gp.tileHeightTree, null);
    }

    public void drawGun(Graphics2D g2, GamePanel gp){
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.player.hasGun && gp.player.direction == "right") g2.drawImage(image, screenX, screenY, 45, 9, null);
        if(gp.player.hasGun && gp.player.direction == "left") g2.drawImage(image2, screenX, screenY, 45, 9, null);
        if(gp.player.hasGun && gp.player.direction == "up") g2.drawImage(image3, screenX, screenY, 3, 42, null);
        if(gp.player.hasGun && gp.player.direction == "down") g2.drawImage(image4, screenX, screenY, 3, 42, null);
        if(!gp.player.hasGun) g2.drawImage(image, screenX, screenY, 45, 9, null);

    }

    public void drawBullet(Graphics2D g2, GamePanel gp){
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.bullet.direction == "up" || gp.bullet.direction == "down") g2.drawImage(image, screenX, screenY, 3, 6, null);
        if(gp.bullet.direction == "left" || gp.bullet.direction == "right") g2.drawImage(image2, screenX, screenY, 6, 3, null);
    }

    public void drawShed(Graphics2D g2, GamePanel gp){
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        g2.drawImage(image, screenX, screenY, gp.tileSizeShed*3, gp.tileHeightShed*3, null);
    }

    public void drawCar(Graphics2D g2, GamePanel gp){
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        g2.drawImage(image, screenX, screenY, gp.carWidth, gp.carHeight, null);
    }

    public void drawKey(Graphics2D g2, GamePanel gp){

        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        g2.drawImage(image, screenX, screenY, gp.tileSizeKey, gp.tileHeightKey, null);
    }

    public void drawPhoto(Graphics2D g2, GamePanel gp){

        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        g2.drawImage(image, screenX, screenY, 9, 12, null);
    }

    public void drawTruck(Graphics2D g2, GamePanel gp){
        objCounter++;
        if(gp.gameState != gp.deadState){
            if(objCounter > 10){
                if(objNum == 1){
                    objNum = 2;
                }
                else if(objNum == 2){
                    objNum = 1;
                }
                objCounter = 0;
            }
        }
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(objNum == 1) g2.drawImage(image, screenX, screenY, 118*3, 34*3, null);
        if(objNum == 2) g2.drawImage(image2, screenX, screenY, 118*3, 34*3, null);
    }

    public void drawWell(Graphics2D g2, GamePanel gp){
        objCounter++;
        if(objCounter > 5){
            if(objNum == 1){
                objNum = 2;
            }
            else if(objNum == 2){
                objNum = 1;
            }
            objCounter = 0;
        }
        if (gp.obj[4].state == gp.obj[4].attackedState)objStateCounter++;
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.obj[4].state == gp.obj[4].attackedState){
            if(objNum == 2) g2.drawImage(image, screenX, screenY, gp.tileSizeWell, gp.tileHeightWell, null);
            if(objNum == 1) g2.drawImage(image2, screenX, screenY, gp.tileSizeWell, gp.tileHeightWell, null);
            if(objStateCounter > 100) {
                gp.ghost.state = gp.ghost.wellState;
                objStateCounter = 0;
                gp.obj[4].state = gp.obj[4].defState;
            }
        }
        else g2.drawImage(image, screenX, screenY, gp.tileSizeWell, gp.tileHeightWell, null);


    }

    public void drawStones(Graphics2D g2, GamePanel gp){
        objCounter++;
        if(objCounter > 5){
            if(objNum == 1){
                objNum = 2;
            }
            else if(objNum == 2){
                objNum = 1;
            }
            objCounter = 0;
        }
        if (gp.obj[2].state == gp.obj[2].attackedState)objStateCounter++;
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.obj[2].state == gp.obj[2].attackedState){
            if(objNum == 2) g2.drawImage(image, screenX, screenY, gp.stonesWidth*2, gp.stonesHeight*2, null);
            if(objNum == 1) g2.drawImage(image2, screenX, screenY, gp.stonesWidth*2, gp.stonesHeight*2, null);
            if(objStateCounter > 100) {
                gp.ghost.state = gp.ghost.stonesState;
                objStateCounter = 0;
                gp.obj[2].state = gp.obj[2].defState;
            }
        }
        else g2.drawImage(image, screenX, screenY, gp.stonesWidth*2, gp.stonesHeight*2, null);


    }

    public void drawWater(Graphics2D g2, GamePanel gp){
        objCounter++;
        if(objCounter > 5){
            if(objNum == 1){
                objNum = 2;
            }
            else if(objNum == 2){
                objNum = 1;
            }
            objCounter = 0;
        }
        if (gp.obj[3].state == gp.obj[3].attackedState)objStateCounter++;
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.obj[3].state == gp.obj[3].attackedState){
            if(objNum == 2) g2.drawImage(image, screenX, screenY, gp.tileSizeWater*3, gp.tileHeightWater*3, null);
            if(objNum == 1) g2.drawImage(image2, screenX, screenY, gp.tileSizeWater*3, gp.tileHeightWater*3, null);
            if(objStateCounter > 100) {
                gp.ghost.state = gp.ghost.waterState;
                objStateCounter = 0;
                gp.obj[3].state = gp.obj[3].defState;
            }
        }
        else g2.drawImage(image, screenX, screenY, gp.tileSizeWater*3, gp.tileHeightWater*3, null);
    }

    public void drawBones(Graphics2D g2, GamePanel gp){
        objCounter++;
        if(objCounter > 5){
            if(objNum == 1){
                objNum = 2;
            }
            else if(objNum == 2){
                objNum = 1;
            }
            objCounter = 0;
        }
        if (gp.obj[5].state == gp.obj[5].attackedState)objStateCounter++;
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(gp.obj[5].state == gp.obj[5].attackedState){
            if(objNum == 2) g2.drawImage(image, screenX, screenY, gp.tileSizeBones, gp.tileHeightBones, null);
            if(objNum == 1) g2.drawImage(image2, screenX, screenY, gp.tileSizeBones, gp.tileHeightBones, null);
            if(objStateCounter > 100) {
                gp.ghost.state = gp.ghost.bonesState;
                objStateCounter = 0;
                gp.obj[5].state = gp.obj[5].defState;
            }
        }
        else g2.drawImage(image, screenX, screenY, gp.tileSizeBones, gp.tileHeightBones, null);
    }

    public void drawSac(Graphics2D g2, GamePanel gp) throws IOException {
        if(gp.player.banishedGhosts == 1) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sac1_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/sac1_2.png"));
        }
        if(gp.player.banishedGhosts == 2) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sac2_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/sac2_2.png"));
        }
        if(gp.player.banishedGhosts == 3) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sac3_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/sac3_2.png"));
        }
        if(gp.player.banishedGhosts == 4) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sac4_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/sac4_2.png"));
        }
        if(gp.player.banishedGhosts == 5) {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sac.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/sac2.png"));
        }

        objCounter++;
        if(objCounter > 12){
            if(objNum == 1){
                objNum = 2;
            }
            else if(objNum == 2){
                objNum = 1;
            }
            objCounter = 0;
        }

        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        if(objNum == 2) g2.drawImage(image, screenX, screenY, gp.tileSizeSac, gp.tileHeightSac, null);
        if(objNum == 1) g2.drawImage(image2, screenX, screenY, gp.tileSizeSac, gp.tileHeightSac, null);
    }
}
