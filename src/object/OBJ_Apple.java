package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Apple extends Entity {

    int spriteCounter = 0;
    GamePanel gamePanel;

    public OBJ_Apple(String name ,GamePanel gamePanel) {

        super(gamePanel);

        type = typeApple;

        this.gamePanel = gamePanel;

        this.name = name;

        stay1 = setup("/items/sprite_apple0", gamePanel.tileSize, gamePanel.tileSize);
        coin1 = setup("/items/sprite_appleNew0", gamePanel.tileSize, gamePanel.tileSize);
        coin2 = setup("/items/sprite_appleNew1", gamePanel.tileSize, gamePanel.tileSize);
        coin3 = setup("/items/sprite_appleNew2", gamePanel.tileSize, gamePanel.tileSize);
        coin4 = setup("/items/sprite_appleNew3", gamePanel.tileSize, gamePanel.tileSize);

        collision = true;
        description = "< Apple > \n Restore full\n health";

        stackable = true;
        setAction();



    }

    public void setAction(){

        spriteCounter++;
        if (spriteCounter <= 12) {
            stay1 = coin1;
        } else if (spriteCounter > 12 && spriteCounter <= 24) {
            stay1 = coin2;
        } else if (spriteCounter > 24 && spriteCounter <= 36) {
            stay1 = coin3;
        } else if (spriteCounter > 36 && spriteCounter <= 48) {
            stay1 = coin4;
        }else if (spriteCounter > 48 && spriteCounter <= 60) {
            stay1 = coin3;
            spriteCounter %= 60;
        }else{
            stay1 = setup("/items/sprite_apple0", gamePanel.tileSize, gamePanel.tileSize);
        }
    }

    public void update(){
        setAction();
    }
}
