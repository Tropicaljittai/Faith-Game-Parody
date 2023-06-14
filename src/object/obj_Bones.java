package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class obj_Bones extends SuperObject {

    public obj_Bones(){
        state = defState;
        attacked = 0;
        name = "Bones";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bones.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/bonesYellow.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
