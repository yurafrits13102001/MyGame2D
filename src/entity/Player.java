package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Axe;
import object.OBJ_Key;

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

        attackArea.width = 16;
        attackArea.height = 18;

        setDefaultValues();
        getPlayerImage();
        getPlayerAxeImages();
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

    public void setItems() {
        inventory.add(currentInstrument);


    }

    public void setSpeech() {

        speech[0] = "Marichka: Oh no!!!! Where i am?????!!!!\n I have to be home by 10 pm!!!! ";
        speech[1] = "Spreader: Hello!!! you got into this world by accident!!\n To return to Novoselitsa, you must find a portal!!!";
        speech[2] = "Great adventures and even dangerous challenges await you ahead!!\n Be careful! Good luck!!!";
        speech[3] = null;
    }

    public void getPlayerImage() {


        stay1 = setup("player/sprite_marichka_new_model0", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("player/sprite_marichka04", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("player/sprite_marichka_newmodel20", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("player/sprite_marichka_newmodel21", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("player/sprite_marichka_newmodel10", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("player/sprite_marichka_newmodel11", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("player/sprite_marichka_newmodel31", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("player/sprite_marichka_newmodel41", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("player/sprite_marichka_newmodel30", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("player/sprite_marichka_newmodel40", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void getPlayerAxeImages() {

        axeDown1 = setup("/player/attackAxe/sprite_axeHitDown0", gamePanel.tileSize, gamePanel.tileSize);
        axeDown2 = setup("/player/attackAxe/sprite_axeHitDown1", gamePanel.tileSize, gamePanel.tileSize);
        axeDown3 = setup("/player/attackAxe/sprite_axeHitDown2", gamePanel.tileSize, gamePanel.tileSize);
        axeUp1 = setup("/player/attackAxe/sprite_axeHitUp0", gamePanel.tileSize, gamePanel.tileSize);
        axeUp2 = setup("/player/attackAxe/sprite_axeHitUp1", gamePanel.tileSize, gamePanel.tileSize);
        axeUp3 = setup("/player/attackAxe/sprite_axeHitUp1", gamePanel.tileSize, gamePanel.tileSize);
        axeLeft1 = setup("/player/attackAxe/sprite_axeHitLeft0", gamePanel.tileSize, gamePanel.tileSize);
        axeLeft2 = setup("/player/attackAxe/sprite_axeHitLeft1", gamePanel.tileSize, gamePanel.tileSize);
        axeLeft3 = setup("/player/attackAxe/sprite_axeHitLeft2", 100, gamePanel.tileSize);
        axeRight1 = setup("/player/attackAxe/sprite_axeHitRight0", gamePanel.tileSize, gamePanel.tileSize);
        axeRight2 = setup("/player/attackAxe/sprite_axeHitRight1", gamePanel.tileSize, gamePanel.tileSize);
        axeRight3 = setup("/player/attackAxe/sprite_axeHitRight2", 100, gamePanel.tileSize);
    }


    public void update() {

        if (attacking == true) {


            attacking();
        } else if (!keyHandler.leftPressed || !keyHandler.rightPressed || !keyHandler.upPressed ||
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
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    private void damageInteractiveTiles(int i) {

        if(i != 999 && gamePanel.iTile[i].destructible == true){

            gamePanel.iTile[i] = null;
        }
    }

    @Override
    public void startingSpeech() {

        super.startingSpeech();
    }

    public void attacking() {

        spriteCounter++;

        if (spriteCounter < 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter < 10) {
            spriteNum = 2;
        }
        if (spriteCounter > 10 && spriteCounter < 25) {
            spriteNum = 3;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case  "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int treeIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.iTile);
            damageInteractiveTiles(treeIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;

        }

    }

    public void pickupObject(int index) {

        if (index != 999) {
            String text = "";

            if (inventory.size() != inventorySize) {


                inventory.add(gamePanel.obj[index]);
                gamePanel.playMusic(1);


            } else {
                text = "You can not carry any more items!";
            }
            gamePanel.obj[index] = null;
        }
    }

    public void interactNpc(int index) {
        if (gamePanel.keyHandler.enterPressed == true) {

            if (index != 999) {


                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();
            }
        }

            if(keyHandler.kPressed == true) {
                attacking = true;
                keyHandler.kPressed = false;
            }


}
    // gamePanel.keyHandler.enterPressed = false;


    public void interactPlayer() {

        System.out.println("Player collision");

    }

    public void contactMonster(int i) {

        if (i != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void selectItem() {

        int itemIndex = gamePanel.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == typeAxe) {

                currentInstrument = selectedItem;
            }
            if (selectedItem.type == typeApple) {
                gamePanel.playMusic(3);
                life = maxLife;
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;
        switch (direction) {

            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = axeUp1;
                    }
                    if (spriteNum == 2) {
                        image = axeUp2;
                    }
                    if (spriteNum == 3) {
                        image = axeUp3;
                    }
                }
                break;

            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = axeDown1;
                    }
                    if (spriteNum == 2) {
                        image = axeDown2;
                    }
                    if (spriteNum == 3) {
                        image = axeDown3;
                    }
                }
                break;

            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = axeLeft1;
                    }
                    if (spriteNum == 2) {
                        image = axeLeft2;
                    }
                    if (spriteNum == 3) {
                        image = axeLeft3;
                    }
                }
                break;

            case "right":
                if (attacking == false) {


                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = axeRight1;
                    }
                    if (spriteNum == 2) {
                        image = axeRight2;
                    }
                    if (spriteNum == 3) {
                        image = axeRight3;
                    }
                }
                break;

            case "stay":

                if (spriteNum == 1) {
                    image = stay1;
                }
                if (spriteNum == 2) {
                    image = stay1;
                }
        }


        if (invincible == true) {

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));


        }


        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}

