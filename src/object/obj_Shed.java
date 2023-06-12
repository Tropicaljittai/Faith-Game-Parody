package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Shed extends SuperObject{
    public obj_Shed(){
        name = "Shed";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shed2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 6;
        solidArea.y = 38;
        solidArea.width = 86;
        solidArea.height = 65;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
    }
}
