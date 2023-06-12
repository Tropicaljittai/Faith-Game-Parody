package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class obj_Gun extends SuperObject{
    public obj_Gun(){
        name = "Gun";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gun.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/gun_Left.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/gun_Up.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/objects/gun_Down.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 45;
        solidArea.height = 9;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
