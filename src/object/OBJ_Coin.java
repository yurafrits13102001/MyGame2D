package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    int spriteCounter = 0;

    GamePanel gamePanel;

    public OBJ_Coin(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typePickupOnly;
        name = "Coin";
        value = 1;
        collision = true;
        stay1 = setup("/items/sprite_coin0", gamePanel.tileSize-10, gamePanel.tileSize-10);
        coin1 = setup("/items/sprite_newCoin0", gamePanel.tileSize-10, gamePanel.tileSize-10);
        coin2 = setup("/items/sprite_newCoin1", gamePanel.tileSize-10, gamePanel.tileSize-10);
        coin3 = setup("/items/sprite_newCoin2", gamePanel.tileSize-10, gamePanel.tileSize-10);
        coin4 = setup("/items/sprite_newCoin3", gamePanel.tileSize-10, gamePanel.tileSize-10);
        coin5 = setup("/items/sprite_newCoin4", gamePanel.tileSize-10, gamePanel.tileSize-10);
        coin6 = setup("/items/sprite_newCoin5", gamePanel.tileSize-10, gamePanel.tileSize-10);
        setAction();


    }

    public void setAction() {
        spriteCounter++;
        if (spriteCounter <= 12) {
            stay1 = coin1;
        } else if (spriteCounter > 12 && spriteCounter <= 24) {
            stay1 = coin2;
        } else if (spriteCounter > 24 && spriteCounter <= 36) {
            stay1 = coin3;
        } else if (spriteCounter > 36 && spriteCounter <= 48) {
            stay1 = coin4;
        } else if (spriteCounter > 48 && spriteCounter <= 60) {
            stay1 = coin5;
        } else if (spriteCounter > 60 && spriteCounter <= 72) {
            stay1 = coin6;
            spriteCounter %= 72;
        }
    }

    public void update(){
        setAction();
    }


    public boolean use(Entity entity){

        gamePanel.playSound(1);
        gamePanel.ui.addMessage("Coin + " + value);
        gamePanel.player.coin += value;
        return true;
    }
}
