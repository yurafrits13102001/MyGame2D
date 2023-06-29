package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {

    GamePanel gamePanel;
    boolean opened = false;

    public OBJ_Door( GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

                type = typeObstacle;
                name = "Door";
                image = setup("/items/sprite_mewDoor0", gamePanel.tileSize, gamePanel.tileSize);
                image2 = setup("/items/sprite_mewDoor1", gamePanel.tileSize, gamePanel.tileSize);
                stay1 = image;
                opened = false;
                collision = true;
                solidArea.x = 0;
                solidArea.y = 16;
                solidArea.width = 48;
                solidArea.height = 25;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
                openFirst = false;

                setDialogue();


    }

    public void setDialogue(){

        dialogues[0][0] = "You need a key to open this!";
    }

    @Override
    public void interact(){

        if(opened == false) {

           startDialogue(this, 0);
           speakWithOldMan = true;
        }
        else {
            stay1 = image2;

        }
    }
}
