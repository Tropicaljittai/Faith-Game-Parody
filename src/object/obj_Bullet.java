package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Bullet extends SuperObject{
    public int speed = 2;
    public boolean hit = false;
    public String direction;
    public boolean positionFound = false, directionFound = false;
    public boolean launched = false;
    public obj_Bullet(){

        name = "Bullet";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bullet.jpg"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/bullet2.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 9;
        solidArea.height = 3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void update(GamePanel gp){
        gp.cChecker.checkObjectF();
        gp.cChecker.checkObjectD();
        if(gp.player.hasGun && launched){
            move(gp);
        }
    }
    public void move(GamePanel gp){
        if(direction == "up" || direction == "down") {
            if(!positionFound){
                worldY = gp.obj[1].worldY;
                worldX = gp.obj[1].worldX;
                positionFound = true;
            }
            if(launched){
                if(direction == "up") worldY -= speed;
                if(direction == "down") worldY += speed;
            }
        }
        if(direction == "left" || direction == "right") {
            if(!positionFound){
                worldY = gp.obj[1].worldY+3;
                if(direction=="left") worldX = gp.obj[1].worldX-28;
                if(direction=="right") worldX = gp.obj[1].worldX+48;
                positionFound = true;
            }
            if(launched) {
                if (direction == "left") worldX -= speed;
                if (direction == "right") worldX += speed;
            }
        }
    }
}
