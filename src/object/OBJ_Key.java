package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {


    public OBJ_Key( GamePanel gamePanel) {

        super(gamePanel);
        type = typeKey;
        name = "Key";
        stay1 = setup("/items/sprite_newKey0", gamePanel.tileSize, gamePanel.tileSize);
        collision = true;
        description = " < Key >" + "\n to open the doors";



    }
}

