package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_GhostAttack extends Projectile {

    GamePanel gamePanel;


    public OBJ_GhostAttack(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;


        name = "GhostAttack";
        speed = 3;
        maxLife = 90;
        life = maxLife;
        attack = 4;
        alive = false;
        manaCost = 1;
        collision = true;
        getImage();
    }

    public void getImage(){
        up1 = setup("/items/sprite_ghostAttack0", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/items/sprite_ghostAttack0", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/items/sprite_ghostAttack0", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/items/sprite_ghostAttack0", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/items/sprite_ghostAttack1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/items/sprite_ghostAttack1", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/items/sprite_ghostAttack1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/items/sprite_ghostAttack1", gamePanel.tileSize, gamePanel.tileSize);
    }
}


