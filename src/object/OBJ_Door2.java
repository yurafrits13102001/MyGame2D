package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door2 extends Entity {

    GamePanel gamePanel;
    boolean opened = false;

    public OBJ_Door2( GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        type = typeObstacle;
        name = "Door2";
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
        dialogues[1][0] = "You have to free the old wizard\n to go there!";
    }

    @Override
    public void interact(){

        if(opened == false) {

            startDialogue(this, 0);
        }
        if(opened == false && speakWithOldMan == false){

            startDialogue(this, 1);

        }else{
            stay1 = image2;
        }
    }
}
