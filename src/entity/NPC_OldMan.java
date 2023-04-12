package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gamePanel){

        super(gamePanel);

        direction = "stay";
        speed = 1;

        getNpcImage();
        setDialogue();

        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 5;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 35;
        solidArea.height = 45;

    }
    public void getNpcImage() {


        stay1 = setup("npc/sprite_old_man2222");
        stay2 = setup("npc/sprite_oldman111111");
        up1 = setup("npc/sprite_oldman9999");
        up2 = setup("npc/sprite_oldman101010");
        down1 = setup("npc/sprite_oldman3333");
        down2 = setup("npc/sprite_oldman4444");
        left1 = setup("npc/sprite_oldman5555");
        left2 = setup("npc/sprite_oldman6666");
        right1 = setup("npc/sprite_oldman7777");
        right2 = setup("npc/sprite_oldman8888");
    }

    public void setDialogue(){

        dialogues.add ("Hello!!! Anton Gulpak is LOH!!! \n AHAHAHAHAHAHAHAH ");
        dialogues.add("AHAHAHAHAHAHHAHA ");
        dialogues.add("MARICHKA MARICHKA MARICHKA!!! ");
        dialogues.add(null);

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

    @Override
    public void speak() {

        super.speak();

    }











}
