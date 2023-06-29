package data;

import entity.Entity;
import interactiveTiles.InteractiveTile;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    //player stats
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int coin;

    //player inventory

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();

    int currentInstrumentSlot;

    //obj on map

    String[] objectNames;
    int[] objWorldX;
    int[] objWorldY;
    String[] objectLootNames;
    boolean[] objectOpened;

    //player position
    int playerWorldX;
    int playerWorldY;

    //interactive tiles alive

    boolean[] iTilesData;



}
