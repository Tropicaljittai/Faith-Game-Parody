package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class obj_Well extends SuperObject {

    public obj_Well(){
        state = defState;
        attacked = 0;
        name = "Well";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/well.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/well2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 0;
        solidArea.y = 6;
        solidArea.width = 62;
        solidArea.height = 17;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;


    }
}
