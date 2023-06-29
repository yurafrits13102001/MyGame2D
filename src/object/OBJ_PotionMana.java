package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_PotionMana extends Entity {

    GamePanel gamePanel;
    int spriteCounter = 0;

    public OBJ_PotionMana(String name, GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;


        type = typePotion;

        this.name = name;

        stay1 = setup("/items/sprite_newPotionMana0", gamePanel.tileSize, gamePanel.tileSize);
        image = setup("/items/sprite_newPotionMana1", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/items/sprite_newPotionMana2", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/items/sprite_newPotionMana3", gamePanel.tileSize, gamePanel.tileSize);
        imageApple = setup("/items/sprite_manaFramePlus0", gamePanel.tileSize, gamePanel.tileSize);

        collision = true;
        description = "< Mana potion > \n Restore full mana";
        stackable = true;
        setAction();

    }

    public void setAction(){

        spriteCounter++;
        if(spriteCounter <= 15){
            stay1 = image;
        }else if(spriteCounter > 15 && spriteCounter <= 30){
            stay1 = image2;
        }else if(spriteCounter > 30 && spriteCounter <= 45){
            stay1 = imageApple;
        } else if (spriteCounter > 45 && spriteCounter <= 60) {
            stay1 = image3;
        }
        else if (spriteCounter > 60 && spriteCounter <= 75) {
            stay1 = imageApple;
        }else if (spriteCounter > 75 && spriteCounter <= 90) {
            stay1 = image2;
            spriteCounter %= 90;
        }else{
            stay1 = setup("/items/sprite_newPotionMana0", gamePanel.tileSize, gamePanel.tileSize);

        }

    }

     public void update(){
        setAction();
     }







    public boolean use(Entity entity) {

        return true;
    }
}
