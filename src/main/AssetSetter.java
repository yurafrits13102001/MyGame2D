package main;

import entity.NPC_OldMan;
import interactiveTiles.IT_DryTree;
import monster.MON_GreenSlime;
import object.OBJ_Apple;
import object.OBJ_Axe;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }




    public void setObject(){

        int i = 0;



        gamePanel.obj[i] = new OBJ_Key( gamePanel);
        gamePanel.obj[i].worldX = 18 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 35 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Key( gamePanel);
        gamePanel.obj[i].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 12 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Key( gamePanel);
        gamePanel.obj[i].worldX = 57 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 55 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Door("Door_Purple", gamePanel);
        gamePanel.obj[i].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 35 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Door("Door_Blue", gamePanel);
        gamePanel.obj[i].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 16 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Apple("Apple" ,gamePanel);
        gamePanel.obj[i].worldX = 25 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 29 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Apple("Apple" ,gamePanel);
        gamePanel.obj[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 38 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Axe(gamePanel);
        gamePanel.obj[i].worldX = 35 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 33 * gamePanel.tileSize;
        i++;




    }

    public void setNpc(){

        gamePanel.npc[0] = new NPC_OldMan(gamePanel);
        gamePanel.npc[0].worldX = 40 * gamePanel.tileSize;
        gamePanel.npc[0].worldY = 29 * gamePanel.tileSize;
    }

    public void setMonster(){
        gamePanel.monster[0] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[0].worldX = 28 * gamePanel.tileSize;
        gamePanel.monster[0].worldY = 29 * gamePanel.tileSize;
    }

    public void setInteractive(){

        int i = 0;
        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 33 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 34 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 35 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 36 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 37 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 37 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 22 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 35 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 21 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 35 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 20 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 35 * gamePanel.tileSize;
        i++;

        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 19 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 35 * gamePanel.tileSize;
        i++;


    }
}
