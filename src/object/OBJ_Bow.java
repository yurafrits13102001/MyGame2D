package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow extends Entity {

    public OBJ_Bow(GamePanel gamePanel) {
        super(gamePanel);

        type = typeBow;
        name = "Bow";
        stay1 = setup("/items/sprite_bowModel0", gamePanel.tileSize, gamePanel.tileSize);
        direction = "stay";
        collision = true;
        description = "< Bow >";

    }
}
