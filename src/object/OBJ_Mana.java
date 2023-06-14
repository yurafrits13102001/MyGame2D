package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {

    GamePanel gamePanel;
    public OBJ_Mana(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Mana";
        type = typePickupOnly;
        value = 2;

        stay1 = setup("/items/sprite_mana0", gamePanel.tileSize, gamePanel.tileSize);
        image = setup("/items/sprite_mana0", gamePanel.tileSize + 15, gamePanel.tileSize+ 15);
        image2 = setup("/items/sprite_mana1", gamePanel.tileSize+ 15, gamePanel.tileSize + 15);
        image3 = setup("/items/sprite_mana2", gamePanel.tileSize+ 15, gamePanel.tileSize+ 15);


        collision = true;
    }

    public void use(Entity entity){

        gamePanel.playMusic(12);
        gamePanel.ui.addMessage("Mana + " + value);
        entity.mana += value;
        if(entity.mana > entity.maxMana){
            entity.mana = entity.maxMana;
        }


    }


}
