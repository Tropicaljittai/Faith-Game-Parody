package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ghost extends Entity{
    public int defState = 0;
    public int wellState = 1;
    public int stonesState = 2;
    public int waterState = 3;
    public int bonesState = 5;

    public int state;
    public int drawStateCounter;
    public boolean positionSet;
    BufferedImage image;
    GamePanel gp;

    public Ghost(GamePanel gp) throws IOException {
        this.gp = gp;
        speed = 0.4F;
        getImage();
    }

    public void getImage() throws IOException {
        up1 = ImageIO.read(getClass().getResourceAsStream("/player/ghost.png"));
    }


    public void update(){
        worldY -= speed;
        spriteCounter++;

        if(spriteCounter > 4){
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
        if(state == wellState || state == stonesState || state == waterState || state == bonesState) drawStateCounter++;

        BufferedImage image = up1;

        if(drawStateCounter > 100){
            if (spriteNum == 1){
                image = up1;
            }
            if (spriteNum == 2){
                image = null;
            }
            if(drawStateCounter>150) {
                state = defState;
                drawStateCounter = 0;
                positionSet = false;
            }
        }



        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        g2.drawImage(image, screenX, screenY, gp.ghostWidth*3, gp.ghostHeight*3, null);
    }
}
