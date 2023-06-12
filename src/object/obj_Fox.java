package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Fox extends SuperObject{
    GamePanel gp;
    public boolean attacked = false;
    public obj_Fox(GamePanel gp){
        this.gp = gp;
        name = "Fox";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/fox.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        worldX = 47*gp.tileSizeRes;
        worldY = 42*gp.tileSizeRes;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 39;
        solidArea.height = 27;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
