package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {


    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);

        type = typeAxe;

        name = "Axe";
        stay1 = setup("/items/sprite_axe0", gamePanel.tileSize, gamePanel.tileSize);
        direction = "stay";
        description = "< Axe >";


    }
}
