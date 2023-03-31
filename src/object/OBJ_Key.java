package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {

    public OBJ_Key(String name) {

        switch (this.name = name) {

            case "Key_Purple":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item0.png"));

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;
                break;
            case "Key_Blue":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item1.png"));

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;
                break;
            case  "Key_Orange":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item2.png"));

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;

        }
    }
}

