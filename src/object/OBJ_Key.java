package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {

    GamePanel gamePanel;



    public OBJ_Key(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;
        type = typeKey;
        name = "Key for doors";
        stay1 = setup("/items/sprite_newKey0", gamePanel.tileSize, gamePanel.tileSize);
        coin1 = setup("/items/sprite_keyNew0", gamePanel.tileSize, gamePanel.tileSize);
        coin2 = setup("/items/sprite_keyNew1", gamePanel.tileSize, gamePanel.tileSize);
        coin3 = setup("/items/sprite_keyNew2", gamePanel.tileSize, gamePanel.tileSize);
        collision = true;
        description = " < Key >" + "\n to open the doors";

        setDialogue();
        setAction();


    }

    public void setAction() {
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


    public void update() {
        setAction();
    }

    public void setDialogue() {

        dialogues[0][0] = "You opened the door";
        dialogues[1][0] = "What are you doing?)))";
        dialogues[2][0] = "This key does not fit,\n try another door.?)))";

    }


    public boolean use(Entity entity) {


        String targetName = "Door";
        String targetName2 = "Door2";

        boolean use = false;

        int objIndex = getDetected(entity, gamePanel.obj, targetName);
//        int objIndex2 = getDetected(entity, gamePanel.obj, targetName2);



        if (objIndex != 999) {

            if(gamePanel.obj[objIndex].openFirst == true) {


                startDialogue(this, 0);
                gamePanel.playSound(11);
                gamePanel.obj[objIndex].opened = true;
                gamePanel.obj[objIndex].stay1 = gamePanel.obj[objIndex].image2;
                gamePanel.obj[objIndex].collision = false;
                use =  true;
            }else {
                startDialogue(this, 2);
            }


        } else {

            startDialogue(this, 1);
            use =  false;
        }

        return use;

    }
}

