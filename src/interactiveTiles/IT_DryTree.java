package interactiveTiles;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_DryTree extends InteractiveTile{

    GamePanel gamePanel;

    public IT_DryTree(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        destructible = true;
        collision = true;


        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        stay1 = setup("/background/interactiveTiles/sprite_dryTree0", gamePanel.tileSize, gamePanel.tileSize);

    }

    public Color getParticleColor(){

        Color color = new Color(28, 20, 10);
        return color;
    }

    public int getParticleSize(){
        int size = 16;
        return size;
    }

    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

}
