package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class obj_Sac extends SuperObject {
    public obj_Sac(GamePanel gp){

        state = defState;
        attacked = 0;
        name = "Sac";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sac0.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/sac0.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
