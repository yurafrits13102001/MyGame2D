package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key2 extends Entity {

    GamePanel gamePanel;


    public OBJ_Key2( GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;
        type = typeKey;
        name = "Key for chest";
        stay1 = setup("/items/sprite_chestKey0", gamePanel.tileSize, gamePanel.tileSize);
        coin1 = setup("/items/sprite_chestKey0", gamePanel.tileSize, gamePanel.tileSize);
        coin2 = setup("/items/sprite_chestKey1", gamePanel.tileSize, gamePanel.tileSize);
        coin3 = setup("/items/sprite_chestKey2", gamePanel.tileSize, gamePanel.tileSize);
        collision = true;
        description = " < Key >" + "\n to open the chest";

        setDialogue();
        setAction();



    }

    public void setAction(){
        spriteCounter++;
        if (spriteCounter <= 12) {
            stay1 = coin1;
        } else if (spriteCounter > 10 && spriteCounter <= 20) {
            stay1 = coin2;
        } else if (spriteCounter > 20 && spriteCounter <= 30) {
            stay1 = coin3;
        } else if (spriteCounter > 30 && spriteCounter <= 40) {
            stay1 = coin2;

            spriteCounter %= 40;
        }
    }


    public void update(){
        setAction();
    }

    public void setDialogue(){

        dialogues[0][0] = "You opened the chest \n and obtain the KEY!!!";
        dialogues[1][0] = "What are you doing?)))";
    }


    public boolean use(Entity entity){


        String targetName = "Chest";

        int objIndex = getDetected(entity, gamePanel.obj, targetName);

        if(objIndex != 999){
            startDialogue(this, 0);
            gamePanel.playSound(15);
            gamePanel.obj[objIndex].opened = true;
            gamePanel.obj[objIndex].stay1 = gamePanel.obj[objIndex].image2;
            gamePanel.obj[objIndex].collision = true;
            gamePanel.player.inventory.add(new OBJ_Key(gamePanel));
            return true;
        }
        else{
            startDialogue(this, 1);
            return false;
        }

    }
}
