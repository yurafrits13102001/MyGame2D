package main;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = false;
    int eventCounter = 0;

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


        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {

            if (hit(50, 18, "up") == true) {

                for (int i = 0; i < gamePanel.obj.length; i++) {
                    if (gamePanel.obj[i] != null) {
                        if (gamePanel.obj[i].name.equals("Door")) {
                            gamePanel.obj[i].openFirst = true;
                        }
                    }
                }

            }
            if (hit(53, 59, "down") == true) {

                gamePanel.player.dialogues[1][0] = "Unfortunately, this is still a very \n early version of the game.";
                gamePanel.player.dialogues[1][1] = " If you find bugs or have ideas for\n the continuation of the game, \nplease let me know.";
                gamePanel.player.dialogues[1][2] = " And also definitely rate the game \n(in any way)!";
                gamePanel.player.startDialogue(gamePanel.player, 1);
            }
            if (hit(13, 13, "right") == true) {


                if (eventCounter == 0) {

                    gamePanel.player.dialogues[0][0] = "MARICHKA: What happened??!!!\n Where I am???!!!";
                    gamePanel.player.dialogues[0][1] = " MARICHKA:Why did I pick up that\n game console?!";
                    gamePanel.player.dialogues[0][2] = " MARICHKA:Everything looks so strange.\n Why am I wearing a pink sweater??!! ";
                    gamePanel.player.dialogues[0][3] = " MARICHKA:.  .  .  .  .  .";
                    gamePanel.player.dialogues[0][4] = "MARICHKA:Ok, I need to calm down\n and think of a way to get out of here,\n because then I'll be late for class\n at the university!";
                    gamePanel.player.dialogues[0][5] = "MARICHKA:But I think it's not so\n important anymore...";
                    gamePanel.player.dialogues[0][6] = "MARICHKA:Hmm, there's a note here,\n maybe it says how to find\n a way out!";

                    gamePanel.player.startDialogue(gamePanel.player, 0);
                    eventCounter = 1;
                } else if (eventCounter < 0) {

                }
            }

            if (hit(50, 16, "down") == true) {

                if (eventCounter == 1) {

                    gamePanel.player.dialogues[2][0] = "MARICHKA: Wow! Magic is really cool!!!";
                    gamePanel.player.dialogues[2][1] = "MARICHKA: But it would be better if \nhe taught me how to teleport...";
                    gamePanel.player.dialogues[2][2] = "MARICHKA: Always dreamed of it!";

                    gamePanel.player.startDialogue(gamePanel.player, 2);
                    eventCounter = 2;
                } else if (eventCounter < 1) {

                }


            }
            if (hit(14, 27, "down") == true) {

                if (eventCounter == 2) {

                    gamePanel.player.dialogues[3][0] = "MARICHKA: Oh no!!! I was so hoping that\n there would be no monsters!!!";
                    gamePanel.player.dialogues[3][1] = "MARICHKA: I think if I don't touch them,\n they won't touch me either.";


                    gamePanel.player.startDialogue(gamePanel.player, 3);
                    eventCounter = 3;
                } else if (eventCounter < 2) {

                }
            }
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
