package interactiveTiles;

import entity.Entity;
import main.GamePanel;

public class IT_Grass extends InteractiveTile{

    GamePanel gamePanel;

    public IT_Grass(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = typeGrass;

        destructible = true;
        collision = true;
        name = "Grass";


//        solidArea.x = 0;
//        solidArea.y = 16;
//        solidArea.width = 32;
//        solidArea.height = 32;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;


        stay1 = setup("/background/interactiveTiles/sprite_weeds0", gamePanel.tileSize, gamePanel.tileSize);
        image = setup("/background/interactiveTiles/sprite_weeds0", gamePanel.tileSize, gamePanel.tileSize);

    }


}
