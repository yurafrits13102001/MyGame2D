package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Ghost extends Entity {

    GamePanel gamePanel;

    public MON_Ghost(GamePanel gamePanel) {

        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typeMonster;

        name = "Ghost";
        speed = 1;
        maxLife = 6;
        alive = true;
        attack = 1;
        dying = false;
        life = maxLife;

        solidArea.x = 5;
        solidArea.y = 5;
        solidArea.width = 45;
        solidArea.height = 45;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        projectile = new OBJ_FireballGhost(gamePanel);

        getImage();
    }

    public void getImage() {

        up1 = setup("/mobs/sprite_ghost6", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/mobs/sprite_ghost7", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/mobs/sprite_ghost4", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/mobs/sprite_ghost5", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/mobs/sprite_ghost0", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/mobs/sprite_ghost1", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/mobs/sprite_ghost2", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/mobs/sprite_ghost3", gamePanel.tileSize, gamePanel.tileSize);
    }

//    @Override
//    public void update(){
//        super.update();
//
//        int xDistance = Math.abs(worldX - gamePanel.player.worldX);
//        int yDistance = Math.abs(worldY - gamePanel.player.worldY);
//        int tileDistance = (xDistance + yDistance)/ gamePanel.tileSize;
//
//        if(onPath == false && tileDistance < 5){
//
//            onPath = true;
//        }
//    }

    public void setAction() {

        if (onPath == true) {

            int goalCol = (gamePanel.player.worldX + gamePanel.player.solidArea.x) / gamePanel.tileSize;
            int goalRow = (gamePanel.player.worldY + gamePanel.player.solidArea.y) / gamePanel.tileSize;

            searchPath(goalCol, goalRow);


            int i = new Random().nextInt(200) + 1;
            if (i > 170 && projectile.alive == false) {
                projectile.set(worldX, worldY, direction, true, this);
                gamePanel.projectileList.add(projectile);

            }
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
    public void damageReaction() {

        actionLockCounter = 0;
        onPath = true;
        speed = 2;


//        switch (gamePanel.player.direction){
//            case "up": direction = "down"; break;
//            case "down": direction = "up"; break;
//            case "left": direction = "right"; break;
//            case "right": direction = "left"; break;
//        }
    }

    public void checkDrop() {

        //cast a die
        int i = new Random().nextInt(100) + 1;

        //set the monster drop
        if (i < 40) {
            dropItem(new OBJ_Coin(gamePanel));
        }
        if (i > 60) {
            dropItem(new OBJ_Mana(gamePanel));
        }

//        dropItem(new OBJ_Key2(gamePanel));


    }
}


