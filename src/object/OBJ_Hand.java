package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hand extends Entity {

    public OBJ_Hand(GamePanel gamePanel) {
        super(gamePanel);

        type = typeHand;

        name = "Hand";
        stay1 = setup("/items/sprite_mainHand0", gamePanel.tileSize, gamePanel.tileSize);
        direction = "stay";
        description = "< Hand >";


    }
}
