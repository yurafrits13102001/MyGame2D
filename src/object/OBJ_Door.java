package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{

    GamePanel gamePanel;

    public OBJ_Door(String name, GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        switch (this.name = name) {

            case "Door_Purple":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item4.png"));
                    utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;
                break;
            case "Door_Blue":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item5.png"));
                    utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;

                break;
            case  "Door_Orange":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item6.png"));
                    utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;


        }
    }
}
