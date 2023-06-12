package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class obj_Water extends SuperObject {

    public obj_Water(){
        state = defState;
        attacked = 0;
        name = "Water";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/water.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/wateryellow.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
