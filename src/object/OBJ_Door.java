package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {

    GamePanel gamePanel;

    public OBJ_Door(String name, GamePanel gamePanel) {

        super(gamePanel);

        switch (this.name = name) {

            case "Door_Purple":

                stay1 = setup("/items/sprite_item4");
                collision = true;
                solidArea.x = 0;
                solidArea.y = 16;
                solidArea.width = 32;
                solidArea.height = 32;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
                break;
            case "Door_Blue":
                up1 = setup("/items/sprite_item5");
                collision = true;
                solidArea.x = 0;
                solidArea.y = 32;
                solidArea.width = 32;
                solidArea.height = 32;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;

                break;
            case  "Door_Orange":
                stay1 = setup("/items/sprite_item6");
                collision = true;
                solidArea.x = 0;
                solidArea.y = 16;
                solidArea.width = 32;
                solidArea.height = 32;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;


        }
    }
}
