package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    GamePanel gamePanel;

    public int worldX, worldY;
    public int speed;

    public BufferedImage stay1, stay2, up1, up2, down1, down2, left1, left2, right1, right2, item;
    public String direction = "stay";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(8, 20, 16, 16);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    public ArrayList<String> dialogues = new ArrayList<>();
    public String[] speech = new String[20];
    public int dialogueIndex = 0;
    public int speechIndex = 0;
    boolean attacking = false;


    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public BufferedImage image, image2, image3, imageApple;
    public BufferedImage axeUp1, axeUp2, axeUp3, axeDown1, axeDown2, axeDown3, axeLeft1, axeLeft2, axeLeft3,
            axeRight1, axeRight2, axeRight3;
    public String name;
    public boolean collision = false;


    public String description = "";

    //TYPE
    public int type;
    public final int typePlayer = 0;
    public final int typeNpc = 1;
    public final int typeMonster = 2;
    public final int typeAxe = 3;
    public final int typeApple = 4;
    public final int typeKey = 5;


    public Entity(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    public void setAction() {
    }

    public void startingSpeech(){



        if (gamePanel.player.speech[speechIndex] == null) {
            speechIndex = 0;
        }
        gamePanel.ui.currentSpeech = gamePanel.player.speech[speechIndex];
        speechIndex++;

        gamePanel.keyHandler.enterPressed = false;
    }



    public void speak() {


        if (dialogues.get(dialogueIndex) == null) {
            dialogueIndex = 0;
            gamePanel.gameState = gamePanel.playState;

        }

            gamePanel.ui.currentDialogue = dialogues.get(dialogueIndex);




            dialogueIndex++;



        gamePanel.keyHandler.enterPressed = false;

        switch (gamePanel.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;
            default:
                direction = "pick";
        }



    }


    public void update() {

        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gamePanel.player.invincible == false){
                gamePanel.player.life -= 1;
                gamePanel.player.invincible = true;
            }

        }

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


    public void draw(Graphics2D g2) {

        BufferedImage image = null;


        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {


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
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);


    }

    public BufferedImage setup(String filePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read((getClass().getResourceAsStream("/res/" + filePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
