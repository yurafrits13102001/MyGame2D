package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {

    GamePanel gamePanel;

    public NPC_OldMan(GamePanel gamePanel){

        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typeNpc;

        direction = "stay";
        speed = 1;

        getNpcImage();



        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 40;
        setDialogue();

    }
    public void getNpcImage() {


        stay1 = setup("/npc/sprite_old_man2222", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/npc/sprite_oldman111111", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/npc/sprite_oldman9999", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/npc/sprite_oldman101010", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/npc/sprite_oldman3333", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/npc/sprite_oldman4444", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/npc/sprite_oldman5555", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/npc/sprite_oldman6666", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/npc/sprite_oldman7777", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/npc/sprite_oldman8888", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialogue(){

        dialogues[0][1] = "Hello!!! Anton Gulpak is LOH!!! \n AHAHAHAHAHAHAHAH ";
        dialogues[0][2] = "AHAHAHAHAHAHHAHA ";
        dialogues[0][3] = "MARICHKA MARICHKA MARICHKA!!! ";

        dialogues[1][0] = "OLD WIZARD: Hello, Marichka!!!";
        dialogues[1][1] = "OLD WIZARD: I knew you would come \nto rescue me!";
        dialogues[1][2] = "OLD WIZARD: Have you met monsters yet?";
        dialogues[1][3] = "MARICHKA: Yes, and they scare \nme a little.";
        dialogues[1][4] = "MARICHKA: What should I do with them?\n I can't fight at all...";
        dialogues[1][5] = "OLD WIZARD: As a thank you, \n I want to grant you some \n magical powers!";
        dialogues[1][6] = "OLD WIZARD: Now you can attack\n them with fireballs.\n But be careful, they get very\n angry when they are attacked!!!";
        dialogues[1][7] = "OLD WIZARD: And do not forget to monitor\n the amount of mana!";
        dialogues[1][8] = "OLD WIZARD: You will notice one spirit\n that will be bigger\n than the others.";
        dialogues[1][9] = "OLD WIZARD:  It was he who stole the key\n from that Chest.";
        dialogues[1][10] = "OLD WIZARD: Just don't say you didn't\n see that chest. \nBut think, he is bigger,\n therefore stronger!";
        dialogues[1][11] = "OLD WIZARD: Good luck!";


    }

    @Override
    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(125) + 1;

            if (i <= 25) {
                direction = "down";
            }
            else if (i > 25 && i <= 50) {
                direction = "up";
            }
            else if (i > 50 && i <= 75) {
                direction = "right";
            }
            else if (i > 75 && i <= 100) {
                direction = "left";
            }
            else if (i > 100) {
                direction = "stay";
            }
            actionLockCounter = 0;
        }
    }


    public void speak() {

       facePlayer();
       startDialogue(this, 1);
        speakWithOldMan = true;
//        super.speak();

    }











}
