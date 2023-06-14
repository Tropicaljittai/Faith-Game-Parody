package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Truck extends SuperObject{
    public boolean positionFound = false;
    GamePanel gp;
    public int speed = 7;
    public obj_Truck(GamePanel gp){
        this.gp = gp;
        name = "Truck";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/truck.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/truck2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        worldX = 1*gp.tileSizeRes;
        worldY = 90*gp.tileSizeRes;
        solidArea.x = 0;
        solidArea.y = 17*3;
        solidArea.width = 118*3;
        solidArea.height = 17*3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
    }

    public void update(){
        if(!gp.player.hasGun){
            worldX += speed;
            if(worldX >= 88*gp.tileSizeRes){
                worldX = -11*gp.tileSizeRes;
            }
        }
        if(gp.player.hasGun && gp.monster.state == gp.monster.endState || gp.monster.state == gp.monster.deadState){
            gp.cChecker.checkObjectTE();
            if(!positionFound && gp.monster.worldY >= 91*gp.tileSizeRes){
                worldX = (int) (gp.monster.worldX - (11*gp.tileSizeRes));
                positionFound = true;
            }
            if(gp.monster.worldY >= 91*gp.tileSizeRes) worldX += speed;
            if(Math.abs(worldX - gp.player.worldX) >= 800 && gp.monster.state == gp.monster.deadState) {
                gp.gameState = gp.goodEnd;
            }
        }
        if(gp.fox.attacked){
            worldX = -10000;
            worldY = -10000;
        }
    }
}
