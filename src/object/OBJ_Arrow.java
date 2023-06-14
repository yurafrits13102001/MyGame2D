package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile {

    GamePanel gamePanel;

    public OBJ_Arrow(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel= gamePanel;

        name = "Arrow";
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        alive = false;


    }

    public void getImage(){

        up1 = setup("/items/sprite_arrow0", gamePanel.tileSize/2, gamePanel.tileSize/2);
        up2 = setup("/items/sprite_arrow20", gamePanel.tileSize/2, gamePanel.tileSize/2);
        down1 = setup("/items/sprite_arrow2", gamePanel.tileSize/2, gamePanel.tileSize/2);
        down2 = setup("/items/sprite_arrow22", gamePanel.tileSize/2, gamePanel.tileSize/2);
        left1 = setup("/items/sprite_arrow1", gamePanel.tileSize/2, gamePanel.tileSize/2);
        left2 = setup("/items/sprite_arrow21", gamePanel.tileSize/2, gamePanel.tileSize/2);
        right1 = setup("/items/sprite_arrow3", gamePanel.tileSize/2, gamePanel.tileSize/2);
        right2 = setup("/items/sprite_arrow23", gamePanel.tileSize/2, gamePanel.tileSize/2);


    }


}
