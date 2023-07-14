package entity;

import interactiveTiles.IT_Grass;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

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
    boolean hasKey = false;
    boolean haveFireball = false;
    OBJ_Fireball fireball = new OBJ_Fireball(gamePanel);
    OBJ_Hand hand = new OBJ_Hand(gamePanel);

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);


        this.keyHandler = keyHandler;

        screenX = gamePanel.worldWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.worldHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 13;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 23;
        solidArea.height = 25;

        attackArea.width = 16;
        attackArea.height = 18;

        setDefaultValues();




    }

    public void setDefaultValues() {

        worldX = 624;
        worldY = 624;
        speed = 4;
        direction = "down";

        holdBow = false;
        attacking = false;

        projectile = new OBJ_Fireball(gamePanel);
        //player health
        maxLife = 6;
        life = maxLife;
        //player mana
        maxMana = 6;
        mana = maxMana;
        inventory.add(hand);
        currentInstrument = hand;
        speakWithOldMan = false;
        getPlayerImage();
        getPlayerAxeImages();
//        getPlayerBowImage();
        setSpeech();

    }

    public Entity currentInstrument;


    public void setDefaultPositions(){

        worldX = 624;
        worldY = 624;
        direction = "down";
    }

    public void restoreLifeAndMana(){
        //player health
        maxLife = 6;
        life = maxLife;
        //player mana
        maxMana = 6;
        mana = maxMana;
        invincible = false;

    }

    public void setInventory(){

        inventory.clear();
        inventory.add(currentInstrument);
    }



    public void setSpeech() {

        speech[0] = "Marichka: Oh no!!!! Where i am?????!!!!\n I have to be home by 10 pm!!!! ";
        speech[1] = "Spreader: Hello!!! you got into this world by accident!!\n To return to Novoselitsa, you must find a portal!!!";
        speech[2] = "Great adventures and even dangerous challenges await you ahead!!\n Be careful! Good luck!!!";
        speech[3] = null;
    }

    public void getPlayerImage() {


        stay1 = setup("/player/sprite_marichka_new_model0", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/player/sprite_marichka04", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/player/sprite_marichka_newmodel20", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/player/sprite_marichka_newmodel21", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/player/sprite_marichka_newmodel10", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/player/sprite_marichka_newmodel11", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/player/sprite_marichka_newmodel31", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/player/sprite_marichka_newmodel41", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/player/sprite_marichka_newmodel30", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/player/sprite_marichka_newmodel40", gamePanel.tileSize, gamePanel.tileSize);
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

//    public void getPlayerBowImage() {
//        bowDown1 = setup("/player/bow/sprite_BowDown10", gamePanel.tileSize, gamePanel.tileSize);
//        bowUp1 = setup("/player/bow/sprite_BowUp0", gamePanel.tileSize, gamePanel.tileSize);
//        bowLeft1 = setup("/player/bow/sprite_bowRightLeft0", gamePanel.tileSize, gamePanel.tileSize);
//        bowRight1 = setup("/player/bow/sprite_bowRightLeft1", gamePanel.tileSize, gamePanel.tileSize);
//
//
//    }

    public int getCurrentInstrumentSlot(){
        int currentInstrumentSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentInstrument){
                currentInstrumentSlot = i;
            }
        }
        return currentInstrumentSlot;
    }




    public void update() {


        if (attacking == true) {

            attacking();


        } else if (!keyHandler.downPressed || !keyHandler.upPressed || !keyHandler.leftPressed
                || !keyHandler.rightPressed) {

            if (direction.equals("down")) {
                direction = "stayDown";
            } else if (direction.equals("up")) {
                direction = "stayUp";
            } else if (direction.equals("left")) {
                direction = "stayLeft";
            } else if (direction.equals("right")) {
                direction = "stayRight";
            }


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

                //CHECK INTERACTIVE COLLISION
                int iTileIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.iTile);
                interactGrass(iTileIndex);







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
            if (invincible == true) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }


        }

//        if (gamePanel.keyHandler.shotKeyPressed == true && projectile.alive == false
//                && projectile.haveResource(this) == true && haveFireball == true && currentInstrument.type == typeHand) {
//
//
//
//
//            projectile.set(worldX, worldY, direction, true, this);
//
//            projectile.subtractResource(this);
//
//            gamePanel.projectileList.add(projectile);
//
//            gamePanel.playSound(6);




        if(mana > maxMana){
            mana = maxMana;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(life <= 0){
            gamePanel.gameState = gamePanel.gameOverState;
            gamePanel.stopMusic();
            gamePanel.playSound(14);
        }

    }

