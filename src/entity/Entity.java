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
    public BufferedImage bowUp1, bowUp2, bowDown1, bowDown2, bowLeft1, bowLeft2, bowRight1, bowRight2;

    public BufferedImage stay1, stay2, up1, up2, down1, down2, left1, left2, right1, right2, itemDest;
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
    public int dyingCounter = 0;
    int hpBarCounter = 0;

    public ArrayList<String> dialogues = new ArrayList<>();
    public String[] speech = new String[20];
    public int dialogueIndex = 0;
    public int speechIndex = 0;
    boolean attacking = false;
    boolean magic = false;
    boolean shooting = false;
    boolean holdBow = false;
    boolean hpBarOn = false;


    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public int mana;
    public int maxMana;
    public int manaCost;
    public int attack;

    public BufferedImage image, image2, image3, imageApple;
    public BufferedImage axeUp1, axeUp2, axeUp3, axeDown1, axeDown2, axeDown3, axeLeft1, axeLeft2, axeLeft3,
            axeRight1, axeRight2, axeRight3;
    public String name;
    public boolean collision = false;


    public String description = "";
    public boolean alive = true;
    public boolean dying = false;

    //TYPE
    public int type;
    public final int typePlayer = 0;
    public final int typeNpc = 1;
    public final int typeMonster = 2;
    public final int typeAxe = 3;
    public final int typeApple = 4;
    public final int typeKey = 5;
    public final int typeBow = 6;
    public final int typeHand = 7;
    public final int  typePotion = 8;
    public final int typeDoor = 9;
    public final int typePickupOnly = 10;
    public int value;
    public int coin;
    boolean contactPlayer = false;

    public Projectile projectile;


    public Entity(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    public void use(Entity entity){}
    public void checkDrop() {}
    public void dropItem(Entity droppedItem){

        for(int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] == null){
                gamePanel.obj[i] = droppedItem;
                gamePanel.obj[i].worldX = worldX;
                gamePanel.obj[i].worldY = worldY;
                break;
            }
        }
    }

    public void setAction() {}

    public void damageReaction(){}

    public void startingSpeech() {


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

    public Color getParticleColor() {

        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = getParticleColor();
        int size = getParticleSize();
        int speed = getParticleSpeed();

        Particle particle1 = new Particle(gamePanel, generator, color, size, speed, -1, -1);
        gamePanel.particleList.add(particle1);

    }


    public void update() {

        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        contactPlayer = gamePanel.collisionChecker.checkPlayer(this, gamePanel.player);
        if (this.type == typeMonster && contactPlayer == true) {
            damagePlayer(attack);
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

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void damagePlayer(int attack) {


        if (gamePanel.player.invincible == false) {
                gamePanel.playSound(7);
                gamePanel.player.life -= attack;
                gamePanel.player.invincible = true;

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

        //Monster HP bar
        if (type == 2 && hpBarOn == true) {

            double oneScale = (double)gamePanel.tileSize/maxLife;
            double hpBarValue = oneScale*life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(screenX - 1, screenY - 16, gamePanel.tileSize + 2, 9);

            g2.setColor(new Color(241, 13, 40));
            g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 7);

            hpBarCounter++;

            if(hpBarCounter > 500){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        if (invincible == true) {

            hpBarOn = true;
            hpBarCounter = 0;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

        }

        if (dying == true) {

            dyingCounter++;

            if (dyingCounter <= 5) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            }
            if (dyingCounter > 5 && dyingCounter <= 10) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            if (dyingCounter > 10 && dyingCounter <= 15) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            }
            if (dyingCounter > 15 && dyingCounter <= 20) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            if (dyingCounter > 20 && dyingCounter <= 25) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            }
            if (dyingCounter > 25 && dyingCounter <= 30) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            if (dyingCounter > 30 && dyingCounter <= 35) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            }
            if (dyingCounter > 35 && dyingCounter <= 40) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            if (dyingCounter > 40) {
                dying = false;
                alive = false;
            }
        }

        g2.drawImage(image, screenX, screenY,  null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


    }


//    public void dyingAnimation(Graphics2D g2){
//
//        dyingCounter++;
//
//        if(dyingCounter <= 5){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
//        }
//        if(dyingCounter > 5 && dyingCounter <= 10){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        }
//        if(dyingCounter > 10 && dyingCounter <= 15){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
//        }
//        if(dyingCounter > 15 && dyingCounter <= 20){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        }
//        if(dyingCounter > 20 && dyingCounter <= 25){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
//        }
//        if(dyingCounter > 25 && dyingCounter <= 30){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        }
//        if(dyingCounter > 30 && dyingCounter <= 35){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
//        }
//        if(dyingCounter > 35 && dyingCounter <= 40){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        }
//        if(dyingCounter > 40){
//            dying = false;
//            alive = false;
//        }
//    }

    public BufferedImage setup(String filePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read((getClass().getResourceAsStream(filePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
