package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    GamePanel gamePanel;

    public OBJ_Coin(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = typePickupOnly;
        name = "Coin";
        value = 1;
        collision = true;
        stay1 = setup("/items/sprite_coin0", gamePanel.tileSize, gamePanel.tileSize);

    }

    public void use(Entity entity){

        gamePanel.playMusic(1);
        gamePanel.ui.addMessage("Coin + " + value);
        gamePanel.player.coin += value;
    }
}
