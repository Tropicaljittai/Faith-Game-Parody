package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DemonicDeer extends Entity{
    public boolean killedPlayer = false;
    public boolean positionFound = false;
    public int defState = 1;
    public int sleepState = 2;
    public int state;
    GamePanel gp;
    public BufferedImage run1, run2;
    public DemonicDeer(GamePanel gp) throws IOException {
        this.gp = gp;
        speed = 3F;
        state = sleepState;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 8*3;
        solidArea.height = 29*3;

        getImage();
    }

    public void getImage() throws IOException {
        run1 = ImageIO.read(getClass().getResourceAsStream("/objects/demonDeer2.png"));
        run2 = ImageIO.read(getClass().getResourceAsStream("/objects/demonDeer.png"));
    }
    public void attackPlayer(){
        gp.stopMusic();
        int diffX = (int) (gp.player.worldX - worldX - 10);
        int diffY = (int) (gp.player.worldY - worldY + 11);

        float angle = (float)Math.atan2(diffY, diffX);

        worldX += (speed * Math.cos(angle))*1.3;
        worldY += (speed * Math.sin(angle))*1.3;
    }

    public void update(){

        if(state == defState) {
            if(!positionFound){
                gp.playMusicNoLoop2(24);
                worldX = gp.player.worldX + gp.tileSizeRes*8;
                worldY = gp.player.worldY;
                positionFound = true;
            }
            attackPlayer();
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
    public void killedPlayer(GamePanel gp){

        gp.ending = true;
        gp.gameState = gp.  deerEnding;
        speed = 0;
        gp.player.state = gp.player.deadState;
        if(!killedPlayer) {
            gp.stopMusic();
            gp.playMusicNoLoop(2);
        }
        killedPlayer = true;
    }


    public void draw(Graphics2D g2, GamePanel gp){
        BufferedImage image = null;
        if(state == defState) {

             if (spriteNum == 1) {
                    image = run1;
                }
                if (spriteNum == 2) {
                    image = run2;
                }

            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, 18*3, 29*3, null);
        }
    }
}
