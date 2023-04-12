package main;

import entity.NPC_OldMan;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){

        gamePanel.obj[0] = new OBJ_Key("Key_Blue", gamePanel);
        gamePanel.obj[0].worldX = 26 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 29 * gamePanel.tileSize;

        gamePanel.obj[1] = new OBJ_Key("Key_Purple", gamePanel);
        gamePanel.obj[1].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 12 * gamePanel.tileSize;

        gamePanel.obj[2] = new OBJ_Key("Key_Orange", gamePanel);
        gamePanel.obj[2].worldX = 57 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 55 * gamePanel.tileSize;

        gamePanel.obj[3] = new OBJ_Door("Door_Purple", gamePanel);
        gamePanel.obj[3].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 35 * gamePanel.tileSize;

        gamePanel.obj[4] = new OBJ_Door("Door_Blue", gamePanel);
        gamePanel.obj[4].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 16 * gamePanel.tileSize;



    }

    public void setNpc(){

        gamePanel.npc[0] = new NPC_OldMan(gamePanel);
        gamePanel.npc[0].worldX = 40 * gamePanel.tileSize;
        gamePanel.npc[0].worldY = 29 * gamePanel.tileSize;
    }
}