//


    private void damageInteractiveTiles(int i) {

        if (i != 999 && gamePanel.iTile[i].destructible == true) {
            gamePanel.playSound(5);

            generateParticle(gamePanel.iTile[i], gamePanel.iTile[i]);

            gamePanel.iTile[i] = null;
        }
    }

    private void interactGrass(int i){


        if (i != 999  && gamePanel.iTile[i].destructible == true && gamePanel.iTile[i].type == typeGrass) {
            gamePanel.playSound(18);



            gamePanel.iTile[i] = null;
        }
    }

    public void setDialogue(){


    }

    @Override
    public void startingSpeech() {

        super.startingSpeech();
    }


    public void shooting() {


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

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int iTileIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.iTile);
            damageInteractiveTiles(iTileIndex);


            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            damageMonster(monsterIndex, attack);

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

                //only pickup
                if (gamePanel.obj[index].type == typePickupOnly) {
                    gamePanel.obj[index].use(this);
                    gamePanel.obj[index] = null;
                }
                else if(gamePanel.obj[index].type == typeObstacle){
                    if(gamePanel.keyHandler.enterPressed == true){
                        gamePanel.obj[index].interact();
                        gamePanel.keyHandler.enterPressed = false;
                    }
                }


                //pickup inventory
                else {

                if (canObtainItem(gamePanel.obj[index]) == true) {

                    if (gamePanel.obj[index].type != typeDoor) {

                        gamePanel.playSound(13);
                        gamePanel.ui.addMessage("Pick up a " + gamePanel.obj[index].name);
                        gamePanel.obj[index] = null;
                    }


                } else {
                    text = "You can not carry any more items!";
                }

            }
        }
    }


    public void interactNpc(int index) {



            if (index != 999) {
                if(gamePanel.keyHandler.enterPressed == true) {
                gamePanel.gameState = gamePanel.dialogueState;

                gamePanel.saveLoad.save();
                gamePanel.npc[index].speak();
                haveFireball = true;
                interactPlayer();
                attacking = false;
                this.speakWithOldMan = true;

                gamePanel.keyHandler.enterPressed = false;

            }
        }






        if (keyHandler.kPressed == true) {
            if (currentInstrument != null && currentInstrument != hand) {
                attacking = true;

                keyHandler.kPressed = false;
                gamePanel.playSound(4);
            }
        }
        if (keyHandler.shotKeyPressed == true) {
            if (currentInstrument != null) {
                if (gamePanel.keyHandler.shotKeyPressed == true && projectile.alive == false
                        && projectile.haveResource(this) == true && haveFireball == true && currentInstrument.type == typeHand) {




                    projectile.set(worldX, worldY, direction, true, this);

                    projectile.subtractResource(this);

                    gamePanel.projectileList.add(projectile);

                    gamePanel.playSound(6);



                }
                if(mana > maxMana){
                    mana = maxMana;
                }
                if(life > maxLife){
                    life = maxLife;
                }


                keyHandler.shotKeyPressed = false;

            }
        }


    }
    // gamePanel.keyHandler.enterPressed = false;

    public void interactITiles(int index){
        if(index != 999){
            gamePanel.iTile[index].use(this);
        }
    }




    public void interactPlayer() {

        System.out.println("Player collision");

    }

    public void contactMonster(int i) {


        if (i != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
                gamePanel.playSound(7);

            }
        }
    }

    public void selectItem() {

        int itemIndex = gamePanel.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == typeAxe) {
                currentInstrument = selectedItem;
                gamePanel.ui.addMessage("You selected " + inventory.get(itemIndex).name);
                holdBow = false;
                attacking = true;
                gamePanel.keyHandler.enterPressed = false;




            } else if (selectedItem.type == typeApple) {
                if(selectedItem.amount > 1) {
                    selectedItem.amount--;

                    if(life != maxLife) {
                        gamePanel.playSound(3);
                        life += 2;
                        gamePanel.ui.addMessage("You ate apple!");
                        gamePanel.keyHandler.enterPressed = false;
                    }else{
                        gamePanel.ui.addMessage( "Your life is full!");

                    }

                }else {
                    gamePanel.playSound(3);
                    life += 2;
                    gamePanel.ui.addMessage("You ate apple!");
                    inventory.remove(itemIndex);
                    gamePanel.keyHandler.enterPressed = false;

                }
            } else if (selectedItem.type == typeHand) {
                currentInstrument = selectedItem;
                gamePanel.ui.addMessage("You selected " + inventory.get(itemIndex).name);
                attacking = false;
                gamePanel.keyHandler.enterPressed = false;



            } else if (selectedItem.type == typePotion) {
                if(selectedItem.amount > 1) {
                    selectedItem.amount--;

                    if(mana != maxMana) {
                        gamePanel.playSound(9);
                        mana = maxMana;
                        gamePanel.ui.addMessage("You drank the mana potion");
                        gamePanel.keyHandler.enterPressed = false;
                    }else{
                        gamePanel.ui.addMessage("Your mana is full!");

                    }


                }else {
                    gamePanel.playSound(9);
                    mana = maxMana;
                    gamePanel.ui.addMessage("You drank the mana potion");
                    inventory.remove(itemIndex);
                    gamePanel.keyHandler.enterPressed = false;

                }
            } else if(selectedItem.type == typeJuice) {
                if (selectedItem.amount > 1) {
                    selectedItem.amount--;
                    gamePanel.playSound(9);
                    life = maxLife;
                    gamePanel.ui.addMessage("You drank the Cherry Juice");
                    gamePanel.keyHandler.enterPressed = false;
                } else {
                    gamePanel.playSound(9);
                    life = maxLife;
                    gamePanel.ui.addMessage("You drank the Cherry Juice");
                    inventory.remove(itemIndex);
                    gamePanel.keyHandler.enterPressed = false;
                }
            }

            else if (selectedItem.type == typeKey) {
                if(selectedItem.use(this) == true){
                    inventory.remove(itemIndex);
                    gamePanel.keyHandler.enterPressed = false;

                }


//                for (Entity entity : inventory) {
//                    if (entity.type == typeKey) {
//                       entity.use(entity);
//                        break;
//                    }
//                }
//                if (hasKey) {
//                    gamePanel.ui.addMessage("You opened the door");
//                    inventory.remove(itemIndex);
//                } else {
//                    gamePanel.ui.addMessage("You need a key to open the door");
//                }
            }else if (selectedItem.type == typeLetter){

                    selectedItem.read();

            }


            else {
                // Якщо вибраний предмет не є ні сокирою, ні яблуком, ні луком, скидаємо вибраний інструмент
                currentInstrument = null;
            }
        }
    }

    public int searchItemInInventory(String itemName){

        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++){

            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item){

        boolean canObtain = false;

        //check if stackable
        if(item.stackable == true){

            int index = searchItemInInventory(item.name);
            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{ //new item so need to check vacancy
                if(inventory.size() != inventorySize){
                    inventory.add(item);
                    canObtain = true;
                }

            }
        }else{  //not stackable so check vacancy
            if(inventory.size() != inventorySize){
                inventory.add(item);
                canObtain = true;
            }

        }
        return canObtain;

    }


    public void damageMonster(int i, int attack) {

        if (i != 999) {
            if (gamePanel.monster[i].invincible == false) {
                gamePanel.monster[i].life -= attack;
                gamePanel.monster[i].invincible = true;
                gamePanel.monster[i].damageReaction();
                gamePanel.playSound(8);

                if (gamePanel.monster[i].life <= 0) {
                    gamePanel.monster[i].dying = true;
                }

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
                if (shooting == true) {
                    if (spriteNum == 1) {
                        image = bowUp1;
                    }
                    if (spriteNum == 2) {
                        image = bowUp1;
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
                if (shooting == true) {
                    if (spriteNum == 1) {
                        image = bowDown1;
                    }
                    if (spriteNum == 2) {
                        image = bowDown1;
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
                }
                if (attacking == true) {
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
                if (shooting == true) {
                    if (spriteNum == 1) {
                        image = bowLeft1;
                    }
                    if (spriteNum == 2) {
                        image = bowLeft1;
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
                if (shooting == true) {
                    if (spriteNum == 1) {
                        image = bowRight1;
                    }
                    if (spriteNum == 2) {
                        image = bowRight1;
                    }
                }
                break;

            case "stayDown":
                image = stay1;
                break;
            case "stayUp":
                image = stay1;
                break;
            case "stayLeft":
                image = left2;
                break;
            case "stayRight":
                image = right2;
                break;


        }


        if (invincible == true) {

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));


        }


        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}

