package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PotionMana extends Entity {
    public OBJ_PotionMana(String name, GamePanel gamePanel) {
        super(gamePanel);



        type = typePotion;

        this.name = name;

        stay1 = setup("/items/sprite_potionNew0", gamePanel.tileSize, gamePanel.tileSize);

        collision = true;
        description = "< Mana potion > \n Restore full mana";

    }
}
