package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Apple extends Entity {

    public OBJ_Apple(String name ,GamePanel gamePanel) {

        super(gamePanel);

        this.name = name;

        imageApple = setup("/items/sprite_apple0");

        collision = true;



    }
}
