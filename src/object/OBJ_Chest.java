package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gamePanel;


    public OBJ_Chest(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.loot = loot;


        type = typeObstacle;
        name = "Chest";
        image = setup("/items/sprite_chestNew0", gamePanel.tileSize + 12, gamePanel.tileSize + 12);
        image2 = setup("/items/sprite_chestNew1", gamePanel.tileSize + 12, gamePanel.tileSize + 12);
        stay1 = image;
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void setLoot(Entity loot){
        this.loot = loot;
    }

    public void interact(){



        if(opened == false){

            dialogues[1][0]  = "It is closed, you need a key!";
            startDialogue(this, 1);




        }
        else{
            stay1 = image2;
        }
    }
}
