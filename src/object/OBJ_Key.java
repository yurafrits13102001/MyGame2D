package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {


    public OBJ_Key(String name, GamePanel gamePanel) {

        super(gamePanel);


        switch (this.name = name) {

            case "Key_Purple":
                stay1 = setup("/items/sprite_item0");
                collision = true;
                break;
            case "Key_Blue":
                stay1 = setup("/items/sprite_item1");
                collision = true;
                break;
            case "Key_Orange":
                stay1 = setup("/items/sprite_item2");
                collision = true;

        }
    }
}

