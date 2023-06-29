package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_FireballGhost;
import object.OBJ_Key2;
import object.OBJ_Mana;

import java.util.Random;

public class MON_GhostBig extends Entity {

    GamePanel gamePanel;

    public MON_GhostBig(GamePanel gamePanel) {

        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typeMonster;

        name = "Ghost";
        speed = 1;
        maxLife = 10;
        alive = true;
        attack = 1;
        dying = false;
        life = maxLife;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 40;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        projectile = new OBJ_FireballGhost(gamePanel);

        getImage();
    }

    public void getImage() {

        up1 = setup("/mobs/sprite_ghost6", gamePanel.tileSize+15, gamePanel.tileSize+15);
        up2 = setup("/mobs/sprite_ghost7", gamePanel.tileSize+15, gamePanel.tileSize+15);
        down1 = setup("/mobs/sprite_ghost4", gamePanel.tileSize+15, gamePanel.tileSize+15);
        down2 = setup("/mobs/sprite_ghost5", gamePanel.tileSize+15, gamePanel.tileSize+15);
        left1 = setup("/mobs/sprite_ghost0", gamePanel.tileSize+15, gamePanel.tileSize+15);
        left2 = setup("/mobs/sprite_ghost1", gamePanel.tileSize+15, gamePanel.tileSize+15);
        right1 = setup("/mobs/sprite_ghost2", gamePanel.tileSize+15, gamePanel.tileSize+15);
        right2 = setup("/mobs/sprite_ghost3", gamePanel.tileSize+15, gamePanel.tileSize+15);
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
//        if (i < 40) {
//            dropItem(new OBJ_Coin(gamePanel));
//        }
//        if (i > 60) {
//            dropItem(new OBJ_Mana(gamePanel));
//        }

        dropItem(new OBJ_Key2(gamePanel));


    }
}
