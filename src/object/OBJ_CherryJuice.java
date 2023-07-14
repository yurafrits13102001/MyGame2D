package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CherryJuice extends Entity {

    GamePanel gamePanel;
    int spriteCounter = 0;

    public OBJ_CherryJuice( GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;


        type = typeJuice;

        name = "Cherry Juice";

        stay1 = setup("/items/sprite_cherryJuise0", gamePanel.tileSize, gamePanel.tileSize);
        image = setup("/items/sprite_cherryJuise0", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/items/sprite_cherryJuise1", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/items/sprite_cherryJuise2", gamePanel.tileSize, gamePanel.tileSize);



        collision = true;
        description = "< Cherry Juice> \n Restore full health";
        stackable = true;
        setAction();

    }

    public void setAction(){

        spriteCounter++;
        if(spriteCounter <= 15){
            stay1 = image;
        }else if(spriteCounter > 15 && spriteCounter <= 30){
            stay1 = image2;
        }else if(spriteCounter > 30 && spriteCounter <= 45){
            stay1 = image3;
        } else if (spriteCounter > 45 && spriteCounter <= 60) {
            stay1 = image2;
            spriteCounter %= 60;
        }else{
            stay1 = setup("/items/sprite_cherryJuise0", gamePanel.tileSize, gamePanel.tileSize);

        }

    }

    public void update(){
        setAction();
    }







    public boolean use(Entity entity) {

        return true;
    }
}
