package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Letter extends Entity {

    int spriteCounter = 0;
    GamePanel gamePanel;

    public OBJ_Letter(GamePanel gamePanel) {

        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typeLetter;

        name = "Letter";



        stay1 = setup("/items/sprite_letter0", gamePanel.tileSize, gamePanel.tileSize);
        coin1 = setup("/items/sprite_letter0", gamePanel.tileSize, gamePanel.tileSize);
        coin2 = setup("/items/sprite_letter1", gamePanel.tileSize, gamePanel.tileSize);
        coin3 = setup("/items/sprite_letter2", gamePanel.tileSize, gamePanel.tileSize);


        collision = true;
        description = "< Letter > \n Read this!!!";

        stackable = false;
        setAction();
        setDialogue();



    }

    public void setAction(){

        spriteCounter++;
        if (spriteCounter <= 12) {
            stay1 = coin1;
        } else if (spriteCounter > 12 && spriteCounter <= 24) {
            stay1 = coin2;
        } else if (spriteCounter > 24 && spriteCounter <= 36) {
            stay1 = coin3;
        } else if (spriteCounter > 36 && spriteCounter <= 48) {
            stay1 = coin2;

            spriteCounter %= 48;
        }
    }

    public void setDialogue(){

        dialogues[0][0] = "Hello. If you're reading this letter,\n then you've most likely come \nacross a strange game console.";
        dialogues[0][1] = "Unfortunately, getting out \nof here will not be so easy.\nYou will have to deal with\n it and find a way out.";
        dialogues[0][2] = "Danger awaits you ahead,\n but there will be someone with\n whom you can come to an agreement.";
        dialogues[0][3] = "You must find the Old Wizard.\n He will help and tell\n you what to do.";
        dialogues[0][4] = "I wish you luck and \nI hope you can even catch up with me \nif I'm still alive.";
        dialogues[0][5] = "And don't forget to check out the\n Controls in the Settings!.";
        dialogues[0][6] = "Sincerely,\n Mysterious Stranger.";
    }

    public void update(){
        setAction();
    }

    public void read(){

        startDialogue(this, 0);


    }
}
