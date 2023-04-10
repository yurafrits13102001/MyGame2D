package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {

    GamePanel gamePanel;

    public OBJ_Key(String name, GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        switch (this.name = name) {

            case "Key_Purple":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item0.png"));
                    utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;
                break;
            case "Key_Blue":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item1.png"));
                    utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;
                break;
            case  "Key_Orange":
                try {

                    image = ImageIO.read(getClass().getResourceAsStream("/items/sprite_item2.png"));
                    utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

                } catch (IOException e) {
                    e.printStackTrace();

                }
                collision = true;

        }
    }
}

