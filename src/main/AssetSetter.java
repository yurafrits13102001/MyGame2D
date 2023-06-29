package main;

import entity.NPC_OldMan;
import interactiveTiles.IT_DryTree;
import monster.MON_Ghost;
import monster.MON_GhostBig;
import monster.MON_GreenSlime;
import object.*;

import java.io.Serial;
import java.io.Serializable;

public class AssetSetter implements Serializable {

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


        gamePanel.obj[i] = new OBJ_Key(gamePanel);
        gamePanel.obj[i].worldX = 57 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 55 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Door(gamePanel);
        gamePanel.obj[i].worldX = 53 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 59 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Door(gamePanel);
        gamePanel.obj[i].worldX = 50 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 16 * gamePanel.tileSize;
        i++;


        gamePanel.obj[i] = new OBJ_Apple("Apple" ,gamePanel);
        gamePanel.obj[i].worldX = 25 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 29 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Chest(gamePanel);
        gamePanel.obj[i].setLoot(new OBJ_Apple("Apple", gamePanel));
        gamePanel.obj[i].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 38 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Axe(gamePanel);
        gamePanel.obj[i].worldX = 35 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 33 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Bow(gamePanel);
        gamePanel.obj[i].worldX = 51 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 20 * gamePanel.tileSize;

        i++;
        gamePanel.obj[i] = new OBJ_Door(gamePanel);
        gamePanel.obj[i].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 35 * gamePanel.tileSize;
        i++;
//        gamePanel.obj[i] = new OBJ_Mana(gamePanel);
//        gamePanel.obj[i].worldX = 28 * gamePanel.tileSize;
//        gamePanel.obj[i].worldY = 29 * gamePanel.tileSize;
//        i++;

        gamePanel.obj[i] = new OBJ_PotionMana("PotionMana", gamePanel);
        gamePanel.obj[i].worldX = 27 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 29 * gamePanel.tileSize;
        i++;
        gamePanel.obj[i] = new OBJ_Coin( gamePanel);
        gamePanel.obj[i].worldX = 27 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 30 * gamePanel.tileSize;
        i++;
        gamePanel.obj[i] = new OBJ_Chest(gamePanel);

        gamePanel.obj[i].worldX = 65 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 14 * gamePanel.tileSize;
        i++;

        gamePanel.obj[i] = new OBJ_Letter(gamePanel);
        gamePanel.obj[i].worldX = 16 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 13 * gamePanel.tileSize;

        i++;
        gamePanel.obj[i] = new OBJ_PotionMana("PotionMana", gamePanel);
        gamePanel.obj[i].worldX = 40 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 29 * gamePanel.tileSize;
        i++;
        gamePanel.obj[i] = new OBJ_Apple("Apple", gamePanel);
        gamePanel.obj[i].worldX = 42 * gamePanel.tileSize;
        gamePanel.obj[i].worldY = 29 * gamePanel.tileSize;
        i++;



    }

    public void setNpc(){

        gamePanel.npc[0] = new NPC_OldMan(gamePanel);
        gamePanel.npc[0].worldX = 50 * gamePanel.tileSize;
        gamePanel.npc[0].worldY = 14 * gamePanel.tileSize;
    }

    public void setMonster(){
        gamePanel.monster[0] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[0].worldX = 28 * gamePanel.tileSize;
        gamePanel.monster[0].worldY = 45 * gamePanel.tileSize;

        gamePanel.monster[1] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[1].worldX = 25 * gamePanel.tileSize;
        gamePanel.monster[1].worldY = 45 * gamePanel.tileSize;

        gamePanel.monster[2] = new MON_Ghost(gamePanel);
        gamePanel.monster[2].worldX = 22 * gamePanel.tileSize;
        gamePanel.monster[2].worldY = 48 * gamePanel.tileSize;

        gamePanel.monster[3] = new MON_Ghost(gamePanel);
        gamePanel.monster[3].worldX = 26 * gamePanel.tileSize;
        gamePanel.monster[3].worldY = 48 * gamePanel.tileSize;

        gamePanel.monster[4] = new MON_GhostBig(gamePanel);
        gamePanel.monster[4].worldX = 24 * gamePanel.tileSize;
        gamePanel.monster[4].worldY = 46 * gamePanel.tileSize;

        gamePanel.monster[5] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[5].worldX = 28 * gamePanel.tileSize;
        gamePanel.monster[5].worldY = 29 * gamePanel.tileSize;

        gamePanel.monster[6] = new MON_GreenSlime(gamePanel);
        gamePanel.monster[6].worldX = 22 * gamePanel.tileSize;
        gamePanel.monster[6].worldY = 29 * gamePanel.tileSize;
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
        gamePanel.iTile[i] = new IT_DryTree(gamePanel);
        gamePanel.iTile[i].worldX = 38 * gamePanel.tileSize;
        gamePanel.iTile[i].worldY = 29 * gamePanel.tileSize;
        i++;


    }


}
