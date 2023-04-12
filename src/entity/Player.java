package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {


    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasPurpleKey = 0;
    public int hasBlueKey = 0;
    public int hasOrangeKey = 0;

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
    }

    public void setDefaultValues() {

        worldX = 624;
        worldY = 624;
        speed = 4;
        direction = "down";
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
                    keyHandler.leftPressed == true || keyHandler.rightPressed == true) {


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
                int mpcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
                interactNpc(mpcIndex);

                //CHECK PLAYER COLLISION:



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


        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}

