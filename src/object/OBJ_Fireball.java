package object;

import entity.Player;
import entity.Projectile;
import main.GamePanel;

import javax.swing.text.html.parser.Entity;

public class OBJ_Fireball extends Projectile {


    GamePanel gamePanel;


    public OBJ_Fireball(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;


        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        alive = false;
        manaCost = 1;

        getImage();
    }

    public void getImage(){
        up1 = setup("/items/sprite_FireBall0", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/items/sprite_FireBall1", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/items/sprite_FireBall2", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/items/sprite_FireBall3", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/items/sprite_FireBall4", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/items/sprite_FireBall5", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/items/sprite_FireBall6", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/items/sprite_FireBall7", gamePanel.tileSize, gamePanel.tileSize);
    }

    public boolean haveResource(Player user){

        boolean haveResource = false;
        if(user.mana >= manaCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Player user){
        user.mana -= manaCost;
    }
}
