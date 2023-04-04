package main;

import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        gamePanel.obj[0] = new OBJ_Key("Key_Blue");
        gamePanel.obj[0].worldX = 26 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 29 * gamePanel.tileSize;

        gamePanel.obj[1] = new OBJ_Key("Key_Purple");
        gamePanel.obj[1].worldX = 40 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 2 * gamePanel.tileSize;

        gamePanel.obj[2] = new OBJ_Key("Key_Orange");
        gamePanel.obj[2].worldX = 47 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 45 * gamePanel.tileSize;

        gamePanel.obj[3] = new OBJ_Door("Door_Purple");
        gamePanel.obj[3].worldX = 27 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 25 * gamePanel.tileSize;

        gamePanel.obj[4] = new OBJ_Door("Door_Blue");
        gamePanel.obj[4].worldX = 40 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 6 * gamePanel.tileSize;



    }
}
