package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class obj_Stones extends SuperObject {

    public obj_Stones(){
        state = defState;
        attacked = 0;
        name = "Stones";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/stones.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/stones2.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
