package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Key extends SuperObject{
    GamePanel gp;
    public obj_Key(){
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 7;
        solidArea.y = 0;
        solidArea.width = 1;
        solidArea.height = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
