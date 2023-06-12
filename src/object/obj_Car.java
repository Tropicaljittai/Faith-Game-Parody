package object;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Car extends SuperObject{
    public obj_Car(){
        name = "Car";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/car.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.y = 18;
        solidArea.width = 56*3;
        solidArea.height = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
    }
}
