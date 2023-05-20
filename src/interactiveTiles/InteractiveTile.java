package interactiveTiles;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {

    GamePanel gamePanel;

    public boolean destructible = false;

    public InteractiveTile(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void update() {

    }
}
