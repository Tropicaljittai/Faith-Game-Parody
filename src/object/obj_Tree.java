package object;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Tree extends SuperObject{
    public obj_Tree(){
        name = "Tree";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/tree.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 26;
        solidArea.y = 36;
        solidArea.width = 16;
        solidArea.height = 35;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
    }
}
