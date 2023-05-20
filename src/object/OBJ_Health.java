package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Health extends Entity {

    GamePanel gamePanel;

    public OBJ_Health(GamePanel gamePanel){


        super(gamePanel);

        name = "Health";

        image = setup("/items/sprite_health0", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/items/sprite_health1", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/items/sprite_health2", gamePanel.tileSize, gamePanel.tileSize);


        collision = true;
    }

}
