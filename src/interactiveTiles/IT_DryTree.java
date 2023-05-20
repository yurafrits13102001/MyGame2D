package interactiveTiles;

import main.GamePanel;

public class IT_DryTree extends InteractiveTile{

    GamePanel gamePanel;

    public IT_DryTree(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        stay1 = setup("/interactiveTiles/sprite_dryTree0", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;
    }

}
