package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cultist extends Entity{
    public boolean positionSet = false;
    public boolean killedPlayer = false;
    GamePanel gp;

    public Cultist(GamePanel gp) throws IOException {
        speed = 3;
        this.gp = gp;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 27;
        solidArea.height = 66;

        getImage();
    }

    public void getImage() throws IOException {
        right1 = ImageIO.read(getClass().getResourceAsStream("/objects/cultist.png"));
    }

    public void attackPlayer(){
        int diffX = (int) (gp.player.worldX - worldX - 10);
        int diffY = (int) (gp.player.worldY - worldY + 11);

        float angle = (float)Math.atan2(diffY, diffX);

        worldX += (speed * Math.cos(angle))*1.1;
        worldY += (speed * Math.sin(angle))*1.1;
    }

    public void update(){
        gp.cChecker.checkObjectC();
        if(gp.fox.attacked){
            if(gp.player.worldY >= 89*gp.tileSizeRes) {
                if(!positionSet){
                    gp.stopMusic();
                    worldX = gp.player.worldX;
                    worldY = gp.player.worldY - 270;
                    positionSet = true;
                    gp.playMusicNoLoop2(26);
                }
                attackPlayer();
            }
        }
    }

    public void draw(Graphics2D g2, GamePanel gp){
            BufferedImage image = right1;


            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            g2.drawImage(image, screenX, screenY, 27, 66, null);
        }
    }
