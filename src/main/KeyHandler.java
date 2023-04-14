package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, anyKeyPressed, spasePressed;

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


        } else if (gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.playState;
            }
        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
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


    }
}
