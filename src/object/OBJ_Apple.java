package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Apple extends Entity {

    public OBJ_Apple(String name ,GamePanel gamePanel) {

        super(gamePanel);

        type = typeApple;

        this.name = name;

        stay1 = setup("/items/sprite_apple0", gamePanel.tileSize, gamePanel.tileSize);

        collision = true;
        description = "< Apple > \n Restore full health";



    }
}
