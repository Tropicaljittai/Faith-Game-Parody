package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_Photo extends SuperObject{
    GamePanel gp;
    public int foundState = 5;
    public obj_Photo(GamePanel gp){
        this.gp = gp;
        name = "Photo";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/photo.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 9;
        solidArea.height = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
