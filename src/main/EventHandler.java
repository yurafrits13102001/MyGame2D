package main;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 20;
            eventRect[col][row].y = 20;
            eventRect[col][row].width = 5;
            eventRect[col][row].height = 5;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {

        int replicCounter = 0;
        boolean replicRead = false;

        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gamePanel.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true) {

//            if(replicRead == false) {
//
//
//                if (hit(15, 13, "right") == true) {
//                    replic(gamePanel.dialogueState);
//                    replicRead = true;
//                    canTouchEvent = false;
//                }
//            }
//            if(replicRead == true) {
//
//                if (hit(15, 13, "right") == true) {
//
//                }
//            }
        }


    }

    public boolean hit(int col, int row, String reqDirection) {

        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        eventRect[col][row].x = col * gamePanel.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gamePanel.tileSize + eventRect[col][row].y;

        if (gamePanel.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {

        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "You have damage!!!";
        gamePanel.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }

    public void replic(int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "Hello Marichka!!!";

    }


}
