package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {


    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);

        name = "Axe";
        down1 = setup("/items/sprite_axe0");
        description = "[Axe]";

    }
}
