package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Axe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {


    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasPurpleKey = 0;
    public int hasBlueKey = 0;
    public int hasOrangeKey = 0;
    public int hasApple = 0;
    public int damageAnimationCounter = 0;

    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);


        this.keyHandler = keyHandler;

        screenX = gamePanel.worldWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.worldHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 13;
        solidArea.y = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 16;
        solidArea.height = 18;

        setDefaultValues();
        getPlayerImage();
        setSpeech();
        setItems();
    }

    public void setDefaultValues() {

        worldX = 624;
        worldY = 624;
        speed = 4;
        direction = "down";

        //player health
        maxLife = 6;
        life = 6;
    }

    public Entity currentInstrument = new OBJ_Axe(gamePanel);

    public void setItems(){
        inventory.add(currentInstrument);

    }

    public void setSpeech(){

        speech[0] = "Marichka: Oh no!!!! Where i am?????!!!!\n I have to be home by 10 pm!!!! ";
        speech[1] = "Spreader: Hello!!! you got into this world by accident!!\n To return to Novoselitsa, you must find a portal!!!";
        speech[2] = "Great adventures and even dangerous challenges await you ahead!!\n Be careful! Good luck!!!";
        speech[3]  = null;
    }

    public void getPlayerImage() {


        stay1 = setup("player/sprite_marichka_new_model0");
        stay2 = setup("player/sprite_marichka04");
        up1 = setup("player/sprite_marichka_newmodel20");
        up2 = setup("player/sprite_marichka_newmodel21");
        down1 = setup("player/sprite_marichka_newmodel10");
        down2 = setup("player/sprite_marichka_newmodel11");
        left1 = setup("player/sprite_marichka_newmodel31");
        left2 = setup("player/sprite_marichka_newmodel41");
        right1 = setup("player/sprite_marichka_newmodel30");
        right2 = setup("player/sprite_marichka_newmodel40");
    }


    public void update() {
        if (!keyHandler.leftPressed || !keyHandler.rightPressed || !keyHandler.upPressed ||
                !keyHandler.downPressed) {
            direction = "stay";


            if (keyHandler.downPressed == true || keyHandler.upPressed == true ||
                    keyHandler.leftPressed == true || keyHandler.rightPressed == true ) {


                if (keyHandler.upPressed) {
                    direction = "up";
//                    worldY -= speed;
                }
                if (keyHandler.downPressed) {
                    direction = "down";
//                    worldY += speed;
                }
                if (keyHandler.leftPressed) {
                    direction = "left";
//                    worldX -= speed;
                }
                if (keyHandler.rightPressed) {
                    direction = "right";
//                    worldX += speed;
                }

                //CHECK TILE COLLISION:
                collisionOn = false;
                gamePanel.collisionChecker.checkTile(this);

                //CHECK OBJECT COLLISION:
                int objIndex = gamePanel.collisionChecker.checkObject(this, true);
                pickupObject(objIndex);

                //CHECK NPC COLLISION:
                int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
                interactNpc(npcIndex);

                //CHECK MONSTER COLLISION:
                int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
                contactMonster(monsterIndex);

                //CHECK PLAYER COLLISION:

                //CHECK EVENT

                gamePanel.eventHandler.checkEvent();

                //start





                //IF COLLISION ID FALSE - PLAYER CAN MOVE
                if (collisionOn == false) {

                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

        }
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    @Override
    public void startingSpeech(){

        super.startingSpeech();
    }

    public void pickupObject(int index) {

        if (index != 999) {

            String objectName = gamePanel.obj[index].name;

            switch (objectName) {
                case "Key_Purple":
                    gamePanel.playMusic(1);
                    hasPurpleKey++;
                    gamePanel.obj[index] = null;

                    break;
                case "Key_Blue":
                    gamePanel.playMusic(1);
                    hasBlueKey++;
                    gamePanel.obj[index] = null;

                    break;
                case "Key_Orange":
                    gamePanel.playMusic(1);
                    hasOrangeKey++;
                    gamePanel.obj[index] = null;
                    break;
                case "Door_Purple":
                    if (hasPurpleKey > 0) {
                        gamePanel.obj[index] = null;
                        hasPurpleKey--;
                    }
                    break;
                case "Door_Blue":
                    if (hasBlueKey > 0) {
                        gamePanel.obj[index] = null;
                        hasBlueKey--;
                    }
                    break;
                case "Door_Orange":
                    if (hasOrangeKey > 0) {
                        gamePanel.obj[index] = null;
                        hasOrangeKey--;
                    }
                    break;
                case "Apple":
                    gamePanel.playMusic(1);
                    hasApple++;
                    gamePanel.obj[index] = null;
                    if(hasApple > 0){
                        life = maxLife;
                    }


            }
        }
    }

    public void interactNpc(int index) {

        if (index != 999) {

            if(gamePanel.keyHandler.enterPressed == true) {

                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();


            }

        }
       // gamePanel.keyHandler.enterPressed = false;

    }






    public void interactPlayer() {

        System.out.println("Player collision");

    }

    public void contactMonster(int i){

        if(i != 999){
            if(invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        if (Objects.equals(direction, "up")) {
            if (spriteNum == 1) {
                image = up1;
            }
            if (spriteNum == 2) {
                image = up2;
            }
        }
        if (Objects.equals(direction, "down")) {
            if (spriteNum == 1) {
                image = down1;
            }
            if (spriteNum == 2) {
                image = down2;
            }

        }
        if (Objects.equals(direction, "left")) {
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
        }
        if (Objects.equals(direction, "right")) {
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
        }
        if (Objects.equals(direction, "stay")) {
            if (spriteNum == 1) {
                image = stay1;
            }
            if (spriteNum == 2) {
                image = stay1;
            }
        }

        if(invincible == true) {

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));


        }


        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}

