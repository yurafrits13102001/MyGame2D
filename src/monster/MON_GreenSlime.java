package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Mana;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gamePanel;

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typeMonster;

        name = "Green Slime";
        attack = 1;
        speed = 1;
        maxLife = 4;
        alive = true;
        dying = false;
        life = maxLife;

        solidArea.x = 5;
        solidArea.y = 5;
        solidArea.width = 42;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        up1 = setup("/mobs/sprite_slime0", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/mobs/sprite_slime10", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/mobs/sprite_slime0", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/mobs/sprite_slime10", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/mobs/sprite_slime0", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/mobs/sprite_slime10", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/mobs/sprite_slime0", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/mobs/sprite_slime10", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction(){

        if (onPath == true) {

            int goalCol = (gamePanel.player.worldX + gamePanel.player.solidArea.x) / gamePanel.tileSize;
            int goalRow = (gamePanel.player.worldY + gamePanel.player.solidArea.y) / gamePanel.tileSize;

            searchPath(goalCol, goalRow);



        } else {

            actionLockCounter++;

            if (actionLockCounter == 120) {

                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }


        }
    }


    @Override
    public void damageReaction(){

        actionLockCounter = 0;

      onPath = true;
      speed = 2;
    }

    public void checkDrop() {

        //cast a die
        int i = new Random().nextInt(100)  + 1;

        //set the monster drop
        if(i < 45){
            dropItem(new OBJ_Coin(gamePanel));
        }
        if(i > 55){
            dropItem(new OBJ_Mana(gamePanel));
        }
    }
}
