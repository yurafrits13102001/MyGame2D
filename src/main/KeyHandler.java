package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed,  spasePressed, mousePressed;
    public boolean anyKeyPressed = false;
    public boolean kPressed = false;

    public KeyHandler(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //MAIN MENU
        if (gamePanel.gameState == gamePanel.mainMenuState) {

            if (code == KeyEvent.VK_W) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 2) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNum == 0) {


                    gamePanel.gameState = gamePanel.playState;
                }


                if (gamePanel.ui.commandNum == 1) {
                    //something
                }
                if (gamePanel.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }


        //PLAY STATE

        if (gamePanel.gameState == gamePanel.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_C){
                gamePanel.gameState = gamePanel.inventoryState;
            }
            if (code == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            if (code == KeyEvent.VK_ALL_CANDIDATES) {
                anyKeyPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spasePressed = true;
            }
            if(code == KeyEvent.VK_K){
                kPressed = true;

            }


        } else if (gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.playState;
            }
        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
            }

        }else if (gamePanel.gameState == gamePanel.inventoryState){
            if(code == KeyEvent.VK_C){
                gamePanel.gameState = gamePanel.playState;
            }
            if(code == KeyEvent.VK_W){
                if(gamePanel.ui.slotRow != 0) {

                    gamePanel.playMusic(2);
                    gamePanel.ui.slotRow--;
                }
            }
            if(code == KeyEvent.VK_S){
                if(gamePanel.ui.slotRow != 5) {
                    gamePanel.playMusic(2);
                    gamePanel.ui.slotRow++;
                }
            }
            if(code == KeyEvent.VK_A){
                if(gamePanel.ui.slotCol != 0) {
                    gamePanel.playMusic(2);
                    gamePanel.ui.slotCol--;
                }
            }
            if(code == KeyEvent.VK_D){
                if(gamePanel.ui.slotCol != 6) {
                    gamePanel.playMusic(2);
                    gamePanel.ui.slotCol++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gamePanel.player.selectItem();
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ALL_CANDIDATES){
            anyKeyPressed = false;
        }



    }
}
