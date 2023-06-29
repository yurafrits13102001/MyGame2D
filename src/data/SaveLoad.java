package data;

import entity.Entity;
import interactiveTiles.InteractiveTile;
import main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {

        this.gp = gp;

    }

    public Entity getObject(String itemName) {

        Entity obj = null;

        switch (itemName) {
            case "Apple":obj = new OBJ_Apple("Apple", gp);break;
            case "Axe": obj = new OBJ_Axe(gp);break;
            case "Bow": obj = new OBJ_Bow(gp);break;
            case "Key": obj = new OBJ_Key(gp);break;
            case "Key for chest": obj = new OBJ_Key2(gp);break;
            case "Potion Mana": obj = new OBJ_PotionMana("Potion Mana", gp);break;
            case "Door": obj = new OBJ_Door(gp);break;
            case "Chest": obj = new OBJ_Chest(gp);break;
            case "Hand": obj = new OBJ_Hand(gp);break;
            case "Letter": obj = new OBJ_Letter(gp);
        }
        return obj;
    }

    public void save() {

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.coin = gp.player.coin;


            //player inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }

            //player equip
            ds.currentInstrumentSlot = gp.player.getCurrentInstrumentSlot();

            //obj on map
            ds.objectNames = new String[gp.obj.length];
            ds.objWorldX = new int[gp.obj.length];
            ds.objWorldY = new int[gp.obj.length];
            ds.objectLootNames = new String[gp.obj.length];
            ds.objectOpened = new boolean[gp.obj.length];

            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] == null) {
                    ds.objectNames[i] = "NA";
                } else {

                    ds.objectNames[i] = gp.obj[i].name;
                    ds.objWorldX[i] = gp.obj[i].worldX;
                    ds.objWorldY[i] = gp.obj[i].worldY;

                    if (gp.obj[i].loot != null) {
                        ds.objectLootNames[i] = gp.obj[i].loot.name;
                    }
                    ds.objectOpened[i] = gp.obj[i].opened;
                }
            }

            //player position


            //interactive tiles




            //write the DataStorage object
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save exception");
            e.printStackTrace();
        }
    }

    public void load() {

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            //read te DataStorage object

            DataStorage ds = (DataStorage) ois.readObject();

            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.coin = ds.coin;

            //player inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                if(getObject(ds.itemNames.get(i)).stackable == true) {
                    gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
                }
            }

            //player equip
            gp.player.currentInstrument = gp.player.inventory.get(ds.currentInstrumentSlot);

            //obj on map
            for (int i = 0; i < gp.obj.length; i++) {
                if (ds.objectNames[i].equals("NA")) {
                    gp.obj[i] = null;
                } else {
                    gp.obj[i] = getObject(ds.objectNames[i]);
                    gp.obj[i].worldX = ds.objWorldX[i];
                    gp.obj[i].worldY = ds.objWorldY[i];

                    if (ds.objectLootNames[i] != null) {
                        gp.obj[i].loot = getObject(ds.objectLootNames[i]);
                    }
                    gp.obj[i].opened = ds.objectOpened[i];
                    if (gp.obj[i].opened == true) {
                        gp.obj[i].stay1 = gp.obj[i].image2;
                    }
                }
            }

            //player position




        } catch (Exception e) {
            System.out.println("Save exception");
            e.printStackTrace();
        }
    }

}
