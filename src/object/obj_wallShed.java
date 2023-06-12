package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class obj_wallShed extends SuperObject {

    public obj_wallShed(){
        name = "Walls";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/wallshed.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 0;
        solidArea.y = 125;
        solidArea.width = 96*3;
        solidArea.height = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
    }
}
