package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{

    public OBJ_Door(String name) {

        switch (this.name = name) {

            case "Door_Purple":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item4.png"));

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;
                break;
            case "Door_Blue":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item5.png"));

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;

                break;
            case  "Door_Orange":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item6.png"));

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;


        }
    }
}
